@HttpStatusCode @Regression @Web @Misc @Traffic
Feature: Misc HttpStatusCode

  @SearchJobsVerify500status @MobileChrome
  Scenario: Verify 500 status code page
    Given  I navigate to page "Page 500 Error"
    Then    the response code should be 200
    And     I should see "ERROR 500 - Internal Server Error" on the page
    And     I should see text "The request was not completed. The server met an unexpected condition."
    And     I should see text p tag "We may be down for maintenance, however if the problem persists please contact " and verify message "We may be down for maintenance, however if the problem persists please contact support@resume-library.com to report the problem."

  @SearchJobsVerify403status @MobileChrome
  Scenario: Verify 403 status code page
    Given  I navigate to page "Page 403 Error"
    Then    the response code should be 200
    And     I should see "ERROR 403 - Forbidden" on the page
    And     I should see text "Access is forbidden to the requested page."
    And     I should see text p tag "We may be down for maintenance, however if the problem persists please contact " and verify message "We may be down for maintenance, however if the problem persists please contact support@resume-library.com to report the problem."

  @SearchJobsNavigationBar403Page @MobileChrome
  Scenario: Search for a job from search jobs navigation bar 403 page
    Given I navigate to page "Page 403 Error"
    And   The search jobs navigation bar button has text "Find jobs"
    When  I click on id "mobile-search-trigger"
    And   I fill in input with "Sales" where the the element id is "header_q"
    And   I fill in input with "10001" where the the element id is "header_loc"
    And   I select the option "60" from dropdown with id "header_radius"
    And   I click on "Find jobs"
    Then  I should be able to see in browser URL "/jobs/sales-in-10001?r=60"
    And   I should see text H one tag "Sales jobs"


  @SearchJobsNavigationBarMaintenancePage @MobileChrome
  Scenario: Search for a job from search jobs navigation bar maintenance page
    Given I navigate to page "Page Maintenance"
    And    The search jobs navigation bar button has text "Find jobs"
    When  I click on id "mobile-search-trigger"
    And   I fill in input with "Sales" where the the element id is "header_q"
    And   I fill in input with "10001" where the the element id is "header_loc"
    And   I select the option "60" from dropdown with id "header_radius"
    And   I click on "Find jobs"
    Then  I should be able to see in browser URL "/jobs/sales-in-10001?r=60"
    And   I should see text H one tag "Sales jobs"