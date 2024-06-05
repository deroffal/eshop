Feature: Price calculation with simple discount (x% from n same items)

  Background:
    Given the following items :
      | name                       | type   | amount | currency |
      | The Fellowship of the Ring | book   | 1500   | EUR      |

  Scenario: Price calculation with no discount
    Given I add the item 'The Fellowship of the Ring' in my cart
    When I validate my cart
    Then I should pay 1500 EUR

