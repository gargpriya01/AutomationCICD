@tag
  Feature: Error Validation
    I want to use this template for my feature file

    @ErrorValidation
  Scenario Outline:Title of your scenario outline
  Given I landed on Ecommere Page
  When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      |name                   |  password  |
      |test2@yahoo.com        |   Test@123 |
