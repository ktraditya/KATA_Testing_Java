Feature: Hotel booking reservation
  A customer should be able to use the portal to a hotel room for a desired number of nights based on the availability

  Background: 
    Given User access the hotel booking website in his browser

  @book
  Scenario: User should be able to make a reservation
    Given User clicks on the Book this room button
    Given User enter valid details
      | firstname | lastname | email                | phone       | checkin_date | checkout_date |
      | Steve     | Jobs     | steve.jobs@apple.com | 04578965254 | 18   | 20   |
    When user clicks on the Book button
    Then user verifies the booking dates in the popup
    
