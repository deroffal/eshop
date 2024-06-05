Feature: Simple price calculation

  Background:
    Given the price of the following items :
      | item | amount | currency |
      | book | 1000   | EUR      |
      | ball | 550    | EUR      |

  Scenario: Price calculation for a basket with only one kind of item
    Given I add 2 'book' in my cart
    When I validate my cart
    Then I should pay 2000 'EUR'

  Scenario: Price calculation for a basket
    Given I add 3 'book' in my cart
    And I add 5 'ball' in my cart
    When I validate my cart
    Then I should pay 5750 'EUR'
