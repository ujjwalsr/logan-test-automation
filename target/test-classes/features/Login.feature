Feature: Login to logan

  @test
  Scenario: Login test
    Given I have user credentials
    When I enter username and password
    Then I should be logged in