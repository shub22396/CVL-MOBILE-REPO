@CareerAdviceHeaderFooterLoggedIn @Regression @Web  @Candidate
Feature: Candidate CareerAdvice CareerAdviceHeadersFootersLoggedIn

  Background: CareerAdvice login page
    Given  I am on home page
    Then   I should see resume library logo
    And    I click on Menu Bar
    Then   I should see text "Login"
    When   I click Login button
    And    I fill in Email address
    And    I fill in Password
    And    I click Login as Jobseeker button
    Then   I should be on "My Dashboard" page

  @CareerAdviceNavigateHeaderLoggedIn
  Scenario: Navigation header for logged in user
    Given  I navigate to page "Career Advice"
    Then   I should see resume Library header logo
    When   I click on the logged in RL header logo
    And   I click on mobile header button
    Then   I click on link text "My Dashboard"
    Then   I should be on page "Candidate My Dashboard"
    Then   I should see hyperlink "My Dashboard"
    Then   I should see hyperlink "Search Jobs"
    And    I should see hyperlink "Job Alerts"
    And    I should see text "Career Advice"
    And    I should see hyperlink "Saved Jobs"
    And    I should see hyperlink "My Profile"
    And    I should see text "Hi Resume-Library"
    And   I click on "Jobseeker Tools"
    When   I click on link text "Career Advice"
    And   I click on mobile header button
    Then   I click on link text "My Dashboard"
    Then   I should be able to see in browser URL "Candidate My Dashboard"
   # And    I click on "Saved Jobs"
   # Then   I should be able to see in browser URL "Candidate Saved Jobs"
    And   I click on "Jobseeker Tools"
    When   I click on link text "Career Advice"
    And   I click on "Jobseeker Tools"
    And    I click on link text "Search Jobs"
    Then   I should be able to see in browser URL "Search Jobs"
    And   I click on "Jobseeker Tools"
    When   I click on link text "Career Advice"
    And   I click on mobile header button
    And    I click on link text "My Profile"
    Then   I should be able to see in browser URL "Candidate My Profile"
    And   I click on "Jobseeker Tools"
    When   I click on link text "Career Advice"
    And   I click on mobile header button
    Then   I click on link text "Job Alerts"
    Then   I should be able to see in browser URL "Candidate Job Alerts"
    And   I click on "Jobseeker Tools"
    When   I click on link text "Career Advice"


  @CareerAdviceNavFooterJobSeekersTools
  Scenario: Navigation Footer JobSeekers Tools for logged in user
    And    I navigate to page "Career Advice"
    Then   I should see resume Library footer logo
   # Then   I should see text H four tag "Jobseeker Tools"
    And   I click on "Jobseeker Tools"
    When   I click on link "Career Advice" in "Jobseeker Tools" footer
    Then   I should be able to see in browser URL "Career Advice"
    And   I click on "Jobseeker Tools"
    When   I click on link "Search Jobs" in "Jobseeker Tools" footer
    Then   I should be able to see in browser URL "Search Jobs"
    And   I click on "Jobseeker Tools"
    When   I click on link "Search Companies" in "Jobseeker Tools" footer
    Then   I should be able to see in browser URL "Companies"
    And   I click on "Jobseeker Tools"
    When   I click on link "FAQs" in "Jobseeker Tools" footer
    Then   I should be able to see in browser URL "Faq"

  @CareerAdviceNavFooterAccountOptions
  Scenario: Navigation Footer Account Options logged in user
    And    I navigate to page "Career Advice"
    Then   I should see resume Library footer logo
#    Then   I should see text H four tag "Account Options"
    And   I click on "Account Options"
    When   I click on link "Job Alerts" account options menu
    Then   I should be able to see in browser URL "Candidate Job Alerts"
    And   I click on "Account Options"
    When   I click on link "My Settings" account options menu
    Then   I should be able to see in browser URL "Candidate Settings"
    And   I click on "Account Options"
    When   I click on link "My Cover Letter" account options menu
    Then   I should be able to see in browser URL "Candidate Cover Letter"
    And   I click on "Account Options"
    And    I click on link "My Profile" account options menu
    Then   I should be able to see in browser URL "Candidate My Profile"

  @CareerAdviceNavFooterAboutRL
  Scenario: Navigation Footer About RL Menu for logged in user
    And    I navigate to page "Career Advice"
    Then   I should see resume Library footer logo
#    Then   I should see text H four tag "About Resume-Library"
    And   I click on "About Resume-Library"
    When   I click on link "About Us" about RL menu
    Then   I should be able to see in browser as URL "/about"
    And    I navigate to page "Career Advice"
    And   I click on "About Resume-Library"
    When   I click on link "Contact Us" about RL menu
    Then   I should be able to see in browser as URL "Contact"
    And    I navigate to page "Career Advice"
    And   I click on "About Resume-Library"
   When   I click on link "Work For Us" about RL menu
    Then   I should be able to see in browser URL "/company/4394/resume-library-jobs"
    And    I navigate to page "Career Advice"
    And   I click on "About Resume-Library"
    And    I click on link "Meet the Team" about RL menu
    Then   I should be able to see in browser URL "Meet The Team"