@CareerAdviceLoggedOut @Regression @Web  @Candidate
Feature: Candidate CareerAdvice CareerAdviceLoggedOut

  @loadCareerAdviceLoggedOut
  Scenario: Load Career Advice
    Given  I navigate to page "Career Advice"
    Then   I should see "Career Advice" on the page
    And    I should see text "View all"
    And    I should see H two tag "The latest jobs on " and verify message "The latest jobs on Resume-Library"
    And    I should see text H three tag "Jobs By Industry"
    And    I should see text H three tag "Jobs By Location"
    And    I should see text H three tag "Popular jobs"

  @careerAdviceCategoriesLoggedOut
  Scenario: Career advice categories are shown
    Given  I navigate to page "Career Advice Getting Started"
    And    I should see text "At Work"
    And    I should see text "Career Development"
    And    I should see text "Cover Letters"
    And    I should see text "Getting Started"
    And    I should see text "Grads"
    And    I should see text "Interviews"
    And    I should see text "Latest"
    And    I should see text "Location Guides"
    And    I should see text "Resume Tips"

  @CareerAdviceCompaniesCountLoggedOut
  Scenario: Career Advice Companies Count is not zero in sub nav bar
    Given  I navigate to page "Career Advice"
    Then   I should not see the companies count is zero
    And   I click on "Jobseekers"
    And   I click on "Search Companies"
    #When   I click on Companies link
    Then   I should be on page "Companies"
    And    I should see text "Search Companies"

  @CareerAdviceFreeResumeReviewLoggedOut
  Scenario: Verify resume review banner on logged out career advice page
    Given  I navigate to page "Career Advice"
    And   I click on "Jobseekers"
    And   I click on "Free Resume Review"
   # And    I click on FREE Resume Review
    Then   I should be on page "Resume Review"
    And    I should see text "FREE Resume Review"

  @CareerAdviceSavedJobsLoggedOut
  Scenario: Career Advice logged out saved Jobs
    Given  I navigate to page "Career Advice"
    And   I click on mobile header button
   # When   I click on saved job button
    And   I click on "Saved Jobs"
    Then   I should be on page "Saved Jobs"
    And    I should see text H one tag "My Saved Jobs"

  @CareerAdviceSavedJobsViewLoggedOut
  Scenario: As a logged out user save a job and view it on career advice page
    Given I navigate to page "Jobs Sales"

    When  I click on save toggle one
#    And   I click on "Career Advice"
    And I navigate to page "Career Advice"
    And   I should be on page "Career Advice"
#    Then  I should see text a tags "Saved Jobs (" and verify "Saved Jobs (1)"
    And I navigate to page "saved jobs"
    And   I should see text p tag "Displaying" and verify message "Displaying 1 to 1 of 1"

  @CareerAdviceSearchJobsLinkLoggedOut
  Scenario: As a logged out user I see the career-advice navigation bar has  search jobs link
    Given  I navigate to page "Career Advice"
    And   I click on mobile header button
   # Then   I should see Search Jobs link
  #  When   I click on Search Jobs link
    And   I click on "Search Jobs"
    Then   I should be on page "Search Jobs"

  @CareerAdviceNavigationLinksLoggedOut
  Scenario:As a logged out user I see the career-advice drop navigation has links
    Given I navigate to page "Career Advice"
    Then  I should see text "Home"
    And I click on "View Categories"
    When  I click on "Getting Started" career advice links
    Then  I should be on page "Career Advice Getting Started"

    When  I navigate to page "Career Advice"
    And I click on "View Categories"
    And   I click on "Resume Tips" career advice links
    Then  I should be on page "Career Advice Resume Tips"

    When  I navigate to page "Career Advice"
    And I click on "View Categories"
    And   I click on "Cover Letters" career advice links
    Then  I should be on page "Career Advice Cover Letters"

    When  I navigate to page "Career Advice"
    And I click on "View Categories"
    And   I click on "Interviews" career advice links
    Then  I should be on page "Career Advice Interviews"

    When  I navigate to page "Career Advice"
    And I click on "View Categories"
    And   I click on "At Work" career advice links
    Then  I should be on page "Career Advice At Work"

    When  I navigate to page "Career Advice"
    And I click on "View Categories"
    And   I click on "Career Development" career advice links
    Then  I should be on page "Career Advice Career Development"

    When  I navigate to page "Career Advice"
    And I click on "View Categories"
    And   I click on "Location Guides" career advice links
    Then  I should be on page "Career Advice Location Guides"

    When  I navigate to page "Career Advice"
    And I click on "View Categories"
    And   I click on "Grads" career advice links
    Then  I should be on page "Career Advice Grads"

    When  I navigate to page "Career Advice"
    And I click on "View Categories"
    And   I click on "Gig Jobs" career advice links
    Then  I should be on page "Career Advice Gig Jobs"

    When  I navigate to page "Career Advice"
    And I click on "View Categories"
    And   I click on "COVID-19 Advice" career advice links
    Then  I should be on page "Career Advice Covid 19 Advice"

  @CareerAdvice3AdvertsLoggedOut
  Scenario: As a logged out user I see the career-advice page has 3 adverts
    Given I navigate to page "Career Advice"
    Then  I should see RL Career Advice Dyn Banner
   # And   I should see RL Career Advice 160x600
    And   I should see blog-fb-banner
#
#  @CareerAdviceSearchJobsNavigationBarLoggedOut  #this scenario wont available in mobile view
#  Scenario: As a logged out user I see the career-advice page has search jobs navigation bar
#    Given I navigate to page "Career Advice"
#  #  And   The search jobs navigation bar button has text "Find jobs"
#    And   I fill in search jobs nav bar keywords search with "Sales"
#    And   I fill in search jobs nav bar location search with "10001"
#    And   I select "60 Miles" from search distance
#    And   I click on search jobs
#    Then  I should be able to see in browser URL "/jobs/sales-in-10001?r=60"
#    And   I should see text H one tag "Sales jobs"