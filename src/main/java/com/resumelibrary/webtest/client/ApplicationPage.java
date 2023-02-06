package com.resumelibrary.webtest.client;

import com.resumelibrary.utilities.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ApplicationPage extends Utility {

    public ApplicationPage() {
        PageFactory.initElements(getThreadDriver(), this);
    }

    private static final Logger logger = LogManager.getLogger(ApplicationPage.class);

    List<String> sortedList;
    @FindBy(id = "application_actions_btn_1")
    WebElement ApplicationActions1Btn;
    @FindBy(id = "application_actions_view_1")
    WebElement ApplicationActionsView1Btn;
    @FindBy(id = "application_actions_reject_1")
    WebElement ApplicationActionsReject1Btn;
    @FindBy(xpath = "//div[@class='table-wrap']/table/tbody/tr/td[2]/a")
    List<WebElement> JobIds;
    @FindBy(xpath = "//div[@class='table-wrap']/table/tbody/tr/td[3]")
    List<WebElement> YourJobRefs;
    @FindBy(xpath = "//div[@class='table-wrap']/table/tbody/tr/td[4]")
    List<WebElement> JobTitles;
    @FindBy(xpath = "//div[@class='table-wrap']/table/tbody/tr/td[5]")
    List<WebElement> CandidateNames;
    @FindBy(xpath = "//div[@class='table-wrap']/table/tbody/tr/td[6]")
    List<WebElement> CandidateEmails;
    // @FindBy(xpath = "//div[@class='table-wrap']/table/tbody/tr/td[8]")
    @FindBy(xpath = "//td[@mobile-label='Date applied']")
    List<WebElement> DateApplied;
    @FindBy(css = ".next")
    WebElement NextPage;
    @FindBy(css = ".prev")
    WebElement PrevPage;
    @FindBy(id = "client-apps-mobile-sort-select")
    WebElement ApplicationsMobileSortSelect;

    public void clickOnApplicationActions1Btn() {
        logger.info("Clicking on application actions button");
        clickOnElement(ApplicationActions1Btn);
        waitFor(1);
    }

    public void clickOnApplicationActionsView1Btn() {
        logger.info("Clicking on application actions view button");
        clickOnElement(ApplicationActionsView1Btn);
        waitFor(1);
    }

    public void clickOnApplicationActionsReject1Btn() {
        logger.info("Clicking on application actions reject button");
        clickOnElement(ApplicationActionsReject1Btn);
        waitFor(1);
    }

    public List<String> getListOfJobIds() {
        sortedList = getDataList(JobIds);
        logger.info("Getting list of job ids " + sortedList);
        return sortedList;
    }

    public List<String> jobIdsByAscendingOrder() {
        sortedList = getListByAscOrder(JobIds);
        logger.info("Sorting job ids by asc order" + sortedList);
        return sortedList;
    }

    public List<String> jobIdsByDescendingOrder() {
        sortedList = getListByDescOrder(JobIds);
        logger.info("Sorting job ids by desc order" + sortedList);
        return sortedList;
    }

    public List<String> getListOfJobRef() {
        sortedList = getDataList(YourJobRefs);
        logger.info("Getting list of job ref " + sortedList);
        return sortedList;
    }

    public List<String> jobRefByAscendingOrder() {
        sortedList = getListByAscOrder(YourJobRefs);
        logger.info("Sorting job ref by asc order" + sortedList);
        return sortedList;
    }

    public List<String> jobRefByDescendingOrder() {
        sortedList = getListByDescOrder(YourJobRefs);
        logger.info("Sorting job ref by desc order" + sortedList);
        return sortedList;
    }

    public List<String> getListOfJobTitle() {
        sortedList = getDataList(JobTitles);
        logger.info("Getting list of job title " + sortedList);
        return sortedList;
    }

    public List<String> jobTitleByAscendingOrder() {
        sortedList = getListByAscOrder(JobTitles);
        logger.info("Sorting job title by asc order" + sortedList);
        return sortedList;
    }

    public List<String> jobTitleByDescendingOrder() {
        sortedList = getListByDescOrder(JobTitles);
        logger.info("Sorting job title by desc order" + sortedList);
        return sortedList;
    }

    public List<String> getListOfCandidateName() {
        if (!getDataList(CandidateNames).equals("Candidate has withdrawn their application")) {
            sortedList = getListByDescOrder(CandidateNames);
        }
        logger.info("Getting list of candidate names " + sortedList);
        return sortedList;
    }

    public List<String> candidateNamesByAscendingOrder() {
        if (!getListByAscOrder(CandidateNames).equals("Candidate has withdrawn their application")) {
            sortedList = getListByDescOrder(CandidateNames);
        }
        logger.info("Sorting candidate names by asc order" + sortedList);
        return sortedList;
    }

    public List<String> candidateNamesByDescendingOrder() {
        if (!getListByDescOrder(CandidateNames).equals("Candidate has withdrawn their application")) {
            sortedList = getListByDescOrder(CandidateNames);
        }
        logger.info("Sorting candidate names by desc order" + sortedList);
        return sortedList;
    }

    public List<String> getListOfCandidateEmail() {
        if (!getDataList(CandidateEmails).equals("Candidate has withdrawn their application")) {
            sortedList = getListByDescOrder(CandidateNames);
        }
        logger.info("Getting list of candidate email " + sortedList);
        return sortedList;
    }

    public List<String> candidateEmailByAscendingOrder() {
        if (!getListByAscOrder(CandidateEmails).equals("Candidate has withdrawn their application")) {
            sortedList = getListByDescOrder(CandidateNames);
        }
        logger.info("Sorting candidate email by asc order" + sortedList);
        return sortedList;
    }

    public List<String> candidateEmailByDescendingOrder() {
        if (!getListByDescOrder(CandidateEmails).equals("Candidate has withdrawn their application")) {
            sortedList = getListByDescOrder(CandidateNames);
        }
        logger.info("Sorting candidate email by desc order" + sortedList);
        return sortedList;
    }

    public List<String> getListOfDateApplied() {
        sortedList = getDataListForDate(DateApplied);
        logger.info("Getting list of date applied " + sortedList);
        return sortedList;
    }

    public List<String> dateAppliedByAscendingOrder() {
        sortedList = getListByAscOrderForDate(DateApplied);
        logger.info("Sorting date applied by asc order" + sortedList);
        return getSortedDatesAsc(sortedList);
    }

    public List<String> dateAppliedByDescendingOrder() {
        sortedList = getListByDescOrderForDate(DateApplied);
        logger.info("Sorting date applied by desc order" + sortedList);
        return getSortedDatesDesc(sortedList);
    }

    public void clickOnNextPageLink() {
        logger.info("Clicking on next page link");
        waitFor(1);
        clickOnElement(NextPage);
    }

    public void clickOnPrevPageLink() {
        logger.info("Clicking on prev page link");
        clickOnElement(PrevPage);
    }

    public void selectValueToSortOnApplicationsPage(String text) {
        logger.info("Select sort by from dropdown");
        clickOnElement(ApplicationsMobileSortSelect);
        clickOnElementUsingText(text);
        waitFor(4);
    }

}