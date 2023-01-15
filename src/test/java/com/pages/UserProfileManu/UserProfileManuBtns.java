package com.pages.UserProfileManu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.testbase.BaseClass.driver;
public class UserProfileManuBtns {
    @FindBy(xpath = "//ul[@id = 'sidenav']/li")
    public List<WebElement> usersManuList;
    public UserProfileManuBtns() {
        PageFactory.initElements(driver, this);
    }
}
