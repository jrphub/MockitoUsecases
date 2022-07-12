package com.mejrp.cop;

public class LoggerService {

    public void logToOut(String s) {
        System.out.println(s);
    }

    public void logToErr(String s) {
        System.err.println(s);
    }
}