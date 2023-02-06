@ResumeSearchOtherElements @Regression @Web  @Search @MobileChrome
Feature: Client ResumeSearch OtherElements

  Background: On resume search page
    Given I login as a client
    Then  I navigate to page "Client Saved Search"
    Then  I delete saved search
    When  I navigate to page "Client Resume Search"

  @mySearchesSavedSearchesTab @ReleaseRegression2
  Scenario: Verify searches in My Searches
    When  I click on my searches link
    Then  I should see text H three tag "My searches"
    And   I should see text "Recent searches"
    And   I should see text "Saved searches"
    And   I click on "Saved searches"
    Then  I should see text "You haven't saved any searches yet" or delete the search
    When  I click on "Start searching"
    Then  I should be able to see in browser URL "Client Resume Search"
    And   I fill in basic search keywords with "test analyst"
    And   I enter location with "10010"
    And   I click on Search Resumes
    And   I wait for "2" seconds
    When  I click on id "save_this_search"
    Then  I should see text H three tag "Save this Search"
    When  I click on Save search submit button
    Then  I should see "Please enter a search name"
    When  I enter saved search name with "test analyst"
    And   I click on Save search submit button
    Then  I should see text "Search saved successfully!"
    And   I should see text "Search Saved"
    When  I click on my searches link
    Then  I click on tab Saved searches
    And   I should see text p tag "Displaying " and verify message "Displaying 1 to 1 of 1"
    When  I navigate to page "Client Saved Search"
    And   I click on saved search actions one
    And   I click on link text "Delete"
    Then  I click on save search delete btn
    And   I should see text "Search removed successfully"

  @popularSearches
  Scenario: Verify Popular Searches
    When  I click on Popular searches link
    Then  I click on "Agriculture"
    When  I click on "Farm Worker"
    Then  I click on "Add to search"
    And   I should be able to see in browser URL "Client Resume Search"
    Then  the keyword field contains "farm worker" and "farm assistant"

  @resetForm
  Scenario: Verify Reset form
    When  I fill in basic search keywords with "Sales"
    And   I enter location with "10010"
    Then  I click on Reset form
    And   I click on Search Resumes
    Then  I should see "No Search Criteria"

