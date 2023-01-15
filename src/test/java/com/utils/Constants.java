package com.utils;

import java.util.HashMap;

public class Constants {

    public static final int IMPLICIT_WAIT = 10;

    public static final int EXPLICIT_WAIT = 20;

    public static final String DEFAULT_CONFIGURATION_FILEPATH = System.getProperty("user.dir") + "/src/test/resources/configs/default.properties";

    public static final String OVERRIDE_CONFIGURATION_FILEPATH = System.getProperty("user.dir") + "/src/test/resources/configs/configuration.properties";

    public static final String SCREENSHOT_FILEPATH = System.getProperty("user.dir") + "/target/screenshots/";

    public static final String[] LOCATIONS = {"Some Location"};

    public static final HashMap<String, String> USERS_FULL_NAME_LIST = new HashMap<>();

    static {
        USERS_FULL_NAME_LIST.put("SomeUser", "FirstName LastName");
    }
}
