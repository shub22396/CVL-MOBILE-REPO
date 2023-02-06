package com.resumelibrary.cucumber.stepdefinitions.web.candidate;

import com.resumelibrary.utilities.Utility;
import com.resumelibrary.webtest.candidate.JobSearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;

public class JobSearch extends Utility {

    @And("I fill in search jobs nav bar keywords search with {string}")
    public void iFillInSearchJobsNavBarKeywordsSearchWith(String keyword) {
        new JobSearchPage().enterSearchJobsNavBarKeyword(keyword);
    }

    @And("I fill in search jobs nav bar location search with {string}")
    public void iFillInSearchJobsNavBarLocationSearchWith(String location) {
        new JobSearchPage().enterSearchJobsNavBarLocation(location);
    }

    @And("I select {string} from search distance")
    public void iSelectFromSearchDistance(String distance) {
        new JobSearchPage().selectDistance(distance);
    }

    @And("I click on search jobs")
    public void iClickOnSearchJobs() {
        new JobSearchPage().clickButtonSearchJobs();
    }

    @And("I fill in search title field with {string}")
    public void iFillInSearchTitleFieldWith(String jobTitle) {
        new JobSearchPage().enterJobTitle(jobTitle);
    }

    @And("I fill in search location with {string}")
    public void iFillInSearchLocationWith(String location) {
        new JobSearchPage().enterSearchLocation(location);
    }

    @And("I click on Find Jobs search button")
    public void iClickOnFindJobsSearchButton() {
        new JobSearchPage().clickFindJobs();
    }
    @And("I click on {string} button")
    public void iClickOnSubmitFeedBack(String text) {
        new JobSearchPage().clickOnSubmitFeedBack(text);
    }
    @And("I verify that http code is 200 and not 301")
    public void iVerifyRedirectURL() {
        new JobSearchPage().verifyRedirectURL();
    }

    @And("I select the option {string} from Salary Min")
    public void iSelectTheOptionFromSalaryMin(String salMin) {
        new JobSearchPage().selectMinSalary(salMin);
    }

    @And("I select the option {string} from Salary Max")
    public void iSelectTheOptionFromSalaryMax(String salMax) {
        new JobSearchPage().selectMaxSalary(salMax);
    }

    @And("I select the option {string} from Job Type")
    public void iSelectTheOptionFromJobType(String jobType) {
        new JobSearchPage().selectJobType(jobType);
    }

    @And("I select the option {string} from Posted Since")
    public void iSelectTheOptionFromPostedSince(String pd) {
        new JobSearchPage().selectPostedSince(pd);
    }

    @And("I should not see link {string} with id {string}")
    public void iShouldNotSeeLinkWithIdOnThePage(String link, String elementId) {
        Assert.assertFalse(new JobSearchPage().verifyPageContainsLinkwithId(link, elementId));
    }

    @Then("I should see link {string} with id {string}")
    public void iShouldSeeLinkWithId(String link, String elementId) {
        Assert.assertTrue(new JobSearchPage().verifyPageContainsLinkwithId(link, elementId));
    }

    @And("I should see link {string}")
    public void iShouldSeeLink(String link) {
        new JobSearchPage().verifyLinkOnThePage(link);
    }

    @Then("I click on link {string}")
    public void iClickOnLink(String link) {
        new JobSearchPage().clickOnLink(link);
    }

    @Then("I should see search results displaying text {string}")
    public void iShouldSeeSearchResultsDisplayingText(String displayingText) {
        new JobSearchPage().verifyDisplayingText(displayingText);
    }

    @And("The search jobs navigation bar button has text {string}")
    public void theSearchJobsNavigationBarButtonHasText(String text) {
        new JobSearchPage().verifySearchJobsNavigationBarButtonHasText(text);
    }

    @When("I select option {string} from results per page")
    public void iSelectOptionFromResultsPerPage(String searchResults) {
        new JobSearchPage().selectResultsPerPage(searchResults);
    }

    @Then("I should see option {string} selected")
    public void iShouldSeeOptionSelected(String option) {
        new JobSearchPage().verifyResultsPerPage(option);
    }

    @Then("I click on Featured jobs one")
    public void iClickOnFeaturedOne() {
        new JobSearchPage().clickOnFeaturedJobOne();
    }

    @And("I click on suggested locations one")
    public void iClickOnSuggestedLocationsOne() {
        new JobSearchPage().clickOnSuggestedLocationsOne();
    }

    @And("I click on suggested locations two")
    public void iClickOnSuggestedLocationsTwo() {
        new JobSearchPage().clickOnSuggestedLocationsTwo();
    }

    @And("I should see text Location: {string}")
    public void iShouldSeeTextLocation(String location) {
        Assert.assertEquals(new JobSearchPage().verifyLocation(location), location);
    }

    @Then("I should not see search jobs navigation bar button")
    public void iShouldNotSeeSearchJobsNavigationBarButton() {
        new JobSearchPage().verifyThatSearchJobsNavigationBarButtonIsNotDisplay();
    }

    @And("the title field should contain {string}")
    public void theTitleFieldShouldContain(String text) {

        Assert.assertEquals(new JobSearchPage().getJobTitleAttributeValue(), text);
    }

    @And("the location field should contain {string}")
    public void theLocationFieldShouldContain(String text) {
        Assert.assertEquals(new JobSearchPage().getLocationAttributeValue(), text);
    }

    @And("the distance field should contain {string}")
    public void theDistanceFieldShouldContain(String distance) {
        Assert.assertTrue(new JobSearchPage().getDistanceAttributeValue(distance));
    }

    @And("the salary min field should contain {string}")
    public void theSalaryMinFieldShouldContain(String salMin) {
        Assert.assertTrue(new JobSearchPage().getSalaryMinAttributeValue(salMin));
    }

    @And("the salary max field should contain {string}")
    public void theSalaryMaxFieldShouldContain(String salMax) {
        Assert.assertTrue(new JobSearchPage().getSalaryMaxAttributeValue(salMax));
    }

    @And("the job type field should contain {string}")
    public void theJobTypeFieldShouldContain(String text) {
        Assert.assertTrue(new JobSearchPage().getJobTypeAttributeValue(text));
    }

    @And("the posted date field should contain {string}")
    public void thePostedDateFieldShouldContain(String text) {
        Assert.assertEquals(new JobSearchPage().getPostedSinceAttributeValue(), text);
    }

    @Then("I click on {string} in location module")
    public void iClickOnInLocationModule(String location) {
        new JobSearchPage().clickOnInLocationModule(location);
    }

    @And("I scroll down to element {string}")
    public void iScrollDownToElement(String text) {
        new JobSearchPage().scrollDownToElement(text);
    }

    @When("I enter reasons other input {string}")
    public void iEnterReasonsOtherInput(String text) {
        new JobSearchPage().enterReasonsOtherInput(text);
    }

    @When("I click on search job title link")
    public void iClickOnSearchJobTitleLink() {
        new JobSearchPage().clickOnSearchJobTitle();
    }

    @And("the distance {string} should be selected")
    public void theDistanceShouldBeSelected(String distance) {
        Assert.assertEquals(new JobSearchPage().getDistanceIsSelected(), distance);
    }

    @And("the salary min should be {string}")
    public void theSalaryMinShouldBe(String salMin) {
        Assert.assertEquals(new JobSearchPage().getSalMinIsSelected(), salMin);
    }

    @And("the salary max should be {string}")
    public void theSalaryMaxShouldBe(String salMax) {
        Assert.assertEquals(new JobSearchPage().getSalMaxIsSelected(), salMax);
    }

    @And("the posted date should be {string}")
    public void thePostedDateShouldBe(String postedDate) {
        Assert.assertTrue(new JobSearchPage().getPostedInTheIsSelected(postedDate));
    }

    @And("I select the option {string} from order by dropdown")
    public void iSelectTheOptionFromOrderByDropdown(String orderBy) {
        new JobSearchPage().selectOrderByFromList(orderBy);
    }

    @And("the sort by should be {string}")
    public void theSortByShouldBe(String sortBy) {
        Assert.assertTrue(new JobSearchPage().getSortByIsSelected(sortBy));
    }

    @And("the job type should be {string}")
    public void theJobTypeShouldBe(String jobType) {
        Assert.assertEquals(new JobSearchPage().getJobTypeIsSelected(), jobType);

    }

    @And("I Click on Similar Jobs")
    public void iClickOnSimilarJobs() {
        new JobSearchPage().clikOnSimilarJobs();
    }

    @And("I Click on job from Similar Jobs")
    public void iClickOnJobFromSimilarJobs() {
        new JobSearchPage().clikOnJobFromSimilarJobs();
    }

    @Then("I should be able to see in browser URL")
    public void iShouldBeAbleToSeeInBrowserURL() {
        new JobSearchPage().iShouldBeAbleToSeeInBrowserURL();
    }

    @When("I click on title of the job on similar jobs module and verify url")
    public void iClickOnTitleOfTheJobOnSimilarJobsModuleAndVerifyUrl() {
        new JobSearchPage().clickOnSimilarJobs();
    }

    @Then("I should see caution message h3 tag {string}")
    public void iShouldSeeCautionMessageH3tag(String text) {
        Assert.assertEquals(new JobSearchPage().getCautionMessageH3Tag(),text);
    }

    @Then("I should see caution message p tag {string}")
    public void iShouldSeeCautionMessagePTag(String text) {
        Assert.assertEquals(new JobSearchPage().getCautionMessagePtag(),text);
    }
}