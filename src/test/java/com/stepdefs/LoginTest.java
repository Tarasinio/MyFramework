package com.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.testbase.PageInitializer.*;
//import static com.testbase.PageInitializer.navigationBar;
import static com.utils.CommonMethods.*;

public class LoginTest {

    public static String userName;
    @And("login as {string} user")
    public void loginAsUser(String userName) {
        LoginTest.userName = userName;
        isDisplayed(loginPage.welcomeLogo);
        enterCorrectCredAtLoginPage(userName);
        clickAndWaitTillElementPresent(loginPage.signInBtn, homePage.welcomeMsg);
        jsWaitTillPageLoaded();
    }

//    @Then("click on Sign Out from Acc Menu and verify user is signed out")
//    public void click_on_Sign_Out_from_Acc_Menu_and_verify_user_is_signed_out() {
//        clickAndPause(navigationBar.userMenuDdBtn, 1000);
//        clickAndPause(navigationBar.userMenuDdSignOutBtn, 1000);
//        waitForVisibility(loginPage.industriousWelcomeLogo);
//    }
}