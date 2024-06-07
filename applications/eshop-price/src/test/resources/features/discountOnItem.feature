Feature: Price calculation with discount on an item

  Background:
    Given the following items :
      | name                       | type | amount | currency |
      | The Fellowship of the Ring | book | 1500   | EUR      |
      | The Two Towers             | book | 2000   | EUR      |
      | The Return of the King     | book | 1800   | EUR      |
    Given there is a discount of 50 % for the item 'The Two Towers'
    And there is a discount of 20 % for the item 'The Return of the King'

  Scenario: No discount on the chosen item
    Given I add the item 'The Fellowship of the Ring' in my cart
    When I validate my cart
    Then I should pay 1500 EUR

  Scenario: A discount on one item
    Given I add the item 'The Two Towers' in my cart
    When I validate my cart
    Then I should pay 1000 EUR

  Scenario: Discount is applied when multiple time the same item
    Given I add the item 'The Return of the King' in my cart
    And I add the item 'The Return of the King' in my cart
    When I validate my cart
    Then I should pay 2880 EUR

  Scenario: 3 items, 2 with a discount and the last one without
    Given I add the item 'The Fellowship of the Ring' in my cart
    And I add the item 'The Two Towers' in my cart
    And I add the item 'The Return of the King' in my cart
    When I validate my cart
    Then I should pay 3940 EUR
