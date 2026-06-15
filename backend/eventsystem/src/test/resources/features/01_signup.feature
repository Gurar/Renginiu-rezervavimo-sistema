Feature: User registration

  Scenario: Successful registration

    Given user provides username "admin"
    And user provides password "1234"
    And user provides email "jonas@test.com"
    And user provides first name "Jonas"
    And user provides last name "Jonaitis"

    When user sends POST request to "/api/auth/signup"

    Then response status should be 201
    And response should contain userId
    And response should contain signup link
    And response should contain signin link

  Scenario: Registration with existing username

    Given user provides username "admin"
    And user provides password "1234"
    And user provides email "jonas@test.com"
    And user provides first name "Jonas"
    And user provides last name "Jonaitis"
    And user already exists in the system
    When user sends POST request to "/api/auth/signup"
    Then response status should be 400
    And response should contain message "User already exists"

  Scenario: Registration with existing email

    Given user provides username "admin2"
    And user provides password "1234"
    And user provides email "jonas@test.com"
    And user provides first name "Jonas"
    And user provides last name "Jonaitis"
    And user already exists in the system
    When user sends POST request to "/api/auth/signup"
    Then response status should be 400
    And response should contain message "Email already exists"

  Scenario: Registration with empty fields

    Given user provides username ""
    And user provides password ""
    And user provides email ""
    And user provides first name ""
    And user provides last name ""
    When user sends POST request to "/api/auth/signup"
    Then response status should be 400
    And response should contain validation errors

  Scenario: Registration with invalid email

    Given user provides username "admin"
    And user provides password "1234"
    And user provides email "invalid-email"
    And user provides first name "Jonas"
    And user provides last name "Jonaitis"
    When user sends POST request to "/api/auth/signup"
    Then response status should be 400
    And response should contain message "Invalid email format"

#  Scenario: Password encryption
#
#    Given user provides valid registration data
#
#    When user registration is completed
#
#    Then user password should be encrypted in database