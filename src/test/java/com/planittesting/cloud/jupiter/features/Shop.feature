@Regression
Feature: Validate Jupiter Toys Shopping scenarios

  Background:
    Given User is on Jupiter Toys home page

  @TC_003 @Shop
  Scenario: Add items to cart in Shop page and verify count
    Given User goes to Shop Page
    When User clicks buy button 2 times on "Funny Cow"
    And User clicks buy button 1 times on "Fluffy Bunny"
    Then User clicks cart menu
    And User verifies the items are in the cart

  @TC_004 @Shop
  Scenario: Add items to cart in Shop page and verify price & total
    Given User goes to Shop Page
    When User clicks buy button 2 times on "Stuffed Frog"
    And User clicks buy button 5 times on "Fluffy Bunny"
    And User clicks buy button 3 times on "Valentine Bear"
    Then User clicks cart menu
    And User verifies the items are in the cart
    And User verifies the price and total for each product