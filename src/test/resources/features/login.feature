Feature: User Anmelden

  Scenario: Verify navigation to Login page
    Given The user launches the browser
    When The user opens the gesundheitspraxis home page
    And The user clicks on the Login button
    And Check that the Login page title is displayed
    And The user enters valid data
    And The user enters Angemeldet bleiben
    And The user clicks on the Anmelden button
    Then The user checks the display of a successful login message
    And The user closes the browser

