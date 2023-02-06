@Resources @Regression @Web  @Traffic
Feature: Client Resources Resources

  @loadResources @ReleaseRegression2 @MobileChrome
  Scenario: Load the resources page
    Given  I navigate to page "Resources"
    Then   I should see resume Library Employers header logo
    When   I click on the RL Employers header logo
    Then   I should be on page "Hiring"
    When   I navigate to page "Resources"
    Then   I should see "Resume Search" link
    Then   I should see "Post Jobs" link
    Then   I should see "Products" link
    And    I should see "Login" link
    And    I should see "Hire Now" link
    And    I should see "Find Jobs" link
    Then   I should see "Resources" link
    When   I mouse hover Resources Navigation menu
    Then   I should see "Demographics" link
    Then   I should see "Integrations" link
    Then   I should see "Hiring Insights" link
    Then   I should see "Events" link
    Then   I should see "Video Guides" link
    And    I click on Menu Bar
    Then   I click on "Resources"
    Then   I click on link text "Demographics"
    Then   I should be able to see in browser URL "Hiring Demographics"
    When   I mouse hover Resources Navigation menu
    And    I click on Menu Bar
    Then   I click on "Resources"
    And    I click on "Integrations"
    Then   I should be able to see in browser URL "Partner Integrations"
    When   I click on Menu Bar
    Then   I click on "Resources"
    And    I click on "Hiring Insights"
    Then   I should be able to see in browser URL "Resources"
    When   I click on Menu Bar
    Then   I click on "Resources"
    And    I click on "Events"
    Then   I should be able to see in browser http URL "Resources Events"
    When   I click on Menu Bar
    Then   I click on "Resources"
    And    I click on "Video Guides"
    Then   I should be able to see in browser URL "Hiring Video Guides"

  @verifyCategories @MobileChrome
  Scenario: Verify the elements
    Given I navigate to page "Resources"
    Then  I should see text "Latest Articles"
    And   I should see text "Attracting Candidates"
    And   I should see text "Interviewing Tips"
    And   I should see text "Job Market Insights"
    And   I should see text "Making The Hire"
    And   I should see text "Workplace"
    When  I am on page "Resources Latest"
    And   I click on "Next"
    Then  I should see text H one tag "Latest"
    Then  I should see text "Categories"
    Then  I should see text "Latest Articles"

  @ResourcesPageLoggedOutClientBanner @MobileChrome
    #TODO This page is not displaying images. see later
  Scenario: As a logged out client user I see the resources banner for resume search and for jobs.
    Given  I navigate to page "Resources"
    Then   I scroll down 0,600
    And    I should see resources-switch-banner "Resume Search"
    Then   I should be able to see in browser http URL "Hiring Resume Search"
    When   I navigate to page "Resources"
    Then   I scroll down 0,600
    And    I should see resources-switch-banner "Post A Job"
    Then   I should be able to see in browser http URL "Hiring Post Jobs"

  @ResourcesDropNavigation @ReleaseRegression2 @MobileChrome
  Scenario: As a logged out user I see the resources page drop navigation has links
    Given  I navigate to page "Resources"
    Then   I click on "View Categories"
    Then   I should see text "Home"
    Then   I should see text "Attracting Candidates"
    Then   I should see text "Interviewing Tips"
    Then   I should see text "Job Market Insights"
    Then   I should see text "Making The Hire"
    Then   I should see text "Workplace"
    Then   I should see text "News"
    Then   I should see text "Events"

    When   I click on "Attracting Candidates"
    Then   I should be on page "Resources Attracting Candidates"
    Then   I should see text H one tag "Attracting Candidates"

    When   I navigate to page "Resources"
    Then   I click on "View Categories"
    And    I click on "Interviewing Tips"
    Then   I should be on page "Resources Interviewing Tips"
    Then   I should see text H one tag "Interviewing Tips"

    When   I navigate to page "Resources"
    Then   I click on "View Categories"
    And    I click on "Job Market Insights"
    Then   I should be on page "Resources Job Market Insights"
    Then   I should see text H one tag "Job Market Insights"

    When   I navigate to page "Resources"
    Then   I click on "View Categories"
    And    I click on "Making The Hire"
    Then   I should be on page "Resources Making The Hire"
    Then   I should see text H one tag "Making The Hire"

    When   I navigate to page "Resources"
    Then   I click on "View Categories"
    And    I click on "Workplace"
    Then   I should be on page "Resources Workplace"
    Then   I should see text H one tag "Workplace"

    When   I navigate to page "Resources"
    Then   I click on "View Categories"
    And    I click on "News"
    Then   I should be on page "Resources News"
    Then   I should see text H one tag "News"

    When   I navigate to page "Resources"
    Then   I click on "View Categories"
    And    I click on "Events"
    Then   I should be on page "Resources Events"
    Then   I should see text H one tag "Events"


  @ResourcesPageLoggedOutBanner @MobileChrome
    #TODO This page is not displaying images. see later
  Scenario: As a logged out user I see the resources banner for resume search and for jobs.
    Given  I navigate to page "Resources"
    Then   I scroll down 0,600
    And    I should see resources-switch-banner "Resume Search"
    Then   I should be able to see in browser http URL "Hiring Resume Search"
    When   I navigate to page "Resources"
    Then   I scroll down 0,600
    And    I should see resources-switch-banner "Post A Job"
    Then   I should be able to see in browser http URL "Hiring Post Jobs"

  @ResourcesPageLoggedInChatWindow @MobileChrome
    #TODO This page is not displaying images. see later
  Scenario: As a logged out user I see the resources navigation bar has  Live chat button
    Given  I login as a client
    Then   I navigate to page "Resources"
    Then   I should see transparent-button envelope is displayed
    When   I click on the envelope
    Then   I should see "Contact Us" popup displayed