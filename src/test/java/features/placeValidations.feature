Feature: Validating place API

  @AddPlaceAPI
  Scenario Outline: Verify if a place is successfully being added by addPlaceAPI
    Given Add place Payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" with HTTP "POST" request
    Then The API should return Status 200
    And Response body "status" should be "OK"
    And Response body "scope" should be "APP"
    And Verify place got added using "<name>" using "getPlaceAPI"

    Examples:
      | name             | language | address                 |
      | Rahul Dev Ghosh  | English  | Madhyapara,Barrackpore  |
      | Rahul Dev Ghosh2 | English2 | Madhyapara,Barrackpore2 |

  @DeletePlaceAPI
  Scenario: Verify deleteAddress API deletes the place
    Given Delete Place API Payload
    When User calls "deletePlaceAPI" with HTTP "DELETE" request
    Then The API should return Status 200
    And Response body "status" should be "OK"
