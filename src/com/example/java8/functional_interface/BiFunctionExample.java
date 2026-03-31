package com.example.java8.functional_interface;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 05
 */
public class BiFunctionExample {

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<String, String, String> concat = (a, b) -> a + b;

        System.out.println(add.apply(10, 20)); // 30
        System.out.println(concat.apply("Hello", "World")); // HelloWorld

        Function<Integer, Integer> square = n -> n * n;
        System.out.println(add.andThen(square).apply(5, 3)); // 64
    }
}