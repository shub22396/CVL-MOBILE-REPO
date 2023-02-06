@InquireProducts @Regression @Web  @Recruiters @MobileChrome
Feature: Client Products InquireProducts

  Background: Login as a client
    Given   I login as a client

  @InquireProductContactCredits
  Scenario: As a logged In user validating the Inquire Now link for Contact Credits Product
    When   I click on link text "Resources"
    And    I click on Menu Bar
    Then   I should see text "Products"
    And    I move backward one page
    And    I click on Menu Bar
    When   I click on "Products"
    Then   I should see text "Buy Products"
    Then   I should see text "Contact Credits"
    Then   I should see text "Job Postings"
    And    I click on "Contact Credits"
    Then   I should be able to see in browser URL "Client Products Contact Credits"
    When   I click on "Inquire Now"
    Then   I should be able to see in browser URL "Client Contact"
  #  And    I select the option "Resume Search" from inquiry type