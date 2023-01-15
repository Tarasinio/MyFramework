package com.testbase;

import com.utils.ConfigsReader;
import com.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseClass.class);
    public static WebDriver driver;
    public static String urlInUse;

    @Deprecated
    public static void setUp() {
        ConfigsReader.readProperties();
        String headless;

        switch (ConfigsReader.getPropertyValue("browser").toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                headless = ConfigsReader.getPropertyValue("headless");
                if (headless.equals("true")) {
                    chromeOptions.setHeadless(true);
                    chromeOptions.addArguments("--window-size=1920,1200");
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--allowed-ips");
                    driver = new ChromeDriver(chromeOptions);
                } else {
                    chromeOptions.addArguments("--disable-gpu");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().maximize();
                }
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                headless = ConfigsReader.getPropertyValue("headless");
                if (headless.equals("true")) {
                    firefoxOptions.setHeadless(true);
                    driver = new FirefoxDriver(firefoxOptions);
                    driver.manage().window().setSize(new Dimension(1920, 1200));
                } else {
                    driver = new FirefoxDriver(firefoxOptions);
                    driver.manage().window().maximize();
                }
                break;
            default:
                throw new RuntimeException("Invalid Browser");
        }
        driver.manage().deleteAllCookies();
        urlInUse = ConfigsReader.getPropertyValue("prod");
        driver.get(urlInUse);
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        PageInitializer.initializePageObjects();
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}