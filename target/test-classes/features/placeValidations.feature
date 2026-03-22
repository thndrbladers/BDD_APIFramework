Feature: Validating place API

  Scenario Outline: Verify if a place is successfully being added by addPlaceAPI
    Given Add place Payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" with HTTP "POST" request
    Then The API should return Status 200
    And Response body "status" should be "OK"
    And Response body "scope" should be "APP"
    
    Examples:
|name|language|address|
|Rahul Dev Ghosh|English|Madhyapara,Barrackpore|
|Rahul Dev Ghosh2|English2|Madhyapara,Barrackpore2|
