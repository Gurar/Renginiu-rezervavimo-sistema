Feature: User authentication

  Background:
    Given registered user exists in the system

  Scenario: Successful authenticate

    Given user provides username "admin"
    And user provides password "1234"
    When user sends POST request to "/api/auth/signin"
    Then response status should be 200
    And response should contain userId
    And response should contain username
    And response should contain token
    And response should contain self link
    And response should contain reservations link

  Scenario: Authentication with empty fields

    Given user provides username ""
    And user provides password ""
    When user sends POST request to "/api/auth/signin"
    Then response status should be 400
    And response should contain validation errors

  Scenario: Authentication with non-existent user
    Given user provides username "nonExist"
    And user provides password "wrongPassword"
    When user sends POST request to "/api/auth/signin"
    Then response status should be 400
    And response should contain validation errors