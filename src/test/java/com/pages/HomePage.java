package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.testbase.BaseClass.driver;

public class HomePage {

    @FindBy(xpath = "/html/body/div[1]/div[1]/div/div[2]/button/span")
    public WebElement MultipleLicensedOfficesSignCloseBtn;

    @FindBy(xpath = "//*[@alt= 'OrangeHRM']")
    public WebElement welcomeMsg;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }
}