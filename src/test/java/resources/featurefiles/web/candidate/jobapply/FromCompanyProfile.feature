@JobApplyCompanyProfile @Regression @Web  @Candidate
Feature: Candidate JobApply FromCompanyProfile

  #Scenario: A user navigates to a company profile while not logged into an account. The user clicks to apply for the
  #job and is asked to log-in or register
  @jobApplyCompanyProfileLoggedOut     #currently its failing on web also
  Scenario: Job apply popup from company profile page
    Given I am on page "/company/14732/acme-company-no-576874-jobs"
    When I click on apply now
    Then I should see text "Already have an account?"
    Then I should see text "Not registered?"

  #Scenario: Registers a new candidate to the site, navigates to a company profile and applies for the first job
  @jobApplyCompanyProfileNewCandidate        #need to fix Candidate Registration
  Scenario: Job apply from company profile page after registering
    Given I navigate to page "Candidate Registration"
    Then I register as a new candidate resume check
    When I go to page "/company/1478/acme-company-no-1728-jobs"
    When I find a non external job to apply for
    And I switch tab
    Then I should be able to see in browser URL "Job Apply"
    And I should see text "you're applying for"

  #Scenario: Logs a candidate into their account, navigates to a company profile and applies for a job they have not
  #already applied for
  @jobApplyCompanyProfileLoggedIn @ReleaseRegression1
  Scenario: Job apply from company profile page after logging into an account
    Given I login as a candidate
    And I am on page "/company/1478/acme-company-no-1728-jobs"
    When I find a non external job to apply for
    And I switch tab
    Then I should be able to see in browser URL "Job Apply"
    And I should see text "you're applying for"