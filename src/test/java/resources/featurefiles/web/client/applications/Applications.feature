@ClientApplications @Regression @Web  @Recruiters  @MobileChrome
Feature: Client Applications Applications

  @contentVerification
  Scenario: Verify the content on job application page
    Given I login as a client
    When  I navigate to page "Client Job Applications"
    Then  I should see text "Job ID"
    And   I should see text "Your job ref"
    And   I should see text "Job title"
    And   I should see text "Candidate name"
    And   I should see text "Candidate email"
    And   I should see text "Suitability"
    And   I should see text "Date applied"
    And   I should see text "Apply type"
    And   I should see text "Actions"

  @viewApplication @ReleaseRegression2
  Scenario: View a job application
    Given I login as a client
    When  I navigate to page "Client Job Applications"
    And   I click on Action button one
    And   I click on action view button one
    Then  I switch tab
    And   I should be able to see in browser URL "Client Resume View Candidate Application"
    And   I should see "Date Applied:"

  @rejectApplication @ReleaseRegression2
  Scenario: Reject a job application
    Given I login as a client
    When  I navigate to page "Client Job Applications"
    And   I click on Action button one
    And   I click on application reject button
    Then  I should see text "The candidate will receive an email confirming they have been unsuccessful for the role of "
    And   I click on "Cancel"
    And   I should see text "Job Applications"

  @jobApplicationPagination
  Scenario: Job Application Pagination
    Given I login as testers client
    When  I navigate to page "Client Job Applications"
    Then  I should see text p tag "Displaying" and verify message "Displaying 1 to 20 of"
    And   I scroll down 0,9000
    When  I click on next page link
    Then  I should be on page "Client Job Applications 20"
    And   I should see text p tag "Displaying" and verify message "Displaying 21 to 40 of"
    And   I scroll down 0,9000
    When  I click on prev page link
    Then  I should be on page "Client Job Applications"

  @jobApplicationSortingByJobIds @ReleaseRegression2
  Scenario: Sorting by Job Ids on Job Application page
    Given I login as testers client
    When  I navigate to page "Client Job Applications"
    And   I select "Job ID (DESC)" from order by dropdown
    Then  I should see job ids in desc order
    When  I select "Job ID (ASC)" from order by dropdown
    Then  I should see job ids in asc order

  @jobApplicationSortingByJobRef
  Scenario: Sorting by Your Job Ref on Job Application page
    Given I login as testers client
    When  I navigate to page "Client Job Applications"
    And   I select "Your job ref (DESC)" from order by dropdown
    Then  I should see Your Job Ref in desc order
    When  I select "Your job ref (ASC)" from order by dropdown
    Then  I should see Your Job Ref in asc order

  @jobApplicationSortingByJobTitle
  Scenario: Sorting by Job Title on Job Application page
    Given I login as testers client
    Given  I navigate to page "Client Job Applications"
    When  I select "Job title (DESC)" from order by dropdown
    Then  I should see job title in desc order
    When  I select "Job title (ASC)" from order by dropdown
    Then  I should see job title  in asc order

  @jobApplicationSortingByCandidateName
  Scenario: Sorting by Candidate Name on Job Application page
    Given I login as testers client
    Given  I navigate to page "Client Job Applications"
    When  I select "Candidate name (DESC)" from order by dropdown
    Then  I should see candidate name in desc order
    When  I select "Candidate name (ASC)" from order by dropdown
    Then  I should see candidate name in asc order

  @jobApplicationSortingByCandidateEmail
  Scenario: Sorting by Candidate Email on Job Application page
    Given I login as testers client
    Given I navigate to page "Client Job Applications"
    When  I select "Candidate email (DESC)" from order by dropdown
    Then  I should see candidate email in desc order
    When  I select "Candidate email (ASC)" from order by dropdown
    Then  I should see candidate email in asc order

  @jobApplicationSortingByDateApplied
  Scenario: Sorting by Date Applied on Job Application page
    Given I login as testers client
    And   I go to page "Client Job Applications"
    When  I select "Date applied (DESC)" from order by dropdown
    Then  I should see date applied in desc order
    When  I select "Date applied (ASC)" from order by dropdown
    Then  I should see date applied in asc order