Feature: Price calculation with simple discount (x% from n same items)

  Background:
    Given a 'book' costs 1000 'EUR'
    And a 'blue-ray' costs 2500 'EUR'
    Given a discount of 10 % from 3 'book'
    And a discount of 50 % from 3 'blue-ray'

  Scenario: Price calculation with a simple discount
    Given I add 5 'book' in my cart
    When I validate my cart
    Then I should pay 4500 'EUR'

  Scenario: Price calculation with a simple discount for 2 items
    Given I add 10 'book' in my cart
    Given I add 10 'blue-ray' in my cart
    When I validate my cart
    Then I should pay 21500 'EUR'
