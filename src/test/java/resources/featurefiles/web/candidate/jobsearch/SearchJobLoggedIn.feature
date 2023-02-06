# Tests coverage
    # 1. Advanced Search job page with keyword and location, then asserting URL
    # 2. Advanced Search job page with all filters, then asserting URL

@SearchJobLoggedIn @Regression @Web  @Search @MobileChrome
Feature: Candidate JobSearch SearchJobsLoggedIn

  Background: On search page with keyword sales
    Given I login as a candidate

  @searchJobLoggedInWithKeywordLocation
  Scenario: Search job page logged in test with keyword and location
    And   I navigate to page "Search Jobs"
    And   I fill in search title field with "Sales"
    And   I fill in search location with "Brooklyn, NY"
    And   I click on Find Jobs search button
    Then  I should be able to see in browser URL "Jobs Sales In Brooklyn Ny"
    And   I should see text H one tag "Sales jobs"
    And   I should see text "Nearby Towns"

  @searchJobLoggedInWithAllParameters @ReleaseRegression1
  Scenario: Search job page logged in test with all values
    And   I navigate to page "Search Jobs"
    And   I fill in search location with "Brooklyn, NY"
    And   I select "250" from search distance
    When  I click on link text "More Search Options"
    And   I select the option "50000" from Salary Min
    And   I select the option "100000" from Salary Max
    And   I select the option "30" from Posted Since
    And   I fill in search title field with "Sales"
    When  I click on Find Jobs search button
    Then  I should be able to see in browser URL "Jobs Sales In Brooklyn Ny R 30 Annual Salary From 50000 Annual Salary To 100000"
    And   I should see text H one tag "Sales jobs"
    And   I should see text "Nearby Towns"
    And   I scroll down to element "Next"
    Then  I click on link text "Next"
    Then  I should be able to see in browser URL "Jobs Sales In Brooklyn Ny R 30 Annual Salary From 50000 Annual Salary To 100000 Page Number 2"
    And   I scroll down to element "Next"
    Then  I click on link text "Next"
    Then  I should be able to see in browser URL "Jobs Sales In Brooklyn Ny R 30 Annual Salary From 50000 Annual Salary To 100000 Page Number 3"
    And   I should see text "Prev"
    Then  I click on hyper link "Prev"
    Then  I should be able to see in browser URL "Jobs Sales In Brooklyn Ny R 30 Annual Salary From 50000 Annual Salary To 100000"

  @searchPartnerJobLoggedInWhenLessRLJobs @ReleaseRegression1
  Scenario: Search job page logged in, displaying partner jobs when < 20 RL jobs
    When  I navigate to page "Jobs Web Job"
    And   I should not see link "Save" with id "save_toggle_20"
    And   I should not see link "More" with id "more_20"
    And   I should not see link "Apply now" with id "apply_now_20"
    And   I should not see link "1-Click apply" with id "one_click_20"
    #And   I should not see "»"
    And   I should see text "Next"
    Then  I click on hyper link "Next"
    And   I should not see link "Save" with id "save_toggle_1"
    And   I should not see link "More" with id "more_1"
    Then  I should see link "Apply now" with id "apply-now-partner-jobs-1"
    Then  I should be able to see in browser URL "Jobs Web Job In 90001 Pd 1 Page Number 2 Pn 20"
    And   I should see text "Prev"
    Then  I click on hyper link "Prev"
    Then  I should be able to see in browser URL "Jobs Web Job In 90001 Pd 1"

  @searchPartnerJobLoggedInWhenMoreRLJobs20
  Scenario: Search job page logged in, displaying partner jobs when > 19 RL jobs
    And   I navigate to page "Search Jobs"
    And   I fill in search title field with "Sales"
    And   I fill in search location with "Brooklyn, NY"
    When  I click on Find Jobs search button
    And   I should not see link "Save" with id "save_toggle_20"
    And   I should see link "Save" with id "save_toggle_14"
    And   I should see link "More" with id "more_14"
    And   I should see link "Apply now" with id "apply_now_14"
    And   I should see link "Apply now" with id "apply-now-partner-jobs-5"
    And   I should see text "Next"
    Then  I click on hyper link "Next"
    And   I should not see link "Save" with id "save_toggle_20"
    And   I should see link "Save" with id "save_toggle_14"
    And   I should see link "More" with id "more_14"
    And   I should see link "Apply now" with id "apply-now-partner-jobs-5"
    Then  I should be able to see in browser URL "Jobs Sales In Brooklyn Ny Page Number 2"

  @searchPartnerJobLoggedInWhenMoreRLJobs50PerPage
  Scenario: Search job page logged in, displaying partner jobs when > 44 RL jobs and 50 per page
    When  I browse the url "Search Jobs Sales In New York City Ny Per Page 50"
    And   I should see link "Save" with id "save_toggle_45"
    And   I should see link "View more" with id "more_45"
    And   I should see link "Apply now" with id "apply_now_45"
    And   I should not see link "Save" with id "save_toggle_50"
    And   I should see link "Apply now" with id "apply-now-partner-jobs-3"

  @searchPartnerJobLoggedInWhenMoreRLJobs75PerPage @ReleaseRegression1
  Scenario: Search job page logged in, displaying partner jobs when > 69 RL jobs and 75 per page
    When  I browse the url "Search Jobs Sales In New York City Ny Per Page 75"
    And   I should see link "Save" with id "save_toggle_70"
    And   I should see link "View more" with id "more_70"
    And   I should see link "Apply now" with id "apply_now_70"
    And   I should not see link "Save" with id "save_toggle_71"
    And   I should see link "Apply now" with id "apply-now-partner-jobs-5"

  @searchPartnerJobLoggedInWhenMoreRLJobs100PerPage
  Scenario: Search job page logged in, displaying partner jobs when > 94 RL jobs and 100 per page
    When  I browse the url "Search Jobs Sales In New York City Ny Per Page 100"
    And   I should see link "Save" with id "save_toggle_95"
    And   I should see link "View more" with id "more_95"
    And   I should see link "Apply now" with id "apply_now_95"
    And   I should not see link "Save" with id "save_toggle_96"
    And   I should see link "Apply now" with id "apply-now-partner-jobs-5"

  @searchJobLoggedInJobMatches @ReleaseRegression1
  Scenario: As a logged in user, modify profile and verify 'View more job matches'
    When  I navigate to page "Candidate My Profile"
    And   I click on "Personal Information" edit button
    And   I fill in the first name with "Resume-Library update"
    And   I click on Save Changes Button on Modify Profile Page
    And   I should see element with text "Your details have been successfully updated" to be "visible"
    And   I click on "Personal Information" edit button
    And   I fill in the first name with "Resume-Library"
    And   I click on Save Changes Button on Modify Profile Page
    And   I should see element with text "Your details have been successfully updated" to be "visible"
    And   I should see element with text "Job Requirements" to be "visible"
    And   I click on "Job Requirements" edit button
    And   I fill in the desired job title with "Sales Executive"
    And   I click on Save Changes Button on Modify Profile Page
    And   I should see element with text "Your details have been successfully updated" to be "visible"
    When  I navigate to page "Search Jobs"
    Then  I should see "Hi Resume-library, here are some job matches you might like…"
    And   I should see text "View more job matches"
    When  I navigate to page "Candidate My Profile"
    And   I click on "Job Requirements" edit button
    And   I enter desired job title "NonExternalTestJob" on modify profile page
    And   I click on Save Changes Button on Update Profile Page
    And   I should see text "Your details have been successfully updated"
    And   I navigate to page "Search Jobs"
    Then  I click on "View more job matches"
    And   I should be able to see in browser URL "Jobs Nonexternaltestjob In 90189 R 51"
    And   I should see text "Nonexternaltestjob jobs in 90189"


  @hideJobsLoggedInReasonOne @ReleaseRegression1
  Scenario: As a logged in user, verify 'Hide Jobs' functionality
    And   I navigate to page "Search Jobs"
    And   I fill in search title field with "Sales"
    And   I fill in search location with "Brooklyn, NY"
    When  I click on Find Jobs search button
    And   I should be on page "Jobs"
    And   I click on id starts with "search_hide_result"
    Then  I should see text "This job will be hidden from all searches"
    When  I click on id starts with "search_hide_tell_us"
    Then  I should see text "I've already applied or viewed this job"
    And   I should see text "It's irrelevant"
    And   I should see text "Other"
    When  I click on id ends with "_0_0"
    Then  I should see "Thank you for your feedback"

  @hideJobsLoggedInReasonTwo @ReleaseRegression1
  Scenario: As a logged in user, verify 'Hide Jobs' functionality
    And   I navigate to page "Search Jobs"
    And   I fill in search title field with "Sales"
    And   I fill in search location with "Brooklyn, NY"
    When  I click on Find Jobs search button
    And   I should be on page "Jobs"
    And   I click on id starts with "search_hide_result"
    Then  I should see text "This job will be hidden from all searches"
    When  I click on id starts with "search_hide_tell_us"
    Then  I should see text "I've already applied or viewed this job"
    And   I should see text "It's irrelevant"
    And   I should see text "Other"
    When  I click on id ends with "_1"
    Then  I should see "Thank you for your feedback"

  @hideJobsLoggedInReasonOther
  Scenario: As a logged in user, verify 'Hide Jobs' functionality
    And   I navigate to page "Search Jobs"
    And   I fill in search title field with "Sales"
    And   I fill in search location with "Brooklyn, NY"
    When  I click on Find Jobs search button
    And   I should be on page "Jobs"
    And   I click on id starts with "search_hide_result"
    Then  I should see text "This job will be hidden from all searches"
    When  I click on id starts with "search_hide_tell_us"
    Then  I should see text "I've already applied or viewed this job"
    And   I should see text "It's irrelevant"
    And   I should see text "Other"
    When  I click on id ends with "_2"
    Then  I should see "Tell us more about why you don't want to see this job"
    When  I enter reasons other input "Regression testing"
    And   I click on "Submit" button
    Then  I should see "Thank you for your feedback"

  @hideJobsLoggedInUndo @ReleaseRegression1
  Scenario: As a logged in user, verify 'Hide Jobs' functionality
    And   I navigate to page "Search Jobs"
    And   I fill in search title field with "Sales"
    And   I fill in search location with "Brooklyn, NY"
    When  I click on Find Jobs search button
    And   I should be on page "Jobs"
    And   I click on id starts with "search_hide_result"
    Then  I should see text "This job will be hidden from all searches"
    When  I click on id starts with "search_hide_tell_us"
    Then  I should see text "I've already applied or viewed this job"
    And   I should see text "It's irrelevant"
    And   I should see text "Other"
    Then  I click on id starts with "search_hide_undo"