Feature: Venue reservation

  Background:
    Given registered user exists in the system
    And user is authenticated

  Scenario: Successful reservation
    Given user provides event id "G5viZblFk3pMO"
    When user sends POST request to "/api/reservation"
    Then response status should be 201
    And response should contain reservationId
    And response should contain message "Reservation created successfully"
    And response should contain reservation self link
    And response should contain reservation cancel link

  Scenario: Reservation with no available seats
    Given user provides event id "G5viZblFk3pMO"
    And event has no available seats
    When user sends POST request to "/api/reservation"
    Then response status should be 400
    And response should contain message "No available seats"

  Scenario: Reservation without authentication
    Given user provides event id "G5viZblFk3pMO"
    When user sends POST request to "/api/reservation" without token
    Then response status should be 401

  Scenario: Reservation with non-existent event
    Given user provides event id "nonExistentId"
    When user sends POST request to "/api/reservation"
    Then response status should be 404
    And response should contain message "Event not found"