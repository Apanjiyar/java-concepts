package com.example.java8.functional_interface;

import java.util.Random;
import java.util.function.Supplier;

/**
 * 04
 */
public class SupplierExample {

    public static void main(String[] args) {
        Supplier<Double> randomValue = () -> Math.random();
        Supplier<Integer> randomInt = () -> new Random().nextInt(50);

        System.out.println(randomValue.get()); // 0.123
        System.out.println(randomInt.get());   // 23
    }
}
