package com.resumelibrary.webtest.candidate;

import com.resumelibrary.utilities.Utility;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.List;

public class JobSearchPage extends Utility {

    public JobSearchPage() {
        PageFactory.initElements(getThreadDriver(), this);
    }

    private static final Logger logger = LogManager.getLogger(JobSearchPage.class);

    @FindBy(name = "top-search-q")
    WebElement SearchKeyword;
    @FindBy(id = "top-search-loc")
    WebElement TopSearchLocation;
    @FindBy(id = "search_radius")
    WebElement Radius;
    @FindBy(id = "top-search-jobs-btn")
    WebElement SearchJobsButton;
    @FindBy(name = "loc")
    WebElement SearchLocation;
    @FindBy(name = "q")
    WebElement JobTitleQ;
    @FindBy(id = "home-search-submit")
    WebElement FindJobs;
    @FindBy(id = "annual_salary_from")
    WebElement SalaryMin;
    @FindBy(id = "annual_salary_to")
    WebElement SalaryMax;
    @FindBy(name = "job_type")
    WebElement JobType;
    @FindBy(id = "pd")
    WebElement PostedSince;
    @FindBy(xpath = "//a[text() = \"2\"]")
    WebElement LinkIndexPage;
    @FindBy(id = "header_radius")
    WebElement SearchResultDistance;
    @FindBy(name = "pd")
    WebElement PostedDate;
    @FindBy(id = "displaying-text")
    WebElement DisplayingText;
    @FindBy(id = "results-pp")
    WebElement ResultsPerPage;
    @FindBy(id = "job_title__nat_prem_1")
    WebElement FeaturedJobOne;
    @FindBy(id = "suggested-locations-1")
    WebElement SuggestedLocationsOne;
    @FindBy(id = "suggested-locations-2")
    WebElement SuggestedLocationsTwo;

    @FindBy(xpath = "//*[@class='search-result-info-desc desc--location']")
    WebElement LocationLabel;
    @FindBy(xpath = "//*[@id='show_our_jobs']/div[1]/dl/dd[2]")
    WebElement Location;
    //xpath for "San Francisco"
    @FindBy(xpath = "//*[@id=\"location-jobs-module\"]/div/ul/li[*]/a")
    WebElement LocationLinkFromModule;
    @FindBy(css = ".reasons-other-input")
    WebElement ReasonsOtherInput;
    @FindBy(css = ".search-result-info-title")
    WebElement SearchJobTitle;
    @FindBy(id = "order_by")
    WebElement OrderBy;
    @FindBy(id = "results-order")
    WebElement SortBy;
    @FindBy(id = "similar-jobs-module-jobview")
    WebElement similarJobSection;
    String SearchJobsNavigationBarButton = "top-search-jobs-btn";
    @FindBy(xpath = "//*[@id='similar-jobs-jobview']/descendant::a")
    List<WebElement> listOfSimilarJobs;
    @FindBy(xpath = "//*[@class = 'caution-div']/h3")
    WebElement CautionMessageH3;
    @FindBy(xpath = "//*[@class = 'caution-div']/p")
    WebElement CautionMessagePtag;

    public void enterSearchJobsNavBarKeyword(String keyword) {
        logger.info("Keyword entered in search jobs nav bar field is  : " + keyword);
        SearchKeyword.sendKeys(keyword);
    }

    public void enterSearchJobsNavBarLocation(String location) {
        logger.info("Location entered in search jobs nav bar location field is  : " + location);
        TopSearchLocation.sendKeys(location);
    }

    public void selectDistance(String distance) {
        logger.info("Select the radius from dropdown is : " + distance);
        waitUntilElementToBeClickable(Radius,20);
        selectByValueFromDropDown(Radius, distance);
    }

    public boolean getDistanceAttributeValue(String distance) {
        logger.info("Verifying distance value " + SearchResultDistance);
        waitUntilElementIsLocated(SearchResultDistance,20);
        int count=0;
        List<WebElement> allOptions = getThreadDriver().findElements(By.xpath("//select[@id='header_radius']//option"));
        for(WebElement option : allOptions) {
            logger.info(option.getAttribute("value"));
            if (option.getAttribute("value").equals(distance)) {
                count++;
                break;
            }
        }
        if(count==0)
        {
            return false;
        }
        else
        {
            return true;
        }
        //return SearchResultDistance.getAttribute("value");
    }

    public void clickButtonSearchJobs() {
        logger.info("click on Search Jobs button");
        clickOnElement(SearchJobsButton);
    }

    public void verifySearchJobsNavigationBarButtonHasText(String text) {
        logger.info("verifying the search jobs navigation bar button has text : " + text);
        String InputValue = getThreadDriver().findElement(By.id("top-search-jobs-btn")).getAttribute("value");
        Assert.assertTrue(text.equalsIgnoreCase(InputValue));
    }

    public void verifyThatSearchJobsNavigationBarButtonIsNotDisplay() {
        logger.info("Verifying search jobs navigation bar button is not display ");
        Assert.assertFalse(verifyThatElementIsDisplayedUsingLocator("id", SearchJobsNavigationBarButton));
    }

    public void enterJobTitle(String jobTitle) {
        logger.info("Keyword/title filled with :" + jobTitle);
        JobTitleQ.sendKeys(jobTitle);
        JobTitleQ.sendKeys(Keys.TAB);
    }

    public String getJobTitleAttributeValue() {
       // System.out.println("Page Source:"+getThreadDriver().getPageSource());
        logger.info("Verifying job title value " + JobTitleQ);
        waitUntilElementIsLocated(JobTitleQ,20);
        return JobTitleQ.getAttribute("value");
    }

    public void enterSearchLocation(String location) {
        logger.info("Entered search location is " + location);
        SearchLocation.sendKeys(location);
        SearchLocation.sendKeys(Keys.TAB);
    }

    public String getLocationAttributeValue() {
        logger.info("Verifying location value " + SearchLocation);
        return SearchLocation.getAttribute("value");
    }

    public void clickFindJobs() {
        logger.info("click on Find jobs button");
        clickOnElementWithJS(FindJobs);
    }
    public void clickOnSubmitFeedBack(String text) {
        logger.info("click on feedb back request");
        clickOnElementWithJS(getThreadDriver().findElement(By.xpath("( //*[text()=\"Submit\"])[1]")));
    }

    public void verifyRedirectURL() {
        try {
            logger.info("verify redirect url");
            HttpURLConnection conn = (HttpURLConnection) (new URL(getThreadDriver().getCurrentUrl()).openConnection());
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            boolean redirect = false;
            int status = conn.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                if (status == HttpURLConnection.HTTP_MOVED_TEMP
                        || status == HttpURLConnection.HTTP_MOVED_PERM
                        || status == HttpURLConnection.HTTP_SEE_OTHER)
                    redirect = true;
            }
            logger.info("Response Code :" + status + "     Redirected :" + redirect);
            if (redirect) {
                // get redirect url from "location" header field
                String newUrl = conn.getHeaderField("Location");
                // get the cookie if need, for login
                String cookies = conn.getHeaderField("Set-Cookie");
                // open the new connnection again
                conn = (HttpURLConnection) new URL(newUrl).openConnection();
                conn.setRequestProperty("Cookie", cookies);
                conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                conn.addRequestProperty("User-Agent", "Mozilla");
                conn.addRequestProperty("Referer", "google.com");
                Assert.assertEquals(status, 200, "Response Code is not 200 while search in home page and URL Redirected to:" + newUrl);
            } else {
                Assert.assertEquals(status, 200, "Response Code is not 200 while search in home page");
            }
        } catch (Exception e) {
            Assert.fail("Failed redirect url :" + e.getMessage());
        }


    }

    public void selectMinSalary(String salMin) {
        logger.info("select the option min salary :" + salMin);
        selectByValueFromDropDown(SalaryMin, salMin);
    }

    public boolean getSalaryMinAttributeValue(String salMin) {
        logger.info("Verifying salary min value " + SalaryMin);
//        waitUntilElementIsLocated(SalaryMin, 30);
//        return SalaryMin.getAttribute("value");
        int count=0;
        List<WebElement> allOptions = getThreadDriver().findElements(By.xpath("//select[@name='annual_salary_from']//option"));
        for(WebElement option : allOptions) {
            logger.info(option.getAttribute("value"));
            if (option.getAttribute("value").equals(salMin)) {
                count++;
                break;
            }
        }
        if(count==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void selectMaxSalary(String salMax) {
        logger.info("select the option max salary :" + salMax);
        //selectByValueFromDropDown(SalaryMax, salMax);
        JavascriptExecutor jse = (JavascriptExecutor) getThreadDriver();
        jse.executeScript("arguments[0].value="+salMax, SalaryMax);
    }

    public boolean getSalaryMaxAttributeValue(String salMax) {
        logger.info("Verifying salary max value " + SalaryMax);
      //  return SalaryMax.getAttribute("value");
        int count=0;
        List<WebElement> allOptions = getThreadDriver().findElements(By.xpath("//select[@name='annual_salary_to']//option"));
        for(WebElement option : allOptions) {
            logger.info(option.getAttribute("value"));
            if (option.getAttribute("value").equals(salMax)) {
                count++;
                break;
            }
        }
        if(count==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void selectJobType(String jobType) {
        logger.info("select the option job type as  :" + jobType);
        WebElement orderBySelect = getThreadDriver().findElement(By.name("job_type"));
        orderBySelect.click();
        WebElement jobIdAsc = getThreadDriver().findElement(By.xpath("//*[text()='"+jobType+"']"));
        waitUntilElementIsLocated(jobIdAsc, 30);
        jobIdAsc.click();
       // selectByValueFromDropDown(JobType, jobType);
    }

    public boolean getJobTypeAttributeValue(String jobType) {
        logger.info("Verifying job type value " + JobType);
        waitUntilElementIsLocated(PostedDate,20);
        int count=0;
        List<WebElement> allOptions = getThreadDriver().findElements(By.xpath("//select[@name='job_type']//option"));
        for(WebElement option : allOptions) {
            logger.info(option.getText());
            if (option.getText().equals(jobType)) {
                count++;
                break;
            }
        }
        if(count==0)
        {
            return false;
        }
        else
        {
            return true;
        }
        //return JobType.getAttribute("value");
    }

    public void selectPostedSince(String pd) {
        logger.info("select the option posted since  :" + pd);
        selectByValueFromDropDown(PostedSince, pd);
    }

    public String getPostedSinceAttributeValue() {
        logger.info("Verifying posted since value " + PostedDate);
        return PostedDate.getAttribute("data-orig");
    }

    public boolean verifyPageContainsLinkwithId(String link, String elementId) {
        logger.info("link/button : " + link + " to be verified with element id :" + elementId);
        int i = 0;
        do {
            waitFor(1);
            List<WebElement> linkWithId = getThreadDriver().findElements(By.xpath("//a[@id='" + elementId + "']"));
            List<WebElement> otherElementWithId = getThreadDriver().findElements(By.xpath("//*[@id='" + elementId + "']"));
            if (linkWithId.size() > 0 || otherElementWithId.size() > 0) {
                return true;
            }
            i++;
            if (elementId.equalsIgnoreCase("save_toggle_20")) {
                i = 4;
            }
        } while (i < 4);

        return false;
    }

    public void verifyLinkOnThePage(String link) {
        logger.info("link/button :" + link + "is to be verified");
        waitFor(2);
        Assert.assertTrue(LinkIndexPage.isDisplayed());
    }

    public void clickOnLink(String link) {
        logger.info("link/button :" + link + " clicked");
        clickOnElement(LinkIndexPage);
    }

    public void verifyDisplayingText(String displayingText) {
        waitUntilElementIsLocated(DisplayingText, 15);
        String textOnElement = DisplayingText.getText();
        logger.info("text to be verified :" + displayingText + "against text :" + textOnElement);
        Assert.assertTrue(displayingText.contains(textOnElement));
    }

    public void selectResultsPerPage(String searchResults) {
        logger.info("selected the option  :" + searchResults);
        selectByVisibleText(ResultsPerPage, searchResults);
    }

    public void verifyResultsPerPage(String option) {
        Select select = new Select(ResultsPerPage);
        WebElement optionSelected = select.getFirstSelectedOption();
        String defaultItem = optionSelected.getText();
        logger.info("selected the option  :" + defaultItem);
        Assert.assertEquals(option, defaultItem);
    }

    public void clickOnFeaturedJobOne() {
        logger.info("Click on first link form the featured Jobs list ");
        clickOnElement(FeaturedJobOne);
    }

    public void clickOnSuggestedLocationsOne() {
        logger.info("Click on first link form the suggested locations list ");
        clickOnElement(SuggestedLocationsOne);
    }

    public void clickOnSuggestedLocationsTwo() {
        logger.info("Click on second link form the suggested locations list ");
        clickOnElement(SuggestedLocationsTwo);
    }

    public String verifyLocation(String location) {
        logger.info("Verifying  location: " + location);
        return LocationLabel.getText();
    }

    public void clickOnInLocationModule(String location) {
        logger.info("Clicking on San Francisco link from jobs by location module" + location);
        List<WebElement> elements = getThreadDriver().findElements(By.xpath("//*[@id='location-jobs-module']/div/ul/li[*]/a"));
        for (WebElement ele : elements) {
            if (ele.getText().contains(location)) {
                clickOnElement(By.xpath("//*[@id='location-jobs-module']/div/ul/li[*]/a[contains(text(),'" + location + " ')]"));
                break;
            }
        }
    }

    public void scrollDownToElement(String text) {
        Point location = getElementLocation(text);
        scrollDown(location.x, location.y);
        waitFor(2);
    }

    public void enterReasonsOtherInput(String input) {
        logger.info("Entered reasons other input : " + input);
        ReasonsOtherInput.sendKeys(input);
        ReasonsOtherInput.sendKeys(Keys.ESCAPE);
        waitFor( 2);
    }

    public void clickOnSearchJobTitle() {
        logger.info("click on Search Jobs title");
        clickOnElement(SearchJobTitle);
    }

    public String getDistanceIsSelected() {
        logger.info("Verifying distance selected");
        return getFirstSelectedOption(SearchResultDistance);
    }

    public String getSalMinIsSelected() {
        logger.info("Verifying sal min selected");
        return getFirstSelectedOption(SalaryMin);
    }

    public String getSalMaxIsSelected() {
        logger.info("Verifying sal max selected");
        return getFirstSelectedOption(SalaryMax);
    }

    public void selectOrderByFromList(String orderBy) {
        logger.info("Selected order by from dropdown : " + orderBy);
        selectByVisibleTextFromDropDown(OrderBy, orderBy);
    }

    public boolean getSortByIsSelected(String sortBy) {
        logger.info("Verifying sort by is selected");
        int count=0;
        List<WebElement> allOptions = getThreadDriver().findElements(By.xpath("//select[@name='results-order']//option"));
        for(WebElement option : allOptions) {
            logger.info(option.getText());
            if (option.getText().equals(sortBy)) {
               count++;
               break;
            }
        }
        if(count==0)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean getPostedInTheIsSelected(String postedDate) {
        logger.info("Verifying posted in the is selected");
        waitUntilElementIsLocated(PostedDate,20);
        int count=0;
        List<WebElement> allOptions = getThreadDriver().findElements(By.xpath("//select[@name='pd']//option"));
        for(WebElement option : allOptions) {
            logger.info(option.getText());
            if (option.getText().equals(postedDate)) {
                count++;
                break;
            }
        }
        if(count==0)
        {
            return false;
        }
        else
        {
            return true;
        }
        //return getFirstSelectedOption(PostedDate);
    }

    public String getJobTypeIsSelected() {
        logger.info("Verifying job type is selected");
        return getFirstSelectedOption(JobType);
    }

    public void clikOnSimilarJobs() {
        logger.info("clik On Similar Jobs");
        clickOnElement(similarJobSection);
    }

    public void clikOnJobFromSimilarJobs() {
        logger.info("clik On Similar Jobs");
        WebElement similarJobElement = getThreadDriver().findElement(By.xpath("(//*[@id=\"similar-jobs-module-jobview\"]//a)[1]"));
        System.setProperty("SimilarJob", similarJobElement.getText());
        clickOnElement(similarJobElement);
    }

    public void iShouldBeAbleToSeeInBrowserURL() {
        logger.info("clik On Similar Jobs");
        String url = "";
        if (System.getProperty("SimilarJob").contains(" ")) {
            url = (System.getProperty("SimilarJob").split(" ")[0]).toLowerCase() + "-" + (System.getProperty("SimilarJob").split(" ")[1]).toLowerCase();
        } else {
            url = (System.getProperty("SimilarJob").toLowerCase());
        }
        new OthersPage().verifyURLText(url);
    }

    public String getCautionMessageH3Tag() {
        return getTextFromElement(CautionMessageH3);
    }

    public String getCautionMessagePtag() {
        return getTextFromElement(CautionMessagePtag);
    }

    public void clickOnSimilarJobs() {

        for (int i = 0; i < listOfSimilarJobs.size(); i++) {
            if (i <= 2) {
                String titleText = getTextFromElement(listOfSimilarJobs.get(i));
                clickOnElement(listOfSimilarJobs.get(i));
                switchWindowIndex(1);
                waitFor(2);
                Assert.assertTrue(getTitleOfThePage().contains(titleText));
                new OthersPage().verifyTextInURL("/job/view");
                getThreadDriver().close();
                switchWindowIndex(0);
            } else if (i > 2) {
                clickOnElement(listOfSimilarJobs.get(i));
                switchWindowIndex(1);
                new OthersPage().verifyTextInURL("/register/apply");
                Assert.assertFalse(getTitleOfThePage().contains("404"));
                logger.info("Similar jobs module for external URLs should not contains 404");
                getThreadDriver().close();
                switchWindowIndex(0);
            }
        }
    }
}
