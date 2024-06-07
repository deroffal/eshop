Feature: Simple price calculation

  Background:
    Given the following items :
      | name | amount | currency |
      | book | 1000   | EUR      |
      | ball | 550    | EUR      |

  Scenario: Price calculation for a cart with only one type of item
    Given I add 2 'book' in my cart
    When I validate my cart
    Then I should pay 2000 EUR

  Scenario: Price calculation for a cart with multiple types
    Given I add 3 'book' in my cart
    And I add 5 'ball' in my cart
    When I validate my cart
    Then I should pay 5750 EUR
