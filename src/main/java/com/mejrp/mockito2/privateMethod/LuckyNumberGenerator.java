package com.mejrp.mockito2.privateMethod;

public class LuckyNumberGenerator {
    public int getLuckyNumber(String name) {
        saveIntoDatabase(name);
        if (name == null) {
            return getDefaultLuckyNumber();
        }
        return getComputedLuckyNumber(name.length());
    }

    //Method with Argument and return value
    private int getComputedLuckyNumber(int length) {
        return 10;
    }

    //Method with no argument
    private int getDefaultLuckyNumber() {
        return 1;
    }

    //to check invocation of a Method
    private void saveIntoDatabase(String name) {
        System.out.println("Saving name into Database");
    }
}
