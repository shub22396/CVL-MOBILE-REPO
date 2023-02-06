@JobSearchViewOptions @Regression @Web  @Search
Feature: Candidate JobView JobViewSearch

  @candidateJobViewOptions @ReleaseRegression1 #failing in desktop as well
  Scenario: Verify the more, save, print, email and share buttons on job view
    Given I am on page "/"
    When  I fill in search title field with "Nonexternaltestjobs"
    And   I click on Find Jobs search button
    Then  I should be on page "Jobs Nonexternaltestjobs"
    And   I Click on Job Title
    And   I switch tab
    Then  I should be able to see in browser URL "Job View"
    And   I should see Save button
    And   I should see link "Print" with id "print-job"
    And   I should see link "Share" with id "share-job"

  @candidateJobViewPagURL
  Scenario: Verify the job title is displayed in the job view page URL
    Given I am on page "/"
    When  I fill in search title field with "Sales"
    And   I click on Find Jobs search button
    Then  I should be able to see in browser URL "Jobs Sales"
    And   I Click on Job Title
    And   I switch tab
    Then  I should be able to see "sales" in browser url

  @candidateJobViewPagURLSimilarJobs
  Scenario: Verify the job title is displayed in the job view page URL for similar jobs
    Given I am on page "/"
    When  I fill in search title field with "Sales"
    And   I click on Find Jobs search button
    Then  I should be on page "Jobs Sales"
    And   I Click on Job Title
    And   I switch tab
    Then  I should be able to see "sales" in browser url
    And   I Click on Similar Jobs
    And   I Click on job from Similar Jobs
    And   I switch tab2
    Then  I should be able to see "sales" in browser url

  @JobViewSimilarJobsModule
  Scenario: Similar jobs module for external jobs should not cause a 404
    And    I am on page "Job View Sales Lead For 37890455"
   # Then   I should see text "Similar Jobs"
    Then   I click on "Similar Jobs"
    When   I click on title of the job on similar jobs module and verify url
    