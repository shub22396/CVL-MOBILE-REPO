# Tests coverage
    # 1. Job search with keyword then asserting some elements on the search results page
            # Elements are: Jobs by category, Jobs by location, more and fewer button
    # 2. Displaying page, upload resume banner, location and job type field on Job card

@JobSearchResults @Regression @Web  @Search @MobileChrome
Feature: Candidate JobSearch SearchResultsPage

  @searchResultsPageModules
  Scenario: Search results page modules
    Given I navigate to page "Jobs Sales"
    And   I should see text "Jobs By Location"
    And   I should see text "Alabama" on the page source
    And   I should see text "Jobs By Category"
    And   I should see text "Sales Account Manager"


  @searchResultsPageElements @ReleaseRegression1
  Scenario: Search results page filters
    Given I navigate to page "Jobs Sales"
    And   I should see text p tag "Displaying" and verify message "Displaying 1 to 20 of "
    And   I should see text "Location:"
    And   I should see text "Job Type:"
    And   I should see link "Apply now" with id "apply_now_1"
    And   I should see link "More" with id "more_1"
    And   I should see link "Sales" with id "job_title_1"

  @viewJobPageAndRecentlyViewedJobs
  Scenario: View Job Page And Recently Viewed Jobs
    Given I navigate to page "Jobs Non External Test Jobs"
    When  I Click on Job Title
    When  I switch tab
    Then  I should see text "Apply now"
    And   I should see "Create a new Job Alert to make sure you see the best new jobs first!"
    And   I should see text "Create Alert"
    When  I am on page "Jobs Sales"
    And   I should see text "Recently Viewed Jobs"

  @searchResultsAlsoInOtherLocations
  Scenario: Verify 'Also in other locations' link on /jobs page
    Given I navigate to page "Jobs Amazon"
    When  I click on "Also in other locations" and see link "View all locations"
    And   I switch tab
    Then  I should be able to see in browser URL "Job View"
    And   I should see text "Apply now"
    And   I should see "Create a new Job Alert to make sure you see the best new jobs first!"

  @viewJobPageClearRecentSearches
  Scenario: View Job search results and verify that recent searches are not displayed if its cleared
    Given I navigate to page "Jobs Sales"
    Then  I navigate to page "Jobs Qa"
    Then  I should see text "Recent Searches"
    And   I should see text "Sales jobs"
    And   I scroll down to element "Recent Searches"
    And   I click on "Recent Searches"
    And   I click on "Clear recent searches"
    And   I confirm browser popup
    Then  I should not see "Recent Searches"

  @nationalJobsBanner
  Scenario: Verify National jobs banner
    When  I navigate to page "Jobs"
    Then  I should see text Location: "National"
    And   I should see link "National" with id "search-results-national-sash-1"

  @featuredJobsBanner
  Scenario: Verify Featured jobs banner
    When  I navigate to page "Jobs Sales"
    And   I should not see "Location: National"
    #And  I should see link "Featured" with id "search-results-featured-sash-1"

  @premiumJobsBanner
  Scenario: Verify Premium jobs banner
    When  I navigate to page "Jobs Premium Featured"
    #TODO: Nirupan
    #And  I should see "Premium Featured" sash

  @jobsByCategoryModule @ReleaseRegression1
  Scenario: Jobs By Category module
    Given I navigate to page "Jobs"
    When  I click on "Jobs By Category"
    And   I click on "Healthcare"
    Then  I should see text "Healthcare jobs"
   # And   the title field should contain "healthcare"
    When  I click on "Jobs By Category"
    And   I click on "Nursing"
    And   I should see text "Nursing jobs"
   # And   the title field should contain "nursing"
    And   I click on "Back"
    Then  I should see text "Healthcare jobs"

  @jobsByLocationModule @ReleaseRegression1
  Scenario: Jobs By Location module
    Given I navigate to page "Jobs"
    When  I click on "Jobs By Location"
    And   I click on "California"
    Then  I should see text "Jobs in California"
    And   the location field should contain "State Of California"
    When  I click on "Jobs By Location"
    And   I click on "San Francisco" in location module
    Then  I should see text "Jobs in San Francisco, CA"
    And   the location field should contain "San Francisco, CA"
    And   I click on "Back"
    Then  I should see text "Jobs in California"

  @viewMoreSearchesModule @ReleaseRegression1
  Scenario: View more searches module
    Given I navigate to page "Jobs"
    Then  I should see text "All jobs"
    When  I navigate to page "Jobs Sales"
    And   I should see text "Sales jobs"
    Then  I navigate to page "Jobs Test"
    And   I should see text "Test jobs"

  @jobsBySimilarJobsModule @ReleaseRegression1
  Scenario: Verify similar jobs module
    Given  I login as a candidate
    And    I navigate to page "Jobs QA Tester"
    Then   I should see text "Similar Jobs"
    When   I click on search job title link
    And    I switch to window 1
    And    I should be able to see in browser URL "Job View"

  @JobSearchDistance
  Scenario Outline: Job search by distance
    Given  I login as a candidate
    And    I navigate to page "Search Jobs"
    When   I fill in search title field with "<Title>"
    And    I fill in search location with "<Location>"
    And    I select "<Distance>" from search distance
    And    I click on find jobs button
    Then   I should be on page "/jobs/test-engineer-in-10001?r=50"
    And    I should see text H one tag "Test Engineer jobs in 10001"
    When   I click on id "mobile-search-trigger"
    Then   the title field should contain "<Title1>"
    And    the location field should contain "<Location>"
    And    the distance field should contain "50"
    Examples:
      | Title         | Location | Distance | Title1        |
      | Test Engineer | 10001    | 50       | test engineer |

  @jobsSearchSalaryMin
  Scenario Outline: Job search salary min
    Given  I login as a candidate
    And    I navigate to page "Search Jobs"
    When   I fill in search title field with "<Title>"
    And    I fill in search location with "<Location>"
    And    I click on link text "More Search Options"
    And    I select the option "<SalMin>" from salary min dropdown
    And    I click on find jobs button
    Then   I should be on page "/jobs/sales-in-10001?annual_salary_from=50000"
    And    I should see text "Sales jobs in 10001"
    When   I click on id "mobile-search-trigger"
    Then   the title field should contain "<Title>"
    And    the location field should contain "<Location>"
    When   I click on "More Search Options"
    Then   the salary min field should contain "<SalMin>"
    Examples:
      | Title | Location | SalMin |
      | sales | 10001    | 50000  |

  @jobsSearchSalaryMax
  Scenario Outline: Job search salary max
    Given  I login as a candidate
    And    I navigate to page "Search Jobs"
    When   I fill in search title field with "<Title>"
    And    I fill in search location with "<Location>"
    And    I click on link text "More Search Options"
    And    I select the option "<SalMax>" from salary max dropdown
    And    I click on find jobs button
    Then   I should be on page "/jobs/sales-in-10001?annual_salary_to=70000"
    And    I should see text "Sales jobs in 10001"
    When   I click on id "mobile-search-trigger"
    And    the title field should contain "<Title>"
    And    the location field should contain "<Location>"
    When   I click on "More Search Options"
    Then   the salary max field should contain "<SalMax>"
    Examples:
      | Title | Location | SalMax |
      | sales | 10001    | 70000  |

  @jobsSearchOrderBy
  Scenario Outline: Job search order results
    Given  I login as a candidate
    And    I navigate to page "Search Jobs"
    When   I fill in search title field with "<Title>"
    And    I fill in search location with "<Location>"
    And    I click on link text "More Search Options"
    And    I select the option "<OrderBy>" from order by dropdown
    And    I click on find jobs button
    Then   I should be on page "<URL>"
    And    I should see text "<Message>"
    And    the sort by should be "Date"
    Examples:
      | Title | Location | OrderBy     | URL                                | Message             |
      | sales | 10010    | Most recent | /jobs/sales-in-10010?order_by=date | Sales jobs in 10010 |

  @jobsSearchByPostedSince
  Scenario Outline: Job Search by Posted Since
    Given  I login as a candidate
    And    I navigate to page "Search Jobs"
    When   I fill in search title field with "<Title>"
    And    I fill in search location with "<Location>"
    When   I click on link text "More Search Options"
    And    I select the option "<PostedSince>" from posted since dropdown
    And    I click on find jobs button
    Then   I should be on page "<URL>"
    And    I should see text "<Message>"
    When   I click on id "mobile-search-trigger"
    And    the title field should contain "<Title>"
    And    the location field should contain "<Location>"
    When   I click on "More Search Options"
    Then   the posted date should be "<PostedSince>"
    Examples:
      | Title | Location | PostedSince | URL                        | Message             | PostedInThe  |
      | sales | 20006    | 15 Days Ago | /jobs/sales-in-20006?pd=15 | Sales jobs in 20006 | Last 15 Days |

  @jobsSearchByJobType
  Scenario Outline: Job search By Job Type
    Given  I login as a candidate
    And    I navigate to page "Search Jobs"
    When   I fill in search title field with "<Title>"
    And    I fill in search location with "<Location>"
    When   I click on link text "More Search Options"
    And    I select the option "<Type>" from job type dropdown
    And    I click on find jobs button
    Then   I should be on page "<URL>"
    And    I should see text "<Message>"
    When   I click on id "mobile-search-trigger"
    And    the title field should contain "<Title>"
    And    the location field should contain "<Location>"
    When   I click on "More Search Options"
    Then   the job type field should contain "<jobType>"

    Examples:
      | Title        | Location | Type      | URL                                       | Message                              | jobType   |
      | sales        | 10001    | Permanent | /jobs/sales-in-10001?job_type=Permanent   | Permanent Sales jobs in 10001        | Permanent |
      | test analyst | 20001    | Temporary | /jobs/test-analyst-in-20001?job_type=Temp | Temporary Test Analyst jobs in 20001 | Temp      |

  @jobSearchSpellingSuggestion
  Scenario: Job Search Spelling Suggestion
    Given I navigate to page "Jobs Spleling"
    Then  I should see caution message h3 tag "Showing results for selling"
    And   I should see caution message p tag "Search instead for spleling"
    Then  I click on link text "spleling"
    Then  I should see caution message h3 tag "Sorry, no jobs match your search criteria"
    And   I should see caution message p tag "Below are some recommended jobs based on your recent searches"

  @jobSearchNoJobsMatched
  Scenario: Job search no jobs
    Given I navigate to page "Jobs Xxyy"
    Then  I should see caution message h3 tag "Sorry, no jobs match your search criteria"
    And   I should see caution message p tag "Need help to find that dream job? Read our Job Searching Tips below"
    And   I should see text "Resume-Library's Job Searching Tips"
    And   I should see text "For example, use 'customer care manager', instead of 'senior customer care manager call center'."

  @jobSearchNoJobsShowRecentSearches
  Scenario: Job search no jobs show recent search
    Given I navigate to page "Jobs Test"
    Then  I navigate to page "Jobs Xxyy"
    And   I should see caution message h3 tag "Sorry, no jobs match your search criteria"
    And   I should see caution message p tag "Below are some recommended jobs based on your recent searches"



