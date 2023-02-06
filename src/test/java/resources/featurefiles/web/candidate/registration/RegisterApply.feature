@RegisterAndApply  @Regression @Web  @Traffic
Feature: Candidate Registration RegisterApply

  @candidateRegisterApply @ReleaseRegression1 @MobileChrome
  Scenario Outline: Register and Apply - Successful registration
    Given I navigate to page "Jobs Non External Test Jobs"
    When  I click on non external job to apply
    And   I Enters Firstname as "<FirstName>"
    And   I Enters Lastname as "<LastName>"
    And   I fill candidate email with random EmailId
    And   I fill job apply password with "<Password>" on job apply
    And   I upload resume "<Resume>"
    And   I wait for "1" seconds
    And   I uncheck the label Claim my free professional Resume Review one
    And   I wait for "1" seconds
    And   I click on "Register & apply" with JS
    Then  I should be able to see in browser URL "Job Apply"
    When  I enter latest job title "<JobTitle>"
    And   I enter zip code "<ZipCode>"
    And   I enter phone number "<Phone>"
    And   I click on send application button
    And   I should be able to see in browser URL "Job Apply"
    And   I should see text "Application Sent - the employer will be in touch if you are successful."
    And   I should see text "Increase your chances of getting hired"

    Examples:
      | Email                                        | FirstName | LastName | Password | Resume  | JobTitle     | ZipCode | Phone        |
      | automation-register-apply@resume-library.com | Bob       | Russel   | 123456   | Test Cv | Test Analyst | 50001   | 012345678912 |

  @registerApplyResumeMandatory @MobileChrome
  Scenario: Register and Apply - Resume mandatory when 'Resume required on registration' partner setting is turned on
    Given  I navigate to page "Jobs Non External Test Jobs"
    Then   I set cookie with "ibpid" and "975438"
    And    I reload the page
    When   I click on non external job to apply
    And    I Click on Link Register & Apply
    Then   I should see text "Resume is required"

  @registerApplyResumeNotMandatory @MobileChrome
  Scenario: Register and Apply - Resume NOT mandatory when 'Resume required on registration' partner setting is turned off
    Given  I navigate to page "Jobs Non External Test Jobs"
    Then   I set cookie with "ibpid" and "975155"
    And    I reload the page
    When   I click on non external job to apply
    And    I click on "Register & Apply" with JS
    And    I should not see "Resume is required" text

  @registerApplyNoIbpidSet @MobileChrome
  Scenario: Register and Apply where IBPID cookie is not set
    Given  I navigate to page "Jobs Non External Test Jobs"
    And    I reload the page
    When   I click on non external job to apply
    And    I wait for "1" seconds
    And    I Click on Link Register & Apply
    And    I should not see "Resume is required" text