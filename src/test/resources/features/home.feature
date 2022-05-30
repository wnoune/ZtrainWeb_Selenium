Feature: As a customer, I am loggin and I want to shop

  Background:
  Scenario Outline: loggin before shop
    Given User is on the login page
    When User is logging-in with <237pk69@gmail.com > "<password>"
    Then User should be redirected to the home page

    Examples:
      |email             | password|
      |237pk69@gmail.com | P@wk/*69|

  @TEST_OF-830
  Scenario: Display product sheet
    Given User is on the product page
    When User clicks on a product
    Then The product sheet should appear