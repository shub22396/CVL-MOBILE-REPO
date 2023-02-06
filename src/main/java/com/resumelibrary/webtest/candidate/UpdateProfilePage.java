package com.resumelibrary.webtest.candidate;

import com.resumelibrary.utilities.Utility;
import com.resumelibrary.webtest.misc.MiscPage;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UpdateProfilePage extends Utility {

    public UpdateProfilePage()
    {
        PageFactory.initElements(getThreadDriver(),this);
    }
    private static final Logger logger = LogManager.getLogger(UpdateProfilePage.class);

    @FindBy(id = "zip_code_ux")
    WebElement ZipCode;
//    @FindBy(xpath = "//*[@id=\"cand-modify-form\"]/div[8]/button")
    @FindBy(xpath ="//*[@id='cand-modify-form']/div[8]/button" )
    WebElement SaveChangesButton;
    @FindBy(xpath ="//*[text()='Main Skills and Languages']/..//button" )
    WebElement ClosesButton;
    @FindBy(id = "phone")
    WebElement PhoneNumber;
    @FindBy(id = "latest_job_title")
    WebElement LatestJobTitle;
    @FindBy(id = "desired_job_title")
    WebElement DesiredJobTitle;
    @FindBy(xpath = "//button[text()='Save changes']")
    WebElement saveChangesButton;
    @FindBy(id = "first_name")
    WebElement FirstName;
    @FindBy(id = "last_name")
    WebElement LastName;
    @FindBy(id = "set_password")
    WebElement SetPassword;
    @FindBy(id = "upload_resume_btn")
    WebElement UploadResumeButton;
    @FindBy(id = "update-resume-submit")
    WebElement UpdateResumeSubmit;
    @FindBy(id = "education_level")
    WebElement EducationLevel;
    @FindBy(id = "work_experience")
    WebElement YearsOfExperience;
    @FindBy(id = "travel_radius")
    WebElement PreparedToTravel;
    @FindBy(xpath = "//label[@for='relocate.1']")
    WebElement WillingTorelocateYes;
    @FindBy(xpath = "//label[@for='relocate.0']")
    WebElement WillingTorelocateNo;
    @FindBy(xpath = "//label[@for='driving_licence.1']")
    WebElement DriversLicenseYes;
    @FindBy(xpath = "//label[@for='driving_licence.0']")
    WebElement DriversLicenseNo;
    @FindBy(id = "salary_expectation_from")
    WebElement DesiredSalaryFrom;
    @FindBy(id = "salary_expectation_to")
    WebElement DesiredSalaryTo;
    @FindBy(id = "eligibility_status")
    WebElement AuthorizationToWorkInTheUS;
    @FindBy(id = "eligibility_status_radio.3")
    WebElement AuthorizationToWorkInTheUSMobile;
    @FindBy(xpath="(//button[@class='popup-close popup-x'])[1]")
    WebElement viewResumePopup;

    public void enterZipCode(String zipcode) {
        logger.info("Zip code entered is : " + zipcode);
        fillWebElement(ZipCode, zipcode);
        ZipCode.sendKeys(Keys.ENTER);
    }

    public void enterFirstName(String firstName) {
        logger.info("first name entered is : " + firstName);
        fillWebElement(FirstName, firstName);
    }

    public void SaveChangesButton() {
        logger.info("Click on Save Changes Button  " );
      //  clickOnElement(SaveChangesButton);
        SaveChangesButton.click();
        waitFor(2);
    }
    public void ClickOnClose() {
        logger.info("Click on Close Button  " );

        ClosesButton.click();
        waitFor(2);
    }

    public void enterPhoneNumber(String phone) {
        logger.info("phone number entered is : " + phone);
        fillWebElement(PhoneNumber, phone);
    }

    public void enterLatestJobTitle(String latestJobTitle) {
        logger.info("latest job tile entered is : " + latestJobTitle);
        fillWebElement(LatestJobTitle, latestJobTitle);
    }

    public void enterDesiredJobTitle(String desiredJobTitle) {
        logger.info("desired job tile entered is : " + desiredJobTitle);
        DesiredJobTitle.clear();
        DesiredJobTitle.sendKeys(desiredJobTitle);
        //DesiredJobTitle.sendKeys(Keys.TAB);
       // DesiredJobTitle.sendKeys(Keys.TAB);
       // DesiredJobTitle.sendKeys(Keys.TAB);
       // fillWebElement(DesiredJobTitle, desiredJobTitle);
        waitFor(4);
    }
    public void clickOnSaveChanges() {
        logger.info("clicking on save changes button");
       // System.out.println("page source ------->"+((AndroidDriver) getThreadDriver()).getPageSource());

        ((AndroidDriver) getThreadDriver()).context("NATIVE_APP");
        if (checkElementPresence("//*[@text='Save changes']")) {
            getThreadDriver().findElement(By.xpath("//*[@text='Save changes']")).click();
        }

        ((AndroidDriver) getThreadDriver()).context("CHROMIUM");


    }


    public void enterSkills(String skills) {
        logger.info("skills entered  is : " + skills);
      //  String javaScript = "document.getElementById('skills-input').value='" + skills + "'";
        //JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getThreadDriver();
        //javascriptExecutor.executeScript(javaScript);
     //   javascriptExecutor.executeScript("document.getElementById('skills-input').click()");
        ((JavascriptExecutor) getThreadDriver()).executeScript("arguments[0].scrollIntoView(true);",  getThreadDriver().findElement(By.id("skills-input")));
        getThreadDriver().findElement(By.id("skills-input")).sendKeys(skills);
        waitFor(4);
        getThreadDriver().findElement(By.id("skills-input")).sendKeys(Keys.TAB);
        getThreadDriver().findElement(By.id("skills-input")).sendKeys(Keys.TAB);
    }

    protected void fillWebElement(WebElement webElement, String filedValue) {
        waitUntilElementIsLocated(webElement,15);
        webElement.clear();
        webElement.sendKeys(filedValue);
        webElement.sendKeys(Keys.TAB);
        webElement.sendKeys(Keys.ESCAPE);
    }

    public void clickOnSetPassword() {
        logger.info("Click on Set Password Button  ");
        clickOnElement(SetPassword);
    }

    public void uploadResume() {
        logger.info("Click on Upload Resume Button  " );
        clickOnElement(UploadResumeButton);
    }

    public void clickOnUpdateResumeSubmit() {
        logger.info("Click on Update Resume Submit Button " );
        clickOnElement(UpdateResumeSubmit);
        waitFor(2);
    }
    public void enterLastName(String lastName) {
        logger.info("last name entered is : " + lastName);
        fillWebElement(LastName, lastName);
    }

    public void selectTheFieldEducationLevelWithOnModifyProfilePage(String educationLevel)
    {
            selectByVisibleTextFromDropDown(EducationLevel,educationLevel);
    }
    public void selectTheFieldYearsOfExperienceWithOnModifyProfilePage(String yearsOfExperience)
    {
        selectByVisibleTextFromDropDown(YearsOfExperience,yearsOfExperience);
    }
    public void compareTheLabelTextWith(String labelName, String expectedLabelValue)
    {
        WebElement element = getThreadDriver().findElement(By.xpath("//dt[text()='"+labelName+"']/following-sibling::dd/span"));
        waitUntilElementIsLocated(element,20);
        String actualFieldValue = getTextFromElement(element);
        Assert.assertEquals(actualFieldValue.toLowerCase(),expectedLabelValue.toLowerCase());

    }
    public void selectTheFieldPreparedToTravelWithOnModifyProfilePage(String preparedToTravel)
    {
        selectByVisibleTextFromDropDown(PreparedToTravel,preparedToTravel);
    }

    public void selectTheFieldWillingToRelocateWithOnModifyProfilePage(String willingTorelocate) {
        if(willingTorelocate.toLowerCase().trim().equals("yes".toLowerCase().trim()))
        {
            clickOnElement(WillingTorelocateYes);
        }
        else if(willingTorelocate.toLowerCase().trim().equals("no".toLowerCase().trim()))
        {
            clickOnElement(WillingTorelocateNo);

        }
    }

    public void selectTheFieldDriversLicenseWithOnModifyProfilePage(String driversLicense) {
        if(driversLicense.toLowerCase().trim().equals("yes".toLowerCase().trim()))
        {
            clickOnElement(DriversLicenseYes);
        }
        else if(driversLicense.toLowerCase().trim().equals("no".toLowerCase().trim()))
        {
            clickOnElement(DriversLicenseNo);
        }
    }

    public void selectTheFieldDesiredSalaryFromWithOnModifyProfilePage(String desiredSalaryFrom) {
        selectByVisibleTextFromDropDown(DesiredSalaryFrom,desiredSalaryFrom);

                DesiredSalaryFrom.click();

               waitFor(3);
              getThreadDriver().findElement(By.xpath("//select[@id='salary_expectation_from']/option[text()='"+desiredSalaryFrom+"']")).click();
               waitFor(3);

    }

    public void selectTheFieldDesiredSalaryToWithOnModifyProfilePage(String desiredSalaryTo) {
        DesiredSalaryTo.click();
         waitFor(3);
        getThreadDriver().findElement(By.xpath("//select[@id='salary_expectation_to']/option[text()='"+desiredSalaryTo+"']")).click();
        waitFor(3);

    }

    public void selectTheFieldAuthorizationToWorkInTheUSWithOnModifyProfilePage(String authorizationToWorkInTheUS) {
        selectByVisibleTextFromDropDown(AuthorizationToWorkInTheUS, authorizationToWorkInTheUS);
    }
    public void selectTheFieldAuthorizationToWorkInTheUSWithOnModifyProfilePageForMobile(String authorizationToWorkInTheUS) {
        clickOnElementUsingActions(AuthorizationToWorkInTheUSMobile);
      //  AuthorizationToWorkInTheUSMobile.click();
    }

    public void clickOnViewResumeClosePopupButton()
    {
        viewResumePopup.click();

    }
}