package com.pages.UserProfileManu;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.testbase.BaseClass.driver;

public class QualificationsPage {

    @FindBy(id = "addSkill")
    public WebElement addSkillsBtn;

    @FindBy(id = "delSkill")
    public WebElement delSkillsBtn;
    @FindBy(xpath = "//select[@id='skill_code']")
    public WebElement addSkillsDialog_SkillsDd;
    @FindBy(id = "skill_years_of_exp")
    public WebElement addSkillsDialog_YearsOfExperience;
    @FindBy(id = "skill_comments")
    public WebElement addSkillsDialog_Comments;
    @FindBy(id = "btnSkillSave")
    public WebElement addSkillsDialog_AddBtn;
    @FindBy(xpath = "//form[@id = 'frmDelSkill']//tbody/tr")
    public List<WebElement> skillsList;
    @FindBy(id = "addLicense")
    public WebElement addLicenseBtn;
    @FindBy(xpath = "//select[@id='license_code']")
    public WebElement addLicenseDialog_LicenseDd;
    @FindBy(id = "license_license_no")
    public WebElement addLicenseDialog_LicenseNumber;

    @FindBy(id = "btnLicenseSave")
    public WebElement addLicenseDialog_AddBtn;
    public QualificationsPage() {
        PageFactory.initElements(driver, this);
    }
}
