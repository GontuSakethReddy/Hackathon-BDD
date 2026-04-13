Feature: Coursera Course Search and Business Form Validation

  Scenario: Search for web development courses and validate business form errors
   
    Given the user is on the Coursera homepage
    
    When the user searches for "web development"
    
    And the user filters by Beginner level and English language
    
    Then the system should display the top 2 course details
    
    
    When the user navigates to the For Business section
    
    And fills the contact sales form with:
      | firstName | lastName | email |
      | Saketh    | Gontu    | saki  |
      
    And submits the form
    
    Then an email validation error "Must be valid email" should be displayed