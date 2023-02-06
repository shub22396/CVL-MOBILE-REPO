@NoEcomUnvalidatedClientPurchase @Regression @Web  @Recruiters @MobileChrome
Feature: Client NoEcommerceJourney UnValidatedProductsJourney

  Background:
    Given I navigate to page "Client Registration"
    And   I enter first name "automation"
    And   I enter last name "client"
    And   I enter password "123456"
    And   I enter client company name "resume-library"
    And   I enter phone number "012345678912"
    And   I click on enter address manually link
    And   I enter address one "1st street"
    And   I enter city "Boston"
    And   I enter state "Massachusetts"
    And   I enter zip code "02108"
    And   I select "recruiter_type.1" from recruiter type
    When  I enter client email address "registration@resume-library.com"
    And   I click on Submit inquiry
    Then  I should be on page "Client Account"
    And   I should see text "Popular Products"
    And   I login as an admin user
    And   I click on menu
    When  I search for "registration@resume-library.com" in the navigation bar on admin
    And   I click on product details Edit button
    And   I should see element with id "disable_ecomm" to be "visible"
    And   I scroll down 0,700
    And   I wait for "2" seconds
    And   I select Disable Ecomm "Yes"
    And   I click on update account

  @NoEcomUnvalidatedClientPurchaseProducts @MobileChrome
  Scenario: As an unvalidated logged in client with E-commerce disabled I do not want to see products option
    When  I navigate to page "Client Account"
    And   I click on Menu Bar
    And   I should see text "Products"
    Then  I should not see text "Contact Credits"
    And   I should not see text "Job postings"
    When  I click on "Products"
#    Then  I should be able to see in browser URL "Hiring Products"
#    And   I should see text H one tag "Our Hiring Products"

    #XT-1487  logged In Headers -Unvalidated ecomm disabled
    When  I navigate to page "Resources"
    And   I click on Menu Bar
    And   I should see text "Products"
    Then  I should not see text "Contact Credits"
    And   I should not see text "Job postings"
    When  I click on "Products"
    #Then  I should be able to see in browser URL "Hiring Products"

  @NoEcommUnvalidatedClientVerifyEmployersLogo @MobileChrome
  Scenario: as an unvalidated logged in client with E-commerce disabled verify employers logo
    When  I navigate to page "Client Account"
    When  I click on the RL Employers header logo
    Then  I should be on page "Hiring"

  @NoEcommUnvalidatedClientInviteToApply2
  Scenario: as an unvalidated logged in client with E-commerce disabled verify resume search select all checkbox invite to apply
    When  I navigate to page "Client Resume Search With Keywords"
    And   I should be able to see in browser URL "Client Resume Search Results"
    Then  I should see text "Invite Candidates"
    And   I should see text "Select all " on the page source
    When  I tick Select all checkbox
    Then  I should see all Resume(s) selected
    And   I select option "Invite to Apply" from resumes selected
    Then  I should see text "To purchase this product, contact your account manager on:"
    Then  I should see text "1-800-672-6706"
    And   I should see text "sales@resume-library.com"