@JobViewBreadcrumb  @Regression @Web  @Traffic
Feature: Candidate JobView JobViewBreadcrumbFormat

  @jobViewBreadcrumbFormat @ReleaseRegression1 @MobileChrome
  Scenario: Breadcrumb format on job view
    Given I navigate to page "Job View Sales Lead For 37890455"
    And   I wait for "1" seconds
    Then  I should be able to see in browser URL "Job View"
    And   I should see bread crumbs "Rockaway jobs" with class name: "breadcrumbs"

  @jobViewBreadcrumbFormatWithKeywordNoLocation @MobileChrome
  Scenario: Breadcrumb format on job view with a keyword but no location
    Given I navigate to page "Jobs Sales"
    When  I click on job title one
    And   I switch tab
    Then  I should be able to see in browser URL "Job View"
    # TODO uncomment below line when the RC-1 is completed
    And   I should see bread crumbs "Fort Myers jobs" with class name: "breadcrumbs"