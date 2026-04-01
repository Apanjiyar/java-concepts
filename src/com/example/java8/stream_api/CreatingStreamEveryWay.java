package src.com.example.java8.stream_api;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 04 Creating Streams in Every Way
 * This class demonstrates various ways to create Streams in Java 8 and later.
 * It covers creating streams from collections, arrays, factory methods, infinite streams, primitive ranges, and using Stream.Builder.
 * Each method shows multiple approaches to create streams with different sources and techniques.
 * This serves as a comprehensive reference for stream creation in Java.
 */
public class CreatingStreamEveryWay {
    public static void main(String[] args) {
        streamFromCollection();
        System.out.println("\n--- Stream from Arrays ---\n");
        streamFromArray();
        System.out.println("\n--- Stream from Factory Methods ---\n");
        streamFromFactoryMethods();
        System.out.println("\n--- Infinite Streams ---\n");
        streamFromInfiniteStreams();
        System.out.println("\n--- Stream from Primitive Ranges ---\n");
        streamFromPrimitiveRanges();
        System.out.println("\n--- Stream from Builder ---\n");
        streamFromBuilder();
    }

    /**
     * There are 6+ ways to create a Stream from a Collection:
     * 1. From List using .stream()
     * 2. From List using .parallelStream()
     * 3. From Set using .stream()
     * 4. From Queue/Deque using .stream()
     * 5. From Map.entrySet() using .stream()
     * 6. From Map.keySet() using .stream()
     * 7. From Map.values() using .stream()
     */
    private static void streamFromCollection(){

        // Way 1: Stream from List
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> fromList = list.stream();
        System.out.println("Stream from List: " + fromList.count());

        // Way 2: Parallel Stream from List
        list = Arrays.asList("a", "b", "c");
        Stream<String> parallelStream = list.parallelStream();
        System.out.println("Parallel Stream from List: " + parallelStream.count());

        // Way 3: Stream from Set
        Set<String> set = new HashSet<>(list);
        Stream<String> fromSet = set.stream();
        System.out.println("Stream from Set: " + fromSet.count());

        // Way 4: Parallel Stream from Set
        Stream<String> parallelFromSet = set.parallelStream();
        System.out.println("Parallel Stream from Set: " + parallelFromSet.count());

        // Way 5: Stream from Queue/Deque
        Queue<String> queue = new LinkedList<>(list);
        Stream<String> fromQueue = queue.stream();
        System.out.println("Stream from Queue: " + fromQueue.count());

        // Way 6: Stream from Map - entrySet()
        Map<String, Integer> map = new HashMap<>();
        map.put("x", 10);
        map.put("y", 20);
        map.put("z", 30);

        Stream<Map.Entry<String, Integer>> fromEntrySet = map.entrySet().stream();
        System.out.println("Stream from Map.entrySet(): " + fromEntrySet.count());

        // Way 7: Stream from Map - keySet()
        Stream<String> fromKeySet = map.keySet().stream();
        System.out.println("Stream from Map.keySet(): " + fromKeySet.count());

        // Way 8: Stream from Map - values()
        Stream<Integer> fromValues = map.values().stream();
        System.out.println("Stream from Map.values(): " + fromValues.count());
    }

    /**
     * There are 6+ ways to create a Stream from an Array:
     * 1. From Object Array using Arrays.stream()
     * 2. From primitive int[] using Arrays.stream()
     * 3. From primitive long[] using Arrays.stream()
     * 4. From primitive double[] using Arrays.stream()
     * 5. From Object Array using Stream.of()
     * 6. From Object Array using Stream.of() with varargs
     * 7. From Object Array using Arrays.stream() with range
     * 8. Using Stream.Builder
     */
    private static void streamFromArray(){

        // Way 1: Stream from Object Array using Arrays.stream()
        String[] stringArray = {"a", "b", "c", "d"};
        Stream<String> fromStringArray = Arrays.stream(stringArray);
        System.out.println("Stream from String Array (Arrays.stream()): " + fromStringArray.count());

        // Way 2: Stream from int[] using Arrays.stream()
        int[] intArray = {1, 2, 3, 4, 5};
        IntStream fromIntArray = Arrays.stream(intArray);
        System.out.println("Stream from int[] (Arrays.stream()): " + fromIntArray.count());

        // Way 3: Stream from long[] using Arrays.stream()
        long[] longArray = {10L, 20L, 30L, 40L};
        LongStream fromLongArray = Arrays.stream(longArray);
        System.out.println("Stream from long[] (Arrays.stream()): " + fromLongArray.count());

        // Way 4: Stream from double[] using Arrays.stream()
        double[] doubleArray = {1.1, 2.2, 3.3};
        DoubleStream fromDoubleArray = Arrays.stream(doubleArray);
        System.out.println("Stream from double[] (Arrays.stream()): " + fromDoubleArray.count());

        // Way 5: Stream from Object Array using Stream.of()
        Stream<String> fromStreamOf = Stream.of(stringArray);
        System.out.println("Stream from String Array (Stream.of()): " + fromStreamOf.count());

        // Way 6: Stream from Object Array using Stream.of() with varargs
        Stream<String> fromStreamOfVarargs = Stream.of("x", "y", "z");
        System.out.println("Stream from varargs (Stream.of()): " + fromStreamOfVarargs.count());

        // Way 7: Stream from Object Array using Arrays.stream() with range
        stringArray = new String[]{"a", "b", "c", "d", "e"};
        Stream<String> fromRange = Arrays.stream(stringArray, 1, 4); // index 1 to 3 (exclusive end)
        System.out.println("Stream from Array range (1 to 4): " + fromRange.count());

        // Way 8: Using Stream.Builder
        Stream<String> fromBuilder = Stream.<String>builder()
                .add("first")
                .add("second")
                .add("third")
                .build();
        System.out.println("Stream from Stream.Builder: " + fromBuilder.count());
    }

    /**
     * There are 9+ ways to create a Stream using Factory Methods:
     * 1. Stream.empty() - Creates an empty stream
     * 2. Stream.of() - Creates stream from varargs
     * 3. Stream.generate() - Creates infinite stream using a supplier
     * 4. Stream.iterate() - Creates infinite stream with initial value and function
     * 5. Stream.concat() - Concatenates two streams
     * 6. IntStream.range() - Creates stream of integers in range (exclusive end)
     * 7. IntStream.rangeClosed() - Creates stream of integers in range (inclusive end)
     * 8. LongStream.range() - Creates stream of longs in range
     * 9. Stream.of() with custom objects
     */
    private static void streamFromFactoryMethods(){

        // Way 1: Stream.empty()
        Stream<String> emptyStream = Stream.empty();
        System.out.println("Empty Stream: " + emptyStream.count());

        // Way 2: Stream.of() - with varargs
        Stream<String> ofStream = Stream.of("apple", "banana", "cherry");
        System.out.println("Stream.of() with varargs: " + ofStream.count());

        // Way 3: Stream.generate() - Creates infinite stream (limited to 5 elements)
        Stream<String> generatedStream = Stream.generate(() -> "generated").limit(5);
        System.out.println("Stream.generate() with limit(5): " + generatedStream.count());

        // Way 4: Stream.iterate() - Creates infinite stream with initial value and function
        Stream<Integer> iteratedStream = Stream.iterate(0, n -> n + 2).limit(5); // 0, 2, 4, 6, 8
        System.out.println("Stream.iterate(0, n -> n + 2) with limit(5): " + iteratedStream.count());

        // Way 5: Stream.concat() - Concatenates two streams
        Stream<String> stream1 = Stream.of("a", "b");
        Stream<String> stream2 = Stream.of("c", "d");
        Stream<String> concatenated = Stream.concat(stream1, stream2);
        System.out.println("Stream.concat() of two streams: " + concatenated.count());

        // Way 6: IntStream.range() - range (exclusive end)
        IntStream rangeStream = IntStream.range(0, 5); // 0,1,2,3,4
        System.out.println("IntStream.range(0, 5): " + rangeStream.count());

        // Way 7: IntStream.rangeClosed() - range (inclusive end)
        IntStream rangeClosedStream = IntStream.rangeClosed(0, 5); // 0,1,2,3,4,5
        System.out.println("IntStream.rangeClosed(0, 5): " + rangeClosedStream.count());

        // Way 8: LongStream.range() - long range
        LongStream longRangeStream = LongStream.range(100, 105); // 100,101,102,103,104
        System.out.println("LongStream.range(100, 105): " + longRangeStream.count());

        // Way 9: Stream.of() with custom objects
        class Person {
            final String name;
            Person(String name) { this.name = name; }
        }
        Stream<Person> personStream = Stream.of(
                new Person("Alice"),
                new Person("Bob"),
                new Person("Charlie")
        );
        System.out.println("Stream.of() with custom objects: " + personStream.count());
    }

    /**
     * There are 8+ ways to create Infinite Streams:
     * NOTE: Infinite streams MUST be terminated with limit(), takeWhile(), etc.
     * 1. Stream.generate() with Supplier
     * 2. Stream.iterate() with initial value and function
     * 3. Stream.iterate() with predicate (Java 9+)
     * 4. IntStream.iterate() (Java 9+)
     * 5. LongStream.iterate() (Java 9+)
     * 6. DoubleStream.generate()
     * 7. Using counter with Stream.generate()
     * 8. Using random with Stream.generate()
     */
    private static void streamFromInfiniteStreams(){

        // Way 1: Stream.generate() with Supplier - generates same value infinitely
        Stream<String> infiniteGenerate = Stream.generate(() -> "value").limit(5);
        System.out.println("Stream.generate() with limit(5): " + infiniteGenerate.count());

        // Way 2: Stream.iterate() with initial value and function
        Stream<Integer> infiniteIterate = Stream.iterate(1, n -> n * 2).limit(6); // 1, 2, 4, 8, 16, 32
        System.out.println("Stream.iterate(1, n -> n * 2) with limit(6): " + infiniteIterate.count());

        // Way 3: Stream.iterate() with predicate (Java 9+) - terminates when predicate is false
        Stream<Integer> infiniteIterateWithPredicate = Stream.iterate(1, n -> n <= 100, n -> n * 2);
        System.out.println("Stream.iterate(1, n -> n <= 100, n -> n * 2): " + infiniteIterateWithPredicate.count());

        // Way 4: IntStream.iterate() with initial value and function (Java 9+)
        IntStream infiniteIntStream = IntStream.iterate(0, n -> n + 3).limit(5); // 0, 3, 6, 9, 12
        System.out.println("IntStream.iterate(0, n -> n + 3) with limit(5): " + infiniteIntStream.count());

        // Way 5: LongStream.iterate() with initial value and function (Java 9+)
        LongStream infiniteLongStream = LongStream.iterate(1L, n -> n * 2).limit(10); // 1, 2, 4, 8, ...
        System.out.println("LongStream.iterate(1L, n -> n * 2) with limit(10): " + infiniteLongStream.count());

        // Way 6: DoubleStream.generate() - generates random doubles infinitely
        DoubleStream infiniteDoubleStream = DoubleStream.generate(Math::random).limit(5);
        System.out.println("DoubleStream.generate(Math::random) with limit(5): " + infiniteDoubleStream.count());

        // Way 7: Using counter with Stream.generate() - stateful generation
        class Counter {
            int count = 0;
        }
        Counter counter = new Counter();
        Stream<Integer> counterStream = Stream.generate(() -> ++counter.count).limit(5); // 1, 2, 3, 4, 5
        System.out.println("Stream.generate() with counter with limit(5): " + counterStream.count());

        // Way 8: Using random with Stream.generate() - generates random integers
        Stream<Integer> randomStream = Stream.generate(() -> (int)(Math.random() * 100)).limit(5);
        System.out.println("Stream.generate() with random integers with limit(5): " + randomStream.count());

        // Way 9: Using takeWhile() to terminate infinite stream (Java 9+)
        // Alternatively use three-arg iterate() instead
        Stream<Integer> predicateStream = Stream.iterate(1, n -> n <= 5, n -> n + 1); // 1, 2, 3, 4, 5
        System.out.println("Stream.iterate() with predicate (n -> n <= 5): " + predicateStream.count());

        // Way 10: Using filter and findFirst() to get first matching element from infinite stream
        Stream<Integer> filterStream = Stream.iterate(0, n -> n + 1).filter(n -> n % 2 == 0); // even numbers
        int firstEven = filterStream.findFirst().orElse(-1);
        System.out.println("Stream.iterate() with filter (first even): " + firstEven);
    }

    /**
     * There are 9+ ways to create Streams from Primitive Ranges:
     * 1. IntStream.range() - exclusive end
     * 2. IntStream.rangeClosed() - inclusive end
     * 3. LongStream.range() - long range, exclusive end
     * 4. LongStream.rangeClosed() - long range, inclusive end
     * 5. IntStream.range() with step (using iterate)
     * 6. LongStream.range() with step
     * 7. IntStream.range() for reverse order
     * 8. Combining multiple ranges with concat()
     * 9. IntStream.range() with filter
     */
    private static void streamFromPrimitiveRanges(){

        // Way 1: IntStream.range() - exclusive end (0 to 4)
        IntStream range = IntStream.range(0, 5);
        System.out.println("IntStream.range(0, 5): " + range.count());

        // Way 2: IntStream.rangeClosed() - inclusive end (0 to 5)
        IntStream rangeClosed = IntStream.rangeClosed(0, 5);
        System.out.println("IntStream.rangeClosed(0, 5): " + rangeClosed.count());

        // Way 3: LongStream.range() - long range, exclusive end (100 to 109)
        LongStream longRange = LongStream.range(100L, 110L);
        System.out.println("LongStream.range(100L, 110L): " + longRange.count());

        // Way 4: LongStream.rangeClosed() - long range, inclusive end (100 to 105)
        LongStream longRangeClosed = LongStream.rangeClosed(100L, 105L);
        System.out.println("LongStream.rangeClosed(100L, 105L): " + longRangeClosed.count());

        // Way 5: IntStream.range() with step using iterate() (0, 2, 4, 6, 8)
        IntStream rangeWithStep = IntStream.iterate(0, n -> n < 10, n -> n + 2);
        System.out.println("IntStream.iterate(0, n < 10, n + 2): " + rangeWithStep.count());

        // Way 6: LongStream.range() with step using iterate() (10, 15, 20, 25...)
        LongStream longRangeWithStep = LongStream.iterate(10L, n -> n < 50L, n -> n + 5);
        System.out.println("LongStream.iterate(10L, n < 50L, n + 5): " + longRangeWithStep.count());

        // Way 7: IntStream.range() for reverse order (using negative step)
        IntStream reverseRange = IntStream.iterate(10, n -> n > 0, n -> n - 1);
        System.out.println("IntStream.iterate(10, n > 0, n - 1) [reverse]: " + reverseRange.count());

        // Way 8: Combining multiple ranges with concat()
        IntStream range1 = IntStream.range(0, 3); // 0, 1, 2
        IntStream range2 = IntStream.range(5, 8); // 5, 6, 7
        IntStream concatenated = IntStream.concat(range1, range2);
        System.out.println("IntStream.concat(range(0,3), range(5,8)): " + concatenated.count());

        // Way 9: IntStream.range() with filter (even numbers 0 to 9)
        IntStream filtered = IntStream.range(0, 10).filter(n -> n % 2 == 0);
        System.out.println("IntStream.range(0, 10).filter(even): " + filtered.count());

        // Way 10: IntStream.range() with map (square numbers)
        IntStream mapped = IntStream.range(1, 6).map(n -> n * n); // 1, 4, 9, 16, 25
        System.out.println("IntStream.range(1, 6).map(n -> n*n): " + mapped.count());

        // Way 11: LongStream.range() with custom processing
        LongStream customRange = LongStream.rangeClosed(1L, 5L);
        long sum = customRange.sum(); // Sum of 1+2+3+4+5 = 15
        System.out.println("LongStream.rangeClosed(1L, 5L).sum(): " + sum);

        // Way 12: IntStream.range() to get average
        IntStream averageRange = IntStream.rangeClosed(1, 5); // 1, 2, 3, 4, 5
        double average = averageRange.average().orElse(0);
        System.out.println("IntStream.rangeClosed(1, 5).average(): " + average);
    }

    /**
     * There are 8+ ways to create Streams using Stream.Builder:
     * 1. Stream.Builder with generic type - basic usage
     * 2. Stream.builder() with typed elements
     * 3. Stream.Builder with multiple add() calls
     * 4. Stream.Builder using builder() static method
     * 5. Stream.Builder with conditional additions
     * 6. Stream.Builder with loop to add elements
     * 7. Stream.Builder with custom objects
     * 8. Stream.Builder with method reference style
     */
    private static void streamFromBuilder(){

        // Way 1: Stream.Builder with generic type - basic usage
        Stream.Builder<String> builder1 = Stream.builder();
        builder1.add("apple");
        builder1.add("banana");
        builder1.add("cherry");
        Stream<String> stream1 = builder1.build();
        System.out.println("Stream.Builder with 3 elements: " + stream1.count());

        // Way 2: Stream.builder() with typed elements - fluent chaining
        Stream<String> stream2 = Stream.<String>builder()
                .add("one")
                .add("two")
                .add("three")
                .build();
        System.out.println("Stream.builder() with chaining: " + stream2.count());

        // Way 3: Stream.Builder with multiple add() calls
        Stream.Builder<Integer> builder3 = Stream.builder();
        builder3.add(10);
        builder3.add(20);
        builder3.add(30);
        builder3.add(40);
        builder3.add(50);
        Stream<Integer> stream3 = builder3.build();
        System.out.println("Stream.Builder with 5 elements: " + stream3.count());

        // Way 4: Stream.Builder using builder() static method - alternative syntax
        Stream<String> stream4 = Stream.<String>builder()
                .add("alpha")
                .add("beta")
                .add("gamma")
                .build();
        System.out.println("Stream.builder() with type parameter: " + stream4.count());

        // Way 5: Stream.Builder with conditional additions
        Stream.Builder<String> builder5 = Stream.builder();
        String[] fruits = {"apple", "banana", "cherry", "date", "elderberry"};
        for (String fruit : fruits) {
            if (fruit.length() > 4) { // only add fruits with length > 4
                builder5.add(fruit);
            }
        }
        Stream<String> stream5 = builder5.build();
        System.out.println("Stream.Builder with conditional adds: " + stream5.count());

        // Way 6: Stream.Builder with loop to add elements
        Stream.Builder<Integer> builder6 = Stream.builder();
        for (int i = 1; i <= 5; i++) {
            builder6.add(i * 10); // 10, 20, 30, 40, 50
        }
        Stream<Integer> stream6 = builder6.build();
        System.out.println("Stream.Builder with loop: " + stream6.count());

        // Way 7: Stream.Builder with custom objects
        class Person {
            final String name;
            final int age;
            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
            @Override
            public String toString() { return name + "(" + age + ")"; }
        }
        Stream<Person> stream7 = Stream.<Person>builder()
                .add(new Person("Alice", 25))
                .add(new Person("Bob", 30))
                .add(new Person("Charlie", 35))
                .build();
        System.out.println("Stream.Builder with custom objects: " + stream7.count());

        // Way 8: Stream.Builder with dynamic list conversion
        List<String> colors = Arrays.asList("red", "green", "blue", "yellow");
        Stream.Builder<String> builder8 = Stream.builder();
        colors.forEach(builder8::add); // using method reference
        Stream<String> stream8 = builder8.build();
        System.out.println("Stream.Builder with forEach and method reference: " + stream8.count());

        // Way 9: Stream.Builder with Map.Entry objects
        Stream<Map.Entry<String, Integer>> stream9 = Stream.<Map.Entry<String, Integer>>builder()
                .add(new AbstractMap.SimpleEntry<>("key1", 100))
                .add(new AbstractMap.SimpleEntry<>("key2", 200))
                .add(new AbstractMap.SimpleEntry<>("key3", 300))
                .build();
        System.out.println("Stream.Builder with Map.Entry: " + stream9.count());

        // Way 10: Stream.Builder with ArrayList conversion
        ArrayList<Double> numbers = new ArrayList<>();
        numbers.add(1.5);
        numbers.add(2.5);
        numbers.add(3.5);
        Stream.Builder<Double> builder10 = Stream.builder();
        numbers.forEach(builder10::add);
        Stream<Double> stream10 = builder10.build();
        System.out.println("Stream.Builder from ArrayList: " + stream10.count());
    }
}