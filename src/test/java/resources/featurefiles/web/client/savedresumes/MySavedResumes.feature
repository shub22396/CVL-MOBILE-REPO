@MySavedResumes @Regression @Web  @Search @MobileChrome
Feature: Client SavedResumes MySavedResumes

  @mySavedResumesPagination
  Scenario:My Saved Resumes Page Pagination
    Given I login as testers client
    And   I navigate to page "Client My Saved Resumes"
    And   I should see text p tag "Displaying" and verify message "Displaying 1 to 20 of"
    And   I click on next page link
    Then  I should be on page "Client My Saved Resumes 20"
    And   I should see text p tag "Displaying" and verify message "Displaying 21 to 40 of"
    When  I click on prev page link
    Then  I should be on page "Client My Saved Resumes 0"
    And   I should not see "Prev" link

  @sortingByDateSaved @ReleaseRegression2
  Scenario: Sorting by Date Saved
    Given I login as testers client
    And   I navigate to page "Client My Saved Resumes"
    When  I select the option "Date Saved (DESC)" from order by dropdown on client resume page
    Then  I should see saved dates in desc order
    When  I select the option "Date Saved (ASC)" from order by dropdown on client resume page
    Then  I should see in saved dates in asc order

  @sortingByCandidateName
  Scenario: Sorting by Candidate Name or Job Title
    Given I login as testers client
    When  I navigate to page "Client My Saved Resumes"
    And   I select the option "Candidate Name (ASC)" from order by dropdown on client resume page
    Then  I should see in Candidate name in asc order
    When  I select the option "Candidate Name (DESC)" from order by dropdown on client resume page
    Then  I should see in Candidate name in desc order

  @mySavedResumesAssertAndActions
  Scenario: Verify elements in my saved resumes and check 'Actions'
    Given I login as testers client
    When  I go to page "Client My Saved Resumes"
    And   I click on saved action one
    And   I click on "View"
    And   I switch tab
    Then  I should be able to see in browser URL "Client Resume View Candidate Profile"
    And   I should see text "Candidate ID:"
    And   I should see text "Email address"
    When  I go to page "Client My Saved Resumes"
    When  I click on saved action one
    And   I click on "Delete"
    Then  I should see text "Are you sure you want to delete this saved Resume?"
    And   I should see text "You can always re-save a Resume at any time. If you have unlocked this Resume, deleting it here will"
    Then  I click on saved delete cancel
    Then  I should see text H one tag "My Saved Resumes"

  @emailDisabledForLockedResume2
  Scenario: Verify if 'Email' option is disabled for locked resumes on My Saved resumes
    Given I navigate to page "Client Registration"
    Then  I register as a new client
    And   I am on page "Client Resume Search With Keywords"
    And   I find a locked candidate and verify text "Unlock Resume"
    Then  I should be able to see in browser URL "Client Resume View Candidate Profile"
    And   I click on save resume
    Then  I go to page "Client My Saved Resumes"
    When  I click on saved action one
    And   I click on "Email"
    Then  I should see text "Resume is locked"
    And   I should see text "You may only contact a candidate once they have been unlocked. Please unlock this candidate to continue."
    Then  I wait for "1" seconds
    And   I click on "OK"
    Then  I should see text H one tag "My Saved Resumes"