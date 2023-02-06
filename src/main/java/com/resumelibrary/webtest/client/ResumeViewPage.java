package com.resumelibrary.webtest.client;

import com.resumelibrary.utilities.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ResumeViewPage extends Utility {

    public ResumeViewPage() {
        PageFactory.initElements(getThreadDriver(), this);
    }

    private static final Logger logger = LogManager.getLogger(ResumeViewPage.class);

    @FindBy(id = "save-resume")
    WebElement SaveResume;
    @FindBy(id = "saved_resume_1")
    WebElement SavedResume1;
    @FindBy(id = "saved_action_view_1")
    WebElement SavedActionView1;
    @FindBy(id = "saved_action_delete_1")
    WebElement SavedActionDelete1;
    @FindBy(id = "saved_delete")
    WebElement SavedDelete;
    @FindBy(id = "saved_delete_cancel")
    WebElement SavedDeleteCancel;
    @FindBy(id = "contact-applicant-btn")
    WebElement ContactApplicantBtn;
    @FindBy(id = "request-resume-action-select")
    WebElement RequestResumeActionSelect;
    @FindBy(id = "request-resume-notify-email")
    WebElement RequestResumeNotifyEmail;
    @FindBy(xpath = "//*[@id=\"per_page\"]")
    WebElement ResultsPerPage;
    @FindBy(id = "resume-critique-btn")
    WebElement FreeResumeReviewButton;
    @FindBy(id = "mobile-search-trigger")
    WebElement mobileSearchButton;
    @FindBy(id = "unlock-resume")
    WebElement UnlockResume;
    @FindBy(id = "sd-mobile-sort-select")
    WebElement MobileOrderBySortSelect;

    public void clickOnSaveResumeBtn() {
        logger.info("Clicking on save resume ");
        waitFor(1);
        if (SaveResume.getText().equals("Save")) {
            clickOnElementWithJS(SaveResume);
        }
        waitFor(1);
    }

    public void clickOnSavedResume1Btn() {
        logger.info("Clicking on saved resume 1");
        clickOnElement(SavedResume1);
    }

    public void clickOnSavedActionView1() {
        logger.info("Clicking on saved action view 1");
        clickOnElement(SavedActionView1);
    }

    public void clickOnSavedActionDelete1() {
        logger.info("Clicking on saved action delete 1");
        clickOnElement(SavedActionDelete1);
    }

    public void clickOnSavedDelete() {
        logger.info("Clicking on saved delete  ");
        clickOnElement(SavedDelete);
    }

    public void clickOnSavedDeleteCancel() {
        logger.info("Clicking on saved delete cancel ");
        clickOnElement(SavedDeleteCancel);
    }

    public void clickOnContactApplicantBtn() {
        logger.info("Clicking on contact applicant button ");
        clickOnElementWithJS(ContactApplicantBtn);
    }

    public void clickOnPreviewResume() {
        String textOnThePage = getThreadDriver().findElement(By.xpath("//*[@id=\"main\"]/div/div[4]/p")).getText();
        if (textOnThePage.isEmpty()) {
            if (UnlockResume.isDisplayed()) {
                logger.info("found element with text Unlock Resume to click");
                clickOnElementWithJS(UnlockResume);
                waitFor(1);
            }
        } else {
            logger.info("no element found  with text Unlock Resume: ");
        }
    }

    public void findLockedCandidate() {
        waitFor(2);
        String xpath = "//*[starts-with(@id,'preview_locked_resume_')]";
        logger.info("finding the web element with xpath : " + xpath);

        List<WebElement> lockedProfile = getThreadDriver().findElements(By.xpath(xpath));
        logger.info("Number of elements : " + lockedProfile.size());
        for (WebElement contact : lockedProfile) {
            waitFor(1);
            clickOnElementWithJS(contact);
            switchTab();
            try {
                WebElement unlockResume = getThreadDriver().findElement(By.xpath("//*[@id=\"unlock-resume\"]"));
                if (unlockResume.isDisplayed()) {
                    logger.info("found element with text Unlock Resume: ");
                    break;
                }
            } catch (NoSuchElementException nse) {
                logger.info("no element found  with text Unlock Resume: ");
                getThreadDriver().close();
                switchWindowIndex(0);
            }
        }
    }

    public void selectFromRequestResumeActionSelect(String action) {
        logger.info("Selected " + action + "from dropdown");
        selectByVisibleTextFromDropDown(RequestResumeActionSelect, action);
    }

    public void enterEmailToRequestResumeNotifyEmailField(String email) {
        logger.info("Entered email " + email);
        RequestResumeNotifyEmail.sendKeys(email);
    }

    public void findLockedCandidateAndVerify(String text) {
        selectByVisibleTextFromDropDown(ResultsPerPage, "80");
        String xpath = "//*[starts-with(@id,'preview_locked_resume')]";
        logger.info("finding the web element with xpath : " + xpath);

        List<WebElement> lockedProfile = getThreadDriver().findElements(By.xpath(xpath));
        waitFor(1);

        logger.info("Number of elements : " + lockedProfile.size());
        for (WebElement contact : lockedProfile) {
            clickOnElement(contact);
            switchTab();
            try {
                WebElement unlockResume = getThreadDriver().findElement(By.xpath("//*[@id=\"unlock-resume\"]"));
                if (unlockResume.isDisplayed()) {
                    logger.info("found element with text  Unlock Resume: ");
                    Assert.assertTrue(unlockResume.isDisplayed());
                    break;
                }
            } catch (NoSuchElementException nse) {
                logger.info("no element found  with text  Unlock Resume: ");
                getThreadDriver().close();
                switchWindowIndex(0);
            }
        }
    }

    public void findUnLockedCandidate() {
        selectByVisibleTextFromDropDown(ResultsPerPage, "80");
        String xpath = "//*[starts-with(@id,'preview_unlocked_resume')]";
        logger.info("finding the web element with xpath : " + xpath);

        waitFor(2);
        List<WebElement> lockedProfile = getThreadDriver().findElements(By.xpath(xpath));

        logger.info("Number of elements : " + lockedProfile.size());
        for (WebElement contact : lockedProfile) {
            clickOnElement(contact);
            switchTab();
            try {
                WebElement unlockResume = getThreadDriver().findElement(By.xpath("//*[@id=\"unlock-resume\"]"));
                if (unlockResume.isDisplayed()) {
                    logger.info("found element with text  Unlock Resume: ");
                    Assert.assertTrue(unlockResume.isDisplayed());
                    waitFor(2);
                    break;
                }
            } catch (NoSuchElementException nse) {
                logger.info("no element found  with text  Unlock Resume: ");
                getThreadDriver().close();
                switchWindowIndex(0);
            }
        }
    }

    public void clickOnFreeResumeReviewBtn() {
        logger.info("Clicking on free resume review button");
        waitUntilElementIsLocated(FreeResumeReviewButton, 5);
        clickOnElement(FreeResumeReviewButton);
    }

    public void clickOnMobileSearch() {
        logger.info("Clicking on Mobile Search button");
        waitUntilElementIsLocated(mobileSearchButton, 5);
        clickOnElement(mobileSearchButton);
    }

    public void clickOncViewResumeOptions(String text) {
        logger.info("Clicking on view resume options " + text);
        List<WebElement> list = getThreadDriver().findElements(By.xpath("//div[@class='view-resume-options top-options']//span[text()=\"" + text + "\"]"));
        for (WebElement ele : list) {
            if (ele.getText().equals(text)) {
                try {
                    waitUntilElementToBeClickable(ele, 10);
                    ele.click();
                } catch (ElementClickInterceptedException e) {
                    waitFor(2);
                    waitUntilElementToBeClickable(ele, 10);
                    ele.click();
                }
            }
        }
    }

    public String viewResumeTopOptions(String text) {
        String isTextPresent = "";
        logger.info("Clicking on view resume options " + text);
        List<WebElement> list = getThreadDriver().findElements(By.xpath("//div[@class='view-resume-options top-options']//span"));
        for (WebElement ele : list) {
            if (ele.getText().equals(text)) {
                isTextPresent = text;
            }
        }
        return isTextPresent;
    }

    public void selectValueFromOrderByDropdownOnClientResumePage(String text) {
        logger.info("Select order by from dropdown" + text);
        // selectByVisibleTextFromDropDown(MobileOrderBySortSelect, text);
        WebElement orderBySelect = getThreadDriver().findElement(By.id("sd-mobile-sort-select"));
        orderBySelect.click();
        WebElement jobIdAsc = getThreadDriver().findElement(By.xpath("//*[text()='" + text + "']"));
        waitUntilElementIsLocated(jobIdAsc, 30);
        jobIdAsc.click();
        waitFor(4);

    }
}