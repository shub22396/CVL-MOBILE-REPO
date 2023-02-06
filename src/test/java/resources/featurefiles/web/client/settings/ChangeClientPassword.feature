@ClientChangePassword @Regression @Web  @ReleaseRegression2 @Traffic
Feature: Client Settings ChangeClientPassword

  @resetClientPassword @MobileChrome
  Scenario: Reset client password
    Given I login as a client
    When  I click on "Account Options"
    And   I wait for "1" seconds
    And   I should see text "Change Password"
    And   I wait for "1" seconds
    When  I click on "Change Password"
    Then  I should be able to see in browser URL "User Change Password"
    Then  I should see text "Change My Password"
    When  I click on "Change my password"
    Then  I should see text "Old password is required"
    And   I should see text "New password is required"
    And   I should see text "Confirm your new password"
    When  I fill in old password "rltest01"
    And   I fill in new password "rltes"
    And   I should see text "Your new password must be at least 6 characters long"
    When  I fill in old password "rltest01"
    And   I fill in new password "rltest01"
    And   I fill in confirm new password "rltest02"
    And   I should see text "New passwords do not match"
    When  I fill in old password "rltest02"
    And   I fill in new password "rltest01"
    And   I fill in confirm new password "rltest01"
    Then  I click on "Change my password" with JS
    And   I should see text "Your old password is incorrect. Please try again"
    When  I fill in old password "rltest01"
    And   I fill in new password "rltest01"
    And   I fill in confirm new password "rltest01"
    Then  I click on "Change my password" with JS
    And   I should see text "Your password has been successfully updated. You will need to use your new password the next time you login."