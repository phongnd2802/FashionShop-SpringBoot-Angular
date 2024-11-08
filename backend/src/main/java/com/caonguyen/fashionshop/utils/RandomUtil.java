package com.caonguyen.fashionshop.utils;

import java.util.Random;

public class RandomUtil {

    public static int generateSixDigit() {
        Random rng = new Random(System.nanoTime());
        int result = 100000 + rng.nextInt(900000);
        return result;
    }
}
