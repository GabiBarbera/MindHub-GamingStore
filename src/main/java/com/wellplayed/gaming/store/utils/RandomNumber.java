package com.wellplayed.gaming.store.utils;

import java.util.Random;

public class RandomNumber {
    private RandomNumber(){}

    public static Random randomNumber = new Random();

    public static String getRandomNum() {
        int randomNum = randomNumber.nextInt(9000000);
        return "Gaming-store" + String.format("%04d", randomNum);
    }
}