package com.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import static com.utils.CommonMethods.*;

public class UsersProfile {

    public String randomYearsOfExperience;
    @And("navigate to UsersQualifications page")
    public void navigateToUsersQualificationsPage() {
        navigateToURI("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/pim/viewQualifications/empNumber/45154");
        WebElement qualificationsBtn = getElementByExactText(userProfileManu.usersManuList, "Qualifications");
        jsScrollIntoView(qualificationsBtn);
        pause(2000);
        isEnabledAndContainAttr(qualificationsBtn, "class", "selected");
    }
    @And("add user skill {string}")
    public void addUserSkill(String usersSkill) {
        jsScrollIntoView(qualificationsPage.addSkillsBtn);
        clickAndPause(qualificationsPage.addSkillsBtn, 1000);
        jsScrollIntoView(qualificationsPage.addSkillsDialog_SkillsDd);
        waitForClickability(qualificationsPage.addSkillsDialog_SkillsDd);
        selectDdByText(qualificationsPage.addSkillsDialog_SkillsDd, usersSkill);
        //-------enter years of exp and verify value:
        randomYearsOfExperience = String.valueOf(selectRandom(1, 15));
        sendText(qualificationsPage.addSkillsDialog_YearsOfExperience, randomYearsOfExperience);
        assertTrue(qualificationsPage.addSkillsDialog_YearsOfExperience.getAttribute("value").equals(randomYearsOfExperience));
        //-------enter comments and verify:
        sendText(qualificationsPage.addSkillsDialog_Comments, "very professional");
        assertTrue(qualificationsPage.addSkillsDialog_Comments.getAttribute("value").equals("very professional"));
        click(qualificationsPage.addSkillsDialog_AddBtn);
    }
    @And("add user license {string}")
    public void addUserLicense(String typeLicense) {
        //------------click dropDown and choose type license
        jsScrollIntoView(qualificationsPage.addLicenseBtn);
        clickAndPause(qualificationsPage.addLicenseBtn, 1000);
        jsScrollIntoView(qualificationsPage.addLicenseDialog_LicenseDd);
        selectDdByText(qualificationsPage.addLicenseDialog_LicenseDd, typeLicense);
        //------------enter License Number
        sendText(qualificationsPage.addLicenseDialog_LicenseNumber, "322223");
        assertTrue(qualificationsPage.addLicenseDialog_LicenseNumber.getAttribute("value").equals("322223"));
        click(qualificationsPage.addLicenseDialog_AddBtn);

    }
    @Then("verify skill {string} is added")
    public void verifySkillIsAdded(String usersSkill) {
        WebElement userSkill = getElementByContainText(qualificationsPage.skillsList, new String[]{usersSkill, randomYearsOfExperience});
        WebElement skillName = elementAsList_ByTextAndXpath(userSkill, usersSkill, "*");
        isDisplayed(skillName);
        WebElement yearsOfExp = elementAsList_ByTextAndXpath(userSkill, randomYearsOfExperience, "*");
        isDisplayed(yearsOfExp);
    }


}
