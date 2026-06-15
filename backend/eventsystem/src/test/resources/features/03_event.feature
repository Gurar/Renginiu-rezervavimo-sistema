Feature: Event browsing

  Scenario: View events list
    When user sends GET request to "/api/events"
    Then response status should be 200
    And response should contain event title
    And response should contain event date
    And response should contain event address
    And response should contain event image
    And response should contain event link
    And response should contain at least one event