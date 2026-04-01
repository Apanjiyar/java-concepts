package src.com.example.java8.stream_api;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * 05 Filter Operations on Streams
 * Demonstrates various filter concepts including:
 * - Basic filtering
 * - Filtering on object fields
 * - Chaining multiple filters
 * - Using Predicate with .and(), .or(), .negate()
 * - Filter with negation (manual and Predicate.not)
 * - Filtering nulls
 */
public class StreamFilterExample {

    public static void main(String[] args){
        // ===== FILTER EXAMPLES =====
        System.out.println("===== FILTER EXAMPLES =====\n");

        // We have list of integers find the no. even numbers from the list
        findEvenNumbers();

        // We have list of strings find the no. of strings which starts with "A"
        findStringStartsWithA();

        // We have list of employees find the no. of employees whose age is greater than 30
        findEmployeesGreaterThan30();

        // Basic filter with IntStream
        basicFilterWithIntStream();

        // Filter on object field
        filterOnObjectField();

        // Chaining multiple filters (each is ANDed)
        chainingMultipleFilters();

        // Equivalent with single predicate using .and()
        equivalentWithSinglePredicate();

        // Filter with negation (manual)
        filterWithNegationManual();

        // Filter with negation (using Predicate.not)
        filterWithNegationPredicateNot();

        // Filter nulls
        filterNulls();
    }

    // ===== ORIGINAL FILTER METHODS =====
    private static void findEvenNumbers(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Integer> even = numbers.stream().filter(n -> n % 2 == 0).toList();
        System.out.println("Number are : " + numbers);
        System.out.println("Even numbers are : " + even);
    }

    private static void findStringStartsWithA(){
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Adam");
        List<String> startsWithA = names.stream().filter(name -> name.startsWith("A")).toList();
        System.out.println("Names are : " + names);
        System.out.println("Names starts with A are : " + startsWithA);
    }

    private static void findEmployeesGreaterThan30(){
        Employee e1 = new Employee("Alice", 25);
        Employee e2 = new Employee("Bob", 35);
        Employee e3 = new Employee("Charlie", 30);
        Employee e4 = new Employee("David", 40);

        List<Employee> employees = Arrays.asList(e1, e2, e3, e4);

        List<Employee> employeeGreaterThen30 = employees.stream().filter(e -> e.getAge() > 30).toList();

        System.out.println("Employees are : " + employees);
        System.out.println("Employees whose age is greater than 30 are : " + employeeGreaterThen30);
    }

    // ===== ADVANCED FILTER METHODS =====

    /**
     * Basic filter with IntStream.rangeClosed().boxed()
     * Converts primitive stream to object stream
     *
     * Code Example:
     * List<Integer> evens = IntStream.rangeClosed(1, 10)
     *     .boxed()
     *     .filter(n -> n % 2 == 0)
     *     .toList();
     * // [2, 4, 6, 8, 10]
     */
    private static void basicFilterWithIntStream(){
        List<Integer> evens = IntStream.rangeClosed(1, 10)
                .boxed()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("IntStream range 1-10 evens: " + evens);
    }

    /**
     * Filter on object field
     * Filters employees based on experience field
     *
     * Code Example:
     * List<Employee> seniors = employees.stream()
     *     .filter(e -> e.getExperience() >= 5)
     *     .toList();
     */
    private static void filterOnObjectField(){
        Employee e1 = new Employee("Alice", 25, "Engineering", 3, 70000);
        Employee e2 = new Employee("Bob", 35, "Engineering", 7, 90000);
        Employee e3 = new Employee("Charlie", 30, "HR", 2, 50000);
        Employee e4 = new Employee("David", 40, "Engineering", 8, 100000);
        List<Employee> employees = Arrays.asList(e1, e2, e3, e4);

        List<Employee> seniors = employees.stream()
                .filter(e -> e.getExperience() >= 5)
                .toList();

        System.out.println("All employees: " + employees);
        System.out.println("Senior employees (experience >= 5): " + seniors);
    }

    /**
     * Chaining multiple filters (each is ANDed)
     * All conditions must be true for an element to pass
     *
     * Code Example:
     * List<Employee> seniorDevs = employees.stream()
     *     .filter(e -> e.getDepartment().equals("Engineering"))
     *     .filter(e -> e.getExperience() >= 5)
     *     .filter(e -> e.getSalary() > 80000)
     *     .toList();
     */
    private static void chainingMultipleFilters(){
        Employee e1 = new Employee("Alice", 25, "Engineering", 3, 70000);
        Employee e2 = new Employee("Bob", 35, "Engineering", 7, 90000);
        Employee e3 = new Employee("Charlie", 30, "HR", 2, 50000);
        Employee e4 = new Employee("David", 40, "Engineering", 8, 100000);
        List<Employee> employees = Arrays.asList(e1, e2, e3, e4);

        List<Employee> seniorDevs = employees.stream()
                .filter(e -> e.getDepartment().equals("Engineering"))
                .filter(e -> e.getExperience() >= 5)
                .filter(e -> e.getSalary() > 80000)
                .toList();

        System.out.println("All employees: " + employees);
        System.out.println("Senior Engineering devs with salary > 80k: " + seniorDevs);
    }

    /**
     * Equivalent with single predicate using .and()
     * More reusable and composable approach
     *
     * Code Example:
     * Predicate<Employee> isEngineer  = e -> e.getDepartment().equals("Engineering");
     * Predicate<Employee> isSenior    = e -> e.getExperience() >= 5;
     * Predicate<Employee> isHighPaid  = e -> e.getSalary() > 80000;
     *
     * List<Employee> result = employees.stream()
     *     .filter(isEngineer.and(isSenior).and(isHighPaid))
     *     .toList();
     */
    private static void equivalentWithSinglePredicate(){
        Employee e1 = new Employee("Alice", 25, "Engineering", 3, 70000);
        Employee e2 = new Employee("Bob", 35, "Engineering", 7, 90000);
        Employee e3 = new Employee("Charlie", 30, "HR", 2, 50000);
        Employee e4 = new Employee("David", 40, "Engineering", 8, 100000);
        List<Employee> employees = Arrays.asList(e1, e2, e3, e4);

        Predicate<Employee> isEngineer = e -> e.getDepartment().equals("Engineering");
        Predicate<Employee> isSenior = e -> e.getExperience() >= 5;
        Predicate<Employee> isHighPaid = e -> e.getSalary() > 80000;

        List<Employee> result = employees.stream()
                .filter(isEngineer.and(isSenior).and(isHighPaid))
                .toList();

        System.out.println("All employees: " + employees);
        System.out.println("Senior High-paid Engineers (using .and()): " + result);
    }

    /**
     * Filter with negation (manual)
     * Using inline negation operator
     *
     * Code Example:
     * List<String> nonEmpty = strings.stream()
     *     .filter(s -> !s.isEmpty())         // verbose negation
     *     .toList();
     */
    private static void filterWithNegationManual(){
        List<String> strings = Arrays.asList("Hello", "", "World", "", "Java");

        List<String> nonEmpty = strings.stream()
                .filter(s -> !s.isEmpty())         // verbose negation
                .toList();

        System.out.println("Original strings: " + strings);
        System.out.println("Non-empty strings (manual negation): " + nonEmpty);
    }

    /**
     * Filter with negation (using Predicate.not)
     * Cleaner approach using Predicate.not() - Java 11+
     *
     * Code Example:
     * List<String> nonEmpty2 = strings.stream()
     *     .filter(Predicate.not(String::isEmpty))  // cleaner (Java 11+)
     *     .toList();
     */
    private static void filterWithNegationPredicateNot(){
        List<String> strings = Arrays.asList("Hello", "", "World", "", "Java");

        List<String> nonEmpty2 = strings.stream()
                .filter(Predicate.not(String::isEmpty))  // cleaner (Java 11+)
                .toList();

        System.out.println("Original strings: " + strings);
        System.out.println("Non-empty strings (Predicate.not()): " + nonEmpty2);
    }

    /**
     * Filter nulls
     * Removes null values from the stream using Objects::nonNull
     *
     * Code Example:
     * List<String> noNulls = stringsWithNulls.stream()
     *     .filter(Objects::nonNull)
     *     .toList();
     */
    private static void filterNulls(){
        List<String> stringsWithNulls = Arrays.asList("Hello", null, "World", null, "Java", "");

        List<String> noNulls = stringsWithNulls.stream()
                .filter(Objects::nonNull)
                .toList();

        System.out.println("Strings with nulls: " + stringsWithNulls);
        System.out.println("After filtering nulls: " + noNulls);
    }
}

/**
 * Employee class with multiple fields for filtering demonstrations
 */
class Employee {
    private final String name;
    private final int age;
    private final String department;
    private final int experience;
    private final int salary;

    // Constructor with all fields
    public Employee(String name, int age, String department, int experience, int salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.experience = experience;
        this.salary = salary;
    }

    // Constructor with only name and age (for backward compatibility)
    public Employee(String name, int age) {
        this(name, age, "Unknown", 0, 0);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public int getExperience() {
        return experience;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dept='" + department + '\'' +
                ", exp=" + experience +
                ", salary=" + salary +
                '}';
    }
}

