Feature: As a customer, I am loggin and I want to shop

  Background:
    Given User is on the homepage
      | email             | password |
      | 237pk69@gmail.com | P@wk/*69 |


  @TEST_OF-830
  Scenario: Display product sheet
    When User clicks on a product
    Then The product sheet should appear

  @TEST_OF-831
  Scenario: Add product to cart from product page
    When User clicks on a cart icon of product
    And User clicks on card icon
    Then The product should be visible in the card

  @TEST_OF-83
  Scenario: Trash cart
    When User adds products to cart
    And User clicks on card icon
    And User clicks on trash cart button
    And User clicks on card icon
    Then The cart should be emtying


  @TEST_OF-839
  Scenario: Delete product to card
    When User adds products to cart
    And User clicks on card icon
    And User clicks on delete icon of product
    Then The product is not already visible in the card

  @TEST_OF-840
  Scenario: Log out of account
    When User move to account icon and clicks on logout
    Then User should be redirected on login page