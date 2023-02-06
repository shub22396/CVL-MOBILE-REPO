@ModifyResumeUpdate @Regression @Web  @ReleaseRegression1 @Candidate
Feature: Candidate ModifyProfile UpdateResume

#  Background: On modify profile page
#    Given  I navigate to page "Candidate Registration"
#    And   I register as a new candidate
#    When I navigate to page "Candidate My Profile"

  @modifyProfileChangeResume
  Scenario: Modify profile change resume
    Given I login as a candidate
    And I navigate to page "Candidate My Profile"
    And I upload resume "Test Cv"
    And I wait for "4" seconds
    Then I should see message "Save changes"
     And I click on save changes button
    And I wait for "5" seconds
    Then I should see message "Your details have been successfully updated"
    #Then I should see text "Your resume is currently processing, you will be able to apply for jobs in just a few moments."