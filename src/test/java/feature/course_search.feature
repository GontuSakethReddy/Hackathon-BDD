Feature: Coursera Course Search and Business Form Validation

  Background:
    Given the user is on the Coursera homepage

  
  Scenario: Search for web development courses
    When the user searches for "web development"
    And the user filters by Beginner level and English language
    Then the system should display the top 2 course details


  Scenario: Validate business form errors
    When the user navigates to the "For Business" section
    And fills the contact sales form with:
      | firstName | lastName | email       |
      | Saketh    | Gontu    | sakethGontu |
      | raj       | sand     | rajesh      |
    And submits the form
    Then an email validation error "Must be valid email" should be displayed