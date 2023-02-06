@ResourcesPageLoggedInClient @Regression @Web  @Traffic
Feature: Client Resources ResourcesLoggedIn

  Background: Login as a client to verify resources page
    Given I login as a client

  @ResourcesPageLoggedInHeaders1 @MobileChrome
  Scenario: As a registered user logged in and verify the navigation menu
    When  I navigate to page "Resources"
    Then  I should see resume Library Employers header logo
    And   I click on Menu Bar
    And   I should see text "Resume library"
    Then  I should see text "Dashboard"
    And   I should see text "Manage Jobs"
    And   I should see text "Resume Search"
    And   I should see text "Resume Alerts"
    And   I should see text "Company Profile"
    And   I should see text "Products"

    When  I click on "Resume Search"
    Then  I should see text "Search Resumes"
    When  I click on link text "Search Resumes"
    Then  I reload the page
    And   I wait for "1" seconds
    Then  I should see element with text "Resume Search" to be "visible"
    Then  I should be able to see in browser URL "Client Resume Search"

    When  I click on Menu Bar
    Then  I should see text "Saved Searches"
    When  I click on "Saved Searches"
    And   I should see element with text "Saved Resume Searches" to be "visible"
    Then  I should be able to see in browser URL "Client Saved Search"

    When  I click on Menu Bar
    Then  I should see text "Saved Resumes"
    When  I click on "Saved Resumes"
    And   I should see element with text "My Saved Resumes" to be "visible"
    Then  I should be able to see in browser URL "Client My Saved Resumes"

    When  I mouse hover to link "Resume Search"
    And   I should see text "Unlocked Resumes"
    And   I click on Menu Bar
    When  I click on "Unlocked Resumes"
    And   I should see element with text "My Unlocked Resumes" to be "visible"
    Then  I should be able to see in browser URL "Client My Unlocked Resumes"

    When  I mouse hover to link "Resume Search"
    And   I should see text "Popular Searches"
    And   I click on Menu Bar
    When  I click on "Popular Searches"
    And   I should see element with text "Select your industry" to be "visible"
    Then  I should be able to see in browser URL "Client Resume Search"

  @ResourcesPageLoggedInHeaders2 @MobileChrome
  Scenario: As a registered user logged in and verify resume search menu
    When  I navigate to page "Resources"

    When  I click on Menu Bar
    And   I click on "Manage Jobs"
    Then  I should see text "Manage Jobs"
    When  I click on Manage Jobs
    Then  I should be able to see in browser URL "Client Jobs"

    When  I click on Menu Bar
    And   I should see text "Post a Job Now"
    When  I click on "Post a Job Now"
    Then  I should be on page "Job Post"

    When  I click on Menu Bar
    And   I should see text "Job Applications"
    When  I click on "Job Applications"
    Then  I should be able to see in browser URL "Client Job Applications"

    When  I click on Menu Bar
    And   I should see text "Manage Job Templates"
    When  I click on "Manage Job Templates"
    Then  I should be able to see in browser URL "Client Jobs Templates"

  @ResourcesPageLoggedInHeaders3 @MobileChrome
  Scenario: As a registered user logged in and verify manage jobs menu
    When  I navigate to page "Resources"
    And   I click on Menu Bar
    When  I click on "Resume Alerts"
    Then  I should see text "Resume Alerts"
    When  I click on Resume Alerts
    Then  I should be able to see in browser URL "Client Resume Alerts"

    And   I click on Menu Bar
    When  I click on "Resume Alerts"
    And   I should see text "Create New Alert"
    When  I click on Create New Alert
    Then  I should be able to see in browser URL "Client Resume Alert Create"

    And   I click on Menu Bar
    When  I click on "Resume Alerts"
    And   I should see text "View Sent Alerts"
    When  I click on "View Sent Alerts"
    Then  I should be able to see in browser URL "Client Resume Alerts Log"

  @ResourcesPageLoggedInHeaders4 @MobileChrome
  Scenario: As a registered user logged in and verify products menu
    When  I navigate to page "Resources"
    And   I click on Menu Bar

    When  I click on "Products"
    Then  I should see text "Contact Credits"
    When  I click on "Contact Credits"
    Then  I should be able to see in browser URL "Client Products Contact Credits"

    And   I click on Menu Bar
    When  I mouse hover Products Navigation menu
    And   I should see text "Job Postings"
    When  I click on "Job Postings"
    Then  I should be able to see in browser URL "Client Products Jobs"

  @ResourcesPageLoggedInHeaders5
  Scenario: As a registered user logged in and verify profile menu
    When  I navigate to page "Resources"
    And   I click on Menu Bar

    When  I click on "Company Profile"
    Then  I should see text "Edit Company Profile"
    When  I click on "Edit Company Profile"
    Then  I should be able to see in browser URL "Client Company Profile"

    And   I click on Menu Bar
    Then  I should see text "Edit Company Profile"
    When  I navigate to page "Client Company Profile"
    Then  I should be able to see in browser URL "Client Company Profile"

    When  I click on Menu Bar
    And   I should see text "Integrated Services"
    When  I click on "Integrated Services"
    Then  I should be able to see in browser URL "Client Account Integrated Services"

    When  I click on Menu Bar
    Then  I should see text "Contact Us"
    When  I click on "Contact Us"
    And   I should see text "Your Customer Success Executive"
    And   I should see text "Logout"

  @ResourcesDropNavigationLoggedIn @ReleaseRegression2 @MobileChrome
  Scenario: As a logged in user I see the resources page drop navigation has links
    Given  I navigate to page "Resources"

    Then   I should see text "Home"
    When   I click on "View Categories"
    Then   I should see text "Attracting Candidates"
    Then   I should see text "Interviewing Tips"
    Then   I should see text "Job Market Insights"
    Then   I should see text "Making The Hire"
    Then   I should see text "Workplace"
    Then   I should see text "News"
    Then   I should see text "Events"

    When   I click on link text "Attracting Candidates"
    Then   I should be on page "Resources Attracting Candidates"
    Then   I should see text H one tag "Attracting Candidates"

    When   I move backward one page
    And    I click on link text "Interviewing Tips"
    Then   I should be on page "Resources Interviewing Tips"
    Then   I should see text H one tag "Interviewing Tips"

    When   I move backward one page
    And    I click on link text "Job Market Insights"
    Then   I should be on page "Resources Job Market Insights"
    Then   I should see text H one tag "Job Market Insights"

    When   I move backward one page
    And    I click on link text "Making The Hire"
    Then   I should be on page "Resources Making The Hire"
    Then   I should see text H one tag "Making The Hire"

    When   I move backward one page
    And    I click on link text "Workplace"
    Then   I should be on page "Resources Workplace"
    Then   I should see text H one tag "Workplace"

    When   I move backward one page
    And    I click on link text "News"
    Then   I should be on page "Resources News"
    Then   I should see text H one tag "News"
    When   I move backward one page

    And    I click on link text "Events"
    Then   I should be on page "Resources Events"
    Then   I should see text H one tag "Events"
