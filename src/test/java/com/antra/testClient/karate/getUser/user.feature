Feature: Get Users Information

  Background:
    Given url 'http://localhost:8080/api'
    * def user1 = read('user.json')

  Scenario: Get User by Id
#    version 1, assertion with a Json File
    Given path '/user/1'
    When method get
    Then status 200
    * match response == user1

  Scenario: Get User by Id
#    version 2, assertion with fields
    Given path '/user/1'
    When method get
    Then status 200
    * match response.id == 1
    * match response.name == 'david'
    * match response.age == 20

  Scenario: Get User by Id, but no such user
    Given path '/user/1000'
    When method get
    Then status 404

  Scenario: Get User by ID using POST
    Given path '/user/1000'
    When method POST
    Then status 405
