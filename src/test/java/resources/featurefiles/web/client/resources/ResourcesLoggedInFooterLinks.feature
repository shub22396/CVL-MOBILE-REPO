@ResourcesPageFooterLinks  @Regression @Web  @ReleaseRegression2 @Traffic
Feature: Client Resources ResourcesLoggedInFooterLinks

  Background: Navigating to Resources page
    Given I login as a client

  @ResourcesPageFooterLinks @MobileChrome
  Scenario: Navigation Footer for logged In user on resources blog page
    When   I navigate to page "Resources"
    And    I should see text "Resume Search"
    And    I should see text "Manage Jobs"
    And    I should see text "Resume Alerts"
    And    I should see text "Account Options"

    When   I click on button "Resume Search" in footer
    Then   I click on link "Search Resumes Now" in "Resume Search" footer
    And    I should see text H two tag "Search Millions of Resumes"
    And    I reload the page
    Then   I should be able to see in browser URL "Client Resume Search"

    When   I navigate to page "Resources"
    Then   I click on button "Resume Search" in footer
    And    I click on link "Saved Resumes" in "Resume Search" footer
    Then   I should be able to see in browser URL "Client My Saved Resumes"
    And    I should see text H one tag "My Saved Resumes"

    When   I navigate to page "Resources"
    When   I click on button "Resume Search" in footer
    Then   I click on link "Unlocked Resumes" in "Resume Search" footer
    Then   I should be able to see in browser URL "Client My Unlocked Resumes"
    Then   I should see text H one tag "My Unlocked Resumes"

    When   I navigate to page "Resources"
    Then   I click on button "Manage Jobs" in footer
    And    I click on link "Manage Jobs" in "Manage Jobs" footer
    Then   I should be able to see in browser URL "Client Jobs"

    When   I navigate to page "Resources"
    Then   I click on button "Manage Jobs" in footer
    When   I click on link "Post Jobs" in "Manage Jobs" footer
    Then   I should be able to see in browser URL "Job Post"

    When   I navigate to page "Resources"
    Then   I click on button "Manage Jobs" in footer
    When   I click on link "Job Applications" in "Manage Jobs" footer
    Then   I should be able to see in browser URL "Client Job Applications"

    When   I navigate to page "Resources"
    Then   I click on button "Resume Alerts" in footer
    When   I click on link "Manage Alerts" in "Resume Alerts" footer
    Then   I should be able to see in browser URL "Client Resume Alerts"

    When   I navigate to page "Resources"
    Then   I click on button "Resume Alerts" in footer
    When   I click on link "Create New Alert" in "Resume Alerts" footer
    Then   I should be able to see in browser URL "Client Resume Alert Create"

    When   I navigate to page "Resources"
    Then   I click on button "Resume Alerts" in footer
    When   I click on link "View Sent Alerts" in "Resume Alerts" footer
    Then   I should be able to see in browser URL "Client Resume Alerts Log"

    When   I navigate to page "Resources"
    Then   I click on button "Account Options" in footer
    When   I click on link "Change Password" in "Account Options" footer
    Then   I should be able to see in browser URL "User Change Password"

    When   I navigate to page "Resources"
    Then   I click on button "Account Options" in footer
    When   I click on link "FAQs" in "Account Options" footer
    Then   I should be able to see in browser URL "/faq?type=recruiter"

    When   I navigate to page "Resources"
    Then   I click on button "Account Options" in footer
    When   I click on link "Contact Us" in "Account Options" footer
    Then   I should be able to see in browser URL "Client Contact"
