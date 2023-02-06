@CSRFToken @Regression @Web  @Traffic @MobileChrome
Feature: Candidate Others CSRF

  Scenario: Testing CSRF token session validation on candidate login
    Given   I am on home page
    Then    I click on Menu Bar
    When    I click Login button
    When    I find value "csrf_rl" and store as a variable
    Then    I click on Menu Bar
    And     I login as a candidate
    And     I get cookie value with name "csrf_rl" and store as a variable
    And     I compare both variable to verify its not the same

  Scenario: Testing CSRF token session validation on client login
    Given   I am on home page
    Then    I click on Menu Bar
    When    I click Login button
    Then    I click Employer login
    When    I find value "csrf_rl" and store as a variable
    Then    I login as a client
    And     I get cookie value with name "csrf_rl" and store as a variable