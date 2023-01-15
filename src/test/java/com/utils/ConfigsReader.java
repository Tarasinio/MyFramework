package com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigsReader {

    static Properties prop;

    public static Properties readProperties() {
        // First read the default properties, which should always be there.
        final Properties defaultProperties = ConfigsReader.loadPropertiesFile(Constants.DEFAULT_CONFIGURATION_FILEPATH, true);
        final Properties overriddenProperties = ConfigsReader.loadPropertiesFile(Constants.OVERRIDE_CONFIGURATION_FILEPATH, false);
        // The result is a mix of both, where the git ignored file takes precedence.
        prop = new Properties(defaultProperties);
        prop.putAll(overriddenProperties);
        return prop;
    }

    public static String getPropertyValue(String key) {
        return prop.getProperty(key);
    }

    private static Properties loadPropertiesFile(String resourcePath, boolean required) {
        try (InputStream input = new FileInputStream(resourcePath)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (FileNotFoundException e) {
            if (required) {
                throw new RuntimeException(String.format("required properties file %s not found", resourcePath), e);
            }
            // Otherwise, return empty properties.
            return new Properties();
        } catch (IOException e) {
            throw new RuntimeException(String.format("unexpected exception while loading properties file %s", resourcePath), e);
        }
    }
}
