@HiringPage @Regression @Web  @Traffic
Feature: Client Others HiringPage

  Background:
    Given I navigate to page "Hiring"
    And   I reload the page

  @AdvertisingPrevNextIcon @ReleaseRegression2 @MobileChrome
  Scenario: Advertising highlights Prev and next icon
    Then  I should see text "Attracting millions of jobseekers in 2022"
    And   I should see text "We're reaching more jobseekers than ever before with our outdoor advertising campaigns across key states including New York, California, Georgia and Illinois."
    And   I should see text p tag "With clear messaging and a standout format, this advertising can be seen by " and verify message "With clear messaging and a standout format, this advertising can be seen by millions of commuters in hand-picked, high footfall locations across the country."
    And   I mouse hover "Chicago"
    And   I should see text "Chicago"
    And   I wait for "1" seconds
    When  I click on next icon
    Then  I should see text "Dallas"
    And   I wait for "1" seconds
    When  I click on prev icon
    Then  I should see text "Dallas"

  @AdvertisingVariousLocations @MobileChrome
  Scenario: Advertising highlights in Various Locations
    And   I mouse hover "Atlanta"
    And   I should see text "Atlanta"
    And   I wait for "1" seconds
    When  I click on slide "2" where title is "Boston"
    Then  I should see text "Boston"
    And   I wait for "1" seconds
    When  I click on slide "3" where title is "Chicago"
    Then  I should see text "Chicago"
    And   I wait for "1" seconds
    When  I click on slide "4" where title is "Chicago"
    Then  I should see text "Chicago"
    And   I wait for "1" seconds
    When  I click on slide "5" where title is "Dallas"
    Then  I should see text "Dallas"
    And   I wait for "1" seconds
    When  I click on slide "6" where title is "Los Angeles"
    Then  I should see text "Los Angeles"
    And   I wait for "1" seconds
    When  I click on slide "7" where title is "Los Angeles"
    Then  I should see text "Los Angeles"
    And   I wait for "1" seconds
    When  I click on slide "8" where title is "Miami"
    Then  I should see text "Miami"
    And   I wait for "1" seconds
    When  I click on slide "9" where title is "New York"
    Then  I should see text "New York"
    And   I wait for "1" seconds
    When  I click on slide "10" where title is "New York"
    Then  I should see text "New York"
    And   I wait for "1" seconds
    When  I click on slide "11" where title is "New York"
    Then  I should see text "New York"
    And   I wait for "1" seconds
    When  I click on slide "12" where title is "New York"
    Then  I should see text "New York"
