package com.testbase;

import com.pages.*;
import com.pages.HomePage;
import com.pages.UserProfileManu.QualificationsPage;
import com.pages.UserProfileManu.UserProfileManuBtns;

public class PageInitializer extends BaseClass {

    public static HomePage homePage;
    public static LoginPage loginPage;

    public static QualificationsPage qualificationsPage;
    public static UserProfileManuBtns userProfileManu;
    public static void initializePageObjects() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        qualificationsPage = new QualificationsPage();
        userProfileManu = new UserProfileManuBtns();
    }
}