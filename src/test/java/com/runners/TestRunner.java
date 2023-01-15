package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
        glue = "com/stepdefs",
        tags = "@addLicense",
        plugin = {"pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber.html",
                "rerun:target/FailedTests.txt",
        })

public class TestRunner extends AbstractTestNGCucumberTests {
}
