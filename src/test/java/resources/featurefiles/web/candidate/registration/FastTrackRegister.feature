@FastTrackRegistration  @Regression @Web  @Traffic
Feature: Candidate Registration FastTrackRegister

  Background: Register as a candidate using fast track form
    Given  I navigate to page "Jobs Non External Test Jobs"
    Then   I set cookie with "ibpid" and "56079"
    And    I reload the page
    #we need the below wait, it was failing so do not remove

  @fastTrackFromSearchResults @ReleaseRegression1 @MobileChrome
  Scenario: Register as a new candidate from search results
    When I click on Apply now Link
    And  I fill candidate email field with random EmailId
    And  I Enters Firstname as "Mike"
    And  I Enters Lastname as "Bob"
    And  I Enters Password "Passw0rd"
    And  I click on "Register & Apply" with JS
    Then I should be able to see in browser URL "Job Apply"

  @fastTrackFromJobView @ReleaseRegression1 @MobileChrome
  Scenario: Register as a new candidate from job view page
    And  I Click on Job Title
    And  I switch tab
    Then  I reload the page
    And  I click on "Apply now"
    And  I fill candidate email field with random EmailId
    And  I Enters Firstname as "Automation"
    And  I Enters Lastname as "Tester"
    And  I Enters Password "rltest01"
    And  I click on "Register & Apply" with JS
    And  I wait for "1" seconds
    Then I should be able to see in browser URL "Job Apply"

  @fastTrackFormValidation @MobileChrome
  Scenario: Validate fast track registration form
    Then I click on "View more"
    Then I click on Apply now Link
    And  I should see text "Apply for"
    And  I should see text "Already have an account?"
    When I follow link Login
    And  I Click on Link Register & Apply
    Then I should see text "Email is required"
    Then I should see text "Password is required"
    And  I Click on Create a jobseeker account link
    When I Click on Link Register & Apply
    Then I should see text "Email is required"
    Then I should see text "First name is required"
    Then I should see text "Last name is required"
    Then I should see text "Password is required"
    And  I should not see "Resume is required" text

  @fastTrackFromCompanyProfile @ReleaseRegression1 @MobileChrome
  Scenario: Register as a new candidate from company profile
    When I am on page "/company/1478/acme-company-no-1728-jobs"
    And  I click on Apply now Link
    And  I fill candidate email with random EmailId
    And  I Enters Firstname as "Automation"
    And  I Enters Lastname as "Tester"
    And  I Enters Password "rltest01"
    And  I upload resume "Test Cv"
    And  I uncheck the label Claim my free professional Resume Review one
    And  I Click on Link Register & Quick Apply
    Then I should be able to see in browser URL "Job Apply"
    And  I should not see element with id "apply_view_more" on the page

  @fastTrackRegistrationResumeMandatory @MobileChrome
  Scenario: Fast track register - Resume mandatory when 'Resume required on registration' partner setting is turned on
    Given  I set cookie with "ibpid" and "975438"
    And    I reload the page
    And    I click on Apply now Link
    And    I click on "Register & apply" with JS
    Then   I should see text "Upload Resume"
