Feature: Validating place API

  Scenario: Verify if a place is successfully being added by addPlaceAPI
    Given Add place Payload
    When User calls "addPlaceAPI" with HTTP Post request
    Then The API should return Status 200
    And Response body "status" should be "OK"
    And Response body "scope" should be "APP"
