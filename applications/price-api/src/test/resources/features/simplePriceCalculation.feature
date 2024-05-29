Feature: Simple price calculation

  Background:
    Given the price of the following items :
      | name | price |
      | book | 10.00 |
      | ball | 5.50  |

  Scenario: Price calculation for a basket with only one kind of item
    Given I add 2 'book' in my basket
    When I validate my basket
    Then I should pay 20.00

  Scenario: Price calculation for a basket
    Given I add 3 'book' in my basket
    And I add 5 'ball' in my basket
    When I validate my basket
    Then I should pay 57.50
