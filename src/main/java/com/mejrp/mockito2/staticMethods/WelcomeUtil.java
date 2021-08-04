package com.mejrp.mockito2.staticMethods;

/**
 * static methods could be mocked only using an inline way,
 * mockito-inline dependency is added in pom.xml
 * mockito-core of mockito-inline is used, other mockito-core version is removed from pom.xml
 * org.mockito.plugins.MockMaker file is used with one line mock-maker-inline
 */
public final class WelcomeUtil {

    public static String generateWelcome(String name) {
        return String.format("Welcome %s", name);
    }

    private WelcomeUtil() {
    }

    public static String generateWelcomeOutput(String name) {
        //We want to mock WelcomeHelper and want to skip instance creation
        WelcomeHelper helper = new WelcomeHelper(name);
        return helper.getMessage();
    }
}