package com.stepdefs;

import com.testbase.BaseClass;
import com.utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before @Deprecated
    public void startTest() {
        BaseClass.setUp();
    }

    @After
    public void endTest(Scenario scenario) {
        byte[] screenshot;
        if (scenario.isFailed()) {
            screenshot = CommonMethods.takeScreenshot("failed/" + scenario.getName());
        } else {
            screenshot = CommonMethods.takeScreenshot("passed/" + scenario.getName());
        }
        scenario.attach(screenshot, "image/png", scenario.getName());

        BaseClass.tearDown();
    }
}