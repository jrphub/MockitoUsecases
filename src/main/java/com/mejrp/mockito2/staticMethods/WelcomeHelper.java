package com.mejrp.mockito2.staticMethods;

public class WelcomeHelper {
    private String name;

    WelcomeHelper(String name){
        this.name = name;
    }

    public String getMessage() {
        return "Hey " + this.name;
    }
}
