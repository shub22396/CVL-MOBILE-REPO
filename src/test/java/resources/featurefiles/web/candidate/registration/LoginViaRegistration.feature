  @LoginViaRegistration @Regression @Web  @Traffic
  Feature: Candidate Registration LoginViaRegistration

    @registerApplyLogin @MobileChrome
    Scenario: Login as an existing candidate via Register and Apply
     Given  I navigate to page "Jobs Non External Test Jobs"
      And   I click on non external job to apply
      And   I click on "Register & Apply" with JS
      And   I fill candidate email with "testers+candidate@resume-library.com"
      And   I fill job apply password with "rl" on job apply
      Then  I should see text "This email already exists, please login"
      When  I click on Menu Bar
      Then  I click on "Login"
      And   I enter candidate email address "testers+candidate@resume-library.com"
      And   I fill in the field where id is "pass" with: "rltest01"
      When  I click on "Login as Jobseeker" with JS
      Then  I click on apply now link
      Then  I should see text p tag replace all "Resume-Library, you're applying for:"
      And   I should be able to see in browser URL "Job Apply"