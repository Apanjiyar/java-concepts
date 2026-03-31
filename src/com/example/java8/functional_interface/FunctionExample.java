package com.example.java8.functional_interface;

import java.util.function.Function;

/**
 * 02
 */
public class FunctionExample {

    public static void main(String[] args) {
        Function<String, Integer> length = s -> s.length();
        Function<Integer, Integer> square = n -> n * n;

        System.out.println(length.apply("Arun")); // 4
        System.out.println(length.andThen(square).apply("Hello")); // 25
        System.out.println(square.compose(length).apply("Hello")); // 25
        System.out.println(Function.identity().apply("Java17")); // Java17
    }
}
