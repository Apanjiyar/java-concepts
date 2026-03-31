package com.example.java8.functional_interface;

import java.util.function.Consumer;

/**
 * 03
 */
public class ConsumerExample {

    public static void main(String[] args) {
        Consumer<String> print = s -> System.out.println("Hello " + s);
        Consumer<String> shout = s -> System.out.println(s.toUpperCase());

        print.accept("Arun"); // Hello Arun
        print.andThen(shout).accept("Ravi");
        // Hello Ravi
        // RAVI
    }
}
