Feature: Price calculation when there is discount related to a set of articles.

  Background:
    Given the following articles :
      | name   | type      | amount | currency |
      | Banana | fruit     | 100    | EUR      |
      | Apple  | fruit     | 120    | EUR      |
      | Tomato | vegetable | 80     | EUR      |

  # (eg. you bike 2 bananas, the 3rd is free)
  Rule: You buy 'n' articles of the same type, the next is free

    Scenario Template: Case with only one kind of product
      Given if I buy 2 articles of type 'Banana', the next one is free
      Given I add <numberOfArticle> 'Banana' in my cart
      When I validate my cart
      Then I should pay <totalAmount> EUR
      Examples:
        | numberOfArticle | totalAmount |
        | 1               | 100         |
        | 2               | 200         |
        | 3               | 200         |
        | 4               | 300         |
        | 5               | 400         |
        | 6               | 400         |
        | 7               | 500         |
        | 8               | 600         |
        | 9               | 600         |
        | 10              | 700         |

    Scenario Template: Case with two kind of product
      Given if I buy 2 articles of type 'Banana', the next one is free
      And if I buy 2 articles of type 'Apple', the next one is free
      Given I add <numberOfArticle> 'Banana' in my cart
      And I add <numberOfArticle> 'Apple' in my cart
      When I validate my cart
      Then I should pay <totalAmount> EUR
      Examples:
        | numberOfArticle | totalAmount |
        | 1               | 220         |
        | 2               | 440         |
        | 3               | 440         |
        | 4               | 660         |
        | 5               | 880         |
        | 6               | 880         |
        | 7               | 1100         |

        # (eg. you bike 2 bananas, the 3rd is 50% of its price)
  Rule: You buy 'n' articles of the same type, the next is 75% free
    Scenario Template: Case with only one kind of product
      Given if I buy 2 articles of type 'Banana', the next one is 75 %
      Given I add <numberOfArticle> 'Banana' in my cart
      When I validate my cart
      Then I should pay <totalAmount> EUR
      Examples:
        | numberOfArticle | totalAmount |
        | 1               | 100         |
        | 2               | 200         |
        | 3               | 225         |
        | 4               | 325         |
        | 5               | 425         |
        | 6               | 450         |
        | 7               | 550         |
        | 8               | 650         |
        | 9               | 675         |
        | 10              | 775         |
