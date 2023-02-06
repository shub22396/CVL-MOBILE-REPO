package com.resumelibrary.webtest.candidate;

import com.resumelibrary.utilities.Utility;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage extends Utility {

    public RegistrationPage() {
        PageFactory.initElements(getThreadDriver(), this);
    }

    private static final Logger logger = LogManager.getLogger(RegistrationPage.class);

    @FindBy(id = "email")
    WebElement EmailAddressField;
    @FindBy(id = "first_name")
    WebElement FirstNameField;
    @FindBy(id = "last_name")
    WebElement LastNameField;
    @FindBy(id = "password")
    WebElement PasswordField;
    @FindBy(id = "latest_job_title")
    WebElement LatestJobTitleField;
    @FindBy(id = "zip_code_ux")
    WebElement ZipCodeField;
    //@FindBy(id = "register_now_button")
    @FindBy(xpath = "//*[@id='register_now_button']")
    WebElement RegisterButton;
    @FindBy(id = "phone")
    WebElement PhoneNumberField;
    @FindBy(id = "desired_job_title")
    WebElement DesiredJobTitle;
    @FindBy(id = "salary_expectation_from")
    WebElement SalaryFromDropdown;
    @FindBy(name = "salary_expectation[1]")
    WebElement SalaryToDropdown;
    @FindBy(id = "add-job-title-link")
    WebElement AddJobTitleLink;
    @FindBy(id = "job_alerts_1")
    WebElement JobTitleField;
    @FindBy(id = "register-stage2-submit")
    WebElement CompleteButton;
    @FindBy(id = "country_id")
    WebElement CountryDropdown;
    @FindBy(id = "city")
    WebElement CityField;
    @FindBy(id = "home_town")
    WebElement HomeTown;
    @FindBy(id = "resume_check")
    WebElement ResumeCheckCheckbox;
    @FindBy(id = "upload_resume_toggle1")
    WebElement UploadYourResumeOption;
    @FindBy(id = "register_confirm_button")
    WebElement RegisterAndConfirmButton;
    @FindBy(id = "register-popup-close-btn")
    WebElement RegisterPopupCloseButton;
    @FindBy(id = "privacy-policy-link")
    WebElement PrivacyPolicyLink;
    @FindBy(id = "terms-conditions-link")
    WebElement TermsConditionsLink;
    @FindBy(id = "search-results-resume-review-banner")
    WebElement SearchResultsResumeReviewBanner;
    @FindBy(id = "job-view-resume-review-banner")
    WebElement JobViewResumeReviewBanner;
    @FindBy(id = "company-search-resume-review-banner")
    WebElement CompanySearchResumeReviewBanner;

    public void enterEmailAddress(String email) {
        logger.info("Entered email address --> : " + email);
        EmailAddressField.sendKeys(email);
    }

    public void enterFirstName(String firstName) {
        logger.info("Entered first name" + firstName);
        FirstNameField.clear();
        FirstNameField.sendKeys(firstName);
    }

    public void clickOnFirstnameField() {
        logger.info("Clicking on first name field ");
        clickOnElement(FirstNameField);
    }

    public void enterLastName(String lastName) {
        logger.info("Entered last name" + lastName);
        LastNameField.clear();
        LastNameField.sendKeys(lastName);
        LastNameField.sendKeys(Keys.ESCAPE);
    }

    public void enterPassword(String password) {
        logger.info("Entered password" + password);
        PasswordField.sendKeys(password);
    }

    public void enterJobTitle(String jobTitle) {
        logger.info("Entered job title" + jobTitle);
        LatestJobTitleField.sendKeys(jobTitle);
    }

    public void enterZipcode(String zipcode) {
        logger.info("Entered zipcode" + zipcode);
        ZipCodeField.sendKeys(zipcode);
        ZipCodeField.sendKeys(Keys.ESCAPE);
        ZipCodeField.sendKeys(Keys.TAB);
    }

    public void upLoadYourResume(String resumePath) {
        String browser = System.getProperty("browserName");
        logger.info("browserName = " + browser);
        waitFor(2);
        WebElement uploadElement = getThreadDriver().findElement(By.id("resume1"));
        if (browser.contains("remote") || browser.contains("lambda") || browser.contains("browserStack") || browser.contains("android")) {
            ((RemoteWebElement) uploadElement).setFileDetector(new LocalFileDetector());
        }
        uploadElement.sendKeys(resumePath);
        waitFor(2);

    }
//    public void upLoadYourResume(String resumePath) {
////        String browser = System.getProperty("browserName");
////        logger.info("browserName = " + browser);
////        waitFor(2);
////        WebElement uploadElement = getThreadDriver().findElement(By.id("resume1"));
////        if (browser.contains("remote") || browser.contains("lambda") || browser.contains("browserStack") || browser.contains("android")) {
////            ((RemoteWebElement) uploadElement).setFileDetector(new LocalFileDetector());
////        }
//        WebElement uploadElement = getThreadDriver().findElement(By.id("resume1"));
//        uploadElement.sendKeys(resumePath);
//        waitFor(2);
//
//    }

    public void selectFile() {
        try {

            System.out.println("context-->" + ((AndroidDriver) getThreadDriver()).getContext());

            if (checkElementPresence("(//*[@name='email'])/../..//*[text()='Upload resume']")) {
                getThreadDriver().findElement(By.xpath("(//*[@name='email'])[2]/../..//*[text()='Upload resume']")).click();
            }
            ((AndroidDriver) getThreadDriver()).context("NATIVE_APP");

            if (checkElementPresence("//*[@text='Upload your resume']")) {
                getThreadDriver().findElement(By.xpath("//*[@text='Upload your resume']")).click();
            }
            ((AndroidDriver) getThreadDriver()).context("CHROMIUM");
            if (checkElementPresence("//*[text()='from this computer']")) {
                getThreadDriver().findElement(By.xpath("//*[text()='from this computer']")).click();
            }
            ((AndroidDriver) getThreadDriver()).context("NATIVE_APP");
            waitFor(1);

            if (checkElementPresence("//*[@text='While using the app']")) {
                getThreadDriver().findElement(By.xpath("//*[@text='While using the app']")).click();
            }
            waitFor(1);
            if (checkElementPresence("//*[@text='Files']")){
                getThreadDriver().findElement(By.xpath("//*[@text='Files']")).click();
            }

           waitFor(2);
            if (checkElementPresence("//*[@content-desc='Show roots']")) {
               getThreadDriver().findElement(By.xpath("//*[@content-desc='Show roots']")).click();


          }

            waitFor(2);
            if (checkElementPresence("//*[@text='Downloads']")) {
                getThreadDriver().findElement(By.xpath("//*[@text='Downloads']")).click();
            }


            waitFor(2);
            if (checkElementPresence("//*[@text='test-cv.pdf']")){
                getThreadDriver().findElement(By.xpath("//*[@text='test-cv.pdf']")).click();
            }


            waitFor(2);
            //  ((AndroidDriver)getThreadDriver()).context("WEBVIEW_com.dayizhihui.dayishi.hpv");
            ((AndroidDriver) getThreadDriver()).context("CHROMIUM");

        }catch (Exception e){
            ((AndroidDriver) getThreadDriver()).context("CHROMIUM");
        }
    }

    public void clickOnResumeCheckbox() {
        try {
            waitUntilElementIsLocated(ResumeCheckCheckbox, 10);
            if (ResumeCheckCheckbox.isSelected()) {
                logger.info("Clicking on resume review checkbox ");
                clickOnElement(ResumeCheckCheckbox);
                waitFor(1);
            }
        } catch (Exception e) {
            logger.info("Either we've already clicked resume review checkbox or we are on page 2 registration. Message: " + e.getMessage());
        }
    }

    public void clickOnRegisterButton() {
        logger.info("Clicking on register button");
        //clickOnElement(RegisterButton);
        clickOnElementWithJS(RegisterButton);
        waitFor(1);
    }

    public void enterPhoneNumber(String phoneNumber) {
        logger.info("Entered phone number" + phoneNumber);
        PhoneNumberField.sendKeys(phoneNumber);
    }

    public void enterDesiredJobTitle(String desiredJobTitle) {
        logger.info("Entered desired job title" + desiredJobTitle);
        DesiredJobTitle.sendKeys(desiredJobTitle);
        DesiredJobTitle.sendKeys(Keys.TAB);
        DesiredJobTitle.sendKeys(Keys.TAB);
    }

    public void jobTypeCheckBox(String checkBox) {
        List<WebElement> linkList = getThreadDriver().findElements(By.xpath("//div[@class='reg2-row jobtype-row']/label/span"));
        logger.info("Clicking on job type check box" + checkBox);
        for (WebElement ele : linkList) {
            if (ele.getText().contains(checkBox)) {
                clickOnElement(By.xpath("//div[@class='reg2-row jobtype-row']/label/span[contains(text(),'" + checkBox + "')]"));
                break;
            }
        }
    }

    public void selectDesiredSalaryMinAndMax(String salMin, String salMax) {
        logger.info("Entered salary from and salary to" + salMin + salMax);
        //  clickOnElement(SalaryFromDropdown);
        SalaryFromDropdown.click();
        clickOnElementUsingText(salMin);
        waitFor(3);
        getThreadDriver().findElement(By.xpath("//select[@id='salary_expectation_from']/option[text()='" + salMin + "']")).click();
        waitFor(3);
        SalaryToDropdown.click();
        // clickOnElement(SalaryToDropdown);
        getThreadDriver().findElement(By.xpath("//select[@id='salary_expectation_to']/option[text()='" + salMax + "']")).click();
        //  clickOnElementUsingText(salMax);
        waitFor(3);
        //  selectByVisibleTextFromDropDown(SalaryFromDropdown, salMin);
        // selectByVisibleTextFromDropDown(SalaryToDropdown, salMax);
    }

    public void clickOnAddJobTitleLink() {
        logger.info("Clicking on add job title link");
        clickOnElement(AddJobTitleLink);
    }

    public void enterAddJobTitle(String jobTitle) {
        logger.info("Entered add job title");
        JobTitleField.sendKeys(jobTitle);
        DesiredJobTitle.sendKeys(Keys.TAB);
    }

    public void clickOnCompleteButton() {
        logger.info("Clicking on complete button");
        ((JavascriptExecutor) getThreadDriver()).executeScript("arguments[0].scrollIntoView(true);", getThreadDriver().findElement(By.xpath("//*[@id='register-stage2-submit']")));
        waitFor(2);
        ((JavascriptExecutor) getThreadDriver()).executeScript("arguments[0].click();", getThreadDriver().findElement(By.xpath("//*[@id='register-stage2-submit']")));

//
//        ((AndroidDriver) getThreadDriver()).context("NATIVE_APP");
//        if (isElementDisplay(getThreadDriver().findElement(By.xpath("//*[@resource-id='register-stage2-submit']")))) {
//            getThreadDriver().findElement(By.xpath("//*[@resource-id='register-stage2-submit']")).click();
//        }
//
//        ((AndroidDriver) getThreadDriver()).context("CHROMIUM");

        waitFor(2);
    }

    public void selectCountryFromDropdown(String country) {
        logger.info("Selected country from dropdown" + country);
        //  selectByVisibleTextFromDropDown(CountryDropdown, country);
        clickOnElement(CountryDropdown);
        clickOnElementUsingText(country);
    }

    public void enterCity(String city) {
        logger.info("Entered city  " + city);
        clearTextFromField(CityField);
        CityField.sendKeys(city);
    }

    public void enterHomeTown(String homeTown) {
        logger.info("Entered city  " + homeTown);
        HomeTown.sendKeys(homeTown);
    }

    public void clickOnRegisterAndConfirmButton() {
        logger.info("Clicking on register and confirm button  ");
        clickOnElement(RegisterAndConfirmButton);
        waitFor(1);
    }

    public void clickOnRegisterAndPopupCloseButton() {
        logger.info("Clicking on register and popup close button  ");
        clickOnElement(RegisterPopupCloseButton);
    }

    public void clickOnPrivacyPolicyLink() {
        logger.info("Clicking on private policy link ");
        //clickOnElement(PrivacyPolicyLink);
        clickOnElementWithJS(PrivacyPolicyLink);
    }

    public void clickOnTermsConditionsLink() {
        logger.info("Clicking on terms conditions link ");
        //clickOnElement(TermsConditionsLink);
        clickOnElementWithJS(TermsConditionsLink);
    }

    public void clickOnSearchResultResumeReviewBanner() {
        logger.info("Clicking on search result resume review banner ");
        clickOnElement(SearchResultsResumeReviewBanner);
    }

    public void clickOnJobViewResumeReviewBanner() {
        logger.info("Clicking on job view resume review banner ");
        clickOnElement(JobViewResumeReviewBanner);
    }

    public void clickOnCompanySearchResumeReviewBanner() {
        logger.info("Clicking on company search resume review banner ");
        clickOnElement(CompanySearchResumeReviewBanner);
    }

    public void SelectDifferentFormatFiles() {
        try {

            System.out.println("context-->" + ((AndroidDriver) getThreadDriver()).getContext());
            ((AndroidDriver) getThreadDriver()).context("NATIVE_APP");
            try {
                if (checkElementPresence("//*[@text='Upload your resume']")) {
                    getThreadDriver().findElement(By.xpath("//*[@text='Upload your resume']")).click();
                }
            } catch (Exception ex) {
                if (checkElementPresence("//*[@text='Upload resumeOptional']")) {
                    getThreadDriver().findElement(By.xpath("//*[@text='Upload resumeOptional']")).click();
                }
            }
            ((AndroidDriver) getThreadDriver()).context("CHROMIUM");
            if (checkElementPresence("//*[text()='from this computer']")) {
                getThreadDriver().findElement(By.xpath("//*[text()='from this computer']")).click();
            }
            ((AndroidDriver) getThreadDriver()).context("NATIVE_APP");
            waitFor(1);

            if (checkElementPresence("//*[@text='While using the app']")) {
                getThreadDriver().findElement(By.xpath("//*[@text='While using the app']")).click();
            }
            waitFor(1);
            if (checkElementPresence("//*[@text='Files']")){
                getThreadDriver().findElement(By.xpath("//*[@text='Files']")).click();
            }

            waitFor(2);
            if (checkElementPresence("//*[@content-desc='Show roots']")) {
                getThreadDriver().findElement(By.xpath("//*[@content-desc='Show roots']")).click();
            }
            waitFor(2);
            if (checkElementPresence("//*[@text='Downloads']")) {
                getThreadDriver().findElement(By.xpath("//*[@text='Downloads']")).click();
            }

//            waitFor(2);
//            if (checkElementPresence("//*[@text='"+path+"']")) {
//                getThreadDriver().findElement(By.xpath("//*[@text='"+path+"']")).click();
//            }

            waitFor(2);
            if (checkElementPresence("//*[@text='test-cv.pdf']")){
                getThreadDriver().findElement(By.xpath("//*[@text='test-cv.pdf']")).click();
            }

            waitFor(2);
            if (checkElementPresence("//*[@text='resume-oversized.odt']")){
                getThreadDriver().findElement(By.xpath("//*[@text='resume-oversized.odt']")).click();
            }
            waitFor(2);
            if (checkElementPresence("//*[@text='resume-undersized.txt']")){
                getThreadDriver().findElement(By.xpath("//*[@text='resume-undersized.txt']")).click();
            }
            waitFor(2);
            if (checkElementPresence("//*[@text='project_structure.jpg']")){
                getThreadDriver().findElement(By.xpath("//*[@text='project_structure.jpg']")).click();
            }
            waitFor(2);
            if (checkElementPresence("//*[@text='Index.html']")){
                getThreadDriver().findElement(By.xpath("//*[@text='Index.html']")).click();
            }
            waitFor(2);
            //  ((AndroidDriver)getThreadDriver()).context("WEBVIEW_com.dayizhihui.dayishi.hpv");
            ((AndroidDriver) getThreadDriver()).context("CHROMIUM");

        }catch (Exception e){
            ((AndroidDriver) getThreadDriver()).context("CHROMIUM");
        }

    }
}