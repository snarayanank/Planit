@Regression
Feature: Validate Jupiter Toys contact page

  Background:
    Given User is on Jupiter Toys home page

  @TC_001 @Contact
  Scenario: Validate mandatory fields on contact page
    Given User goes to Contact Page
    When User clicks submit button
    Then Validate errors on contact page
    And User populate mandatory fields
      | ForeName    | Email                 |  Message                          |
      | Sathiya     | snarayanank@gmail.com |  Enjoyed shopping at Jupiter Toys |
    Then Validate no errors on contact page

  @TC_002 @Contact
  Scenario Outline: Validate successful feedback submission on contact page
    Given User goes to Contact Page
    And User populate mandatory fields "<ForeName>" "<Email>" "<Message>"
    When User clicks submit button
    And Validate successful submission message for user "<ForeName>"

    Examples:
      | ForeName    | Email             |  Message                                  |
      | Tester1     | tester1@gmail.com |  Enjoyed shopping at Jupiter Toys         |
      | Tester2     | tester2@gmail.com |  Not happy with shopping at Jupiter Toys  |
      | Tester3     | tester3@gmail.com |  Need a refund for an incorrect order     |
      | Tester4     | tester4@gmail.com |  Not received the items that I ordered    |
      | Tester5     | tester5@gmail.com |  Satisfied with the quality of the Toys   |