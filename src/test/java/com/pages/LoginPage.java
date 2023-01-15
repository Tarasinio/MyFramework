package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.testbase.BaseClass.driver;

public class LoginPage {

    @FindBy(id = "divLogo")
    public WebElement welcomeLogo;

    @FindBy(id = "txtUsername")
    public WebElement emailField;

    @FindBy(id = "txtPassword")
    public WebElement passwordField;

    @FindBy(name = "Submit")
    public WebElement signInBtn;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
}