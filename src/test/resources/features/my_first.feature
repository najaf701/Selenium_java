Feature: Login
  As a user I should able to login into my app

  Scenario Outline: I login with valid credential
    Given User is on the login page
    When User enters valid credentials '<username>' and '<password>'
    Then Verify the sorting order displayed for Z-A on the All Items page
    Then Verify the price order high-low displayed on the All Items page
    Then Add multiple items to the card and validate the checkout journey

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |
