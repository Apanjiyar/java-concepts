package com.example.java8.functional_interface;

import java.util.function.Predicate;

/**
 * 01
 */
public class PredicateExample {

    public static void main(String[] args) {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;

        System.out.println(isEven.test(10)); // true
        System.out.println(isEven.negate().test(10)); // false
        System.out.println(isEven.and(isPositive).test(10)); // true
        System.out.println(isEven.or(isPositive).test(-2)); // true
        System.out.println(Predicate.isEqual("Hello").test("Hello")); // true
    }
}