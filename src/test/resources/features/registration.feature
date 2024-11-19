Feature: User Registration

  Scenario: Register with valid data
    Given The user launches the browser
    When The user opens the gesundheitspraxis home page
    And The user clicks on the Login button
    And Check that the Login page title is displayed
    Then The user clicks on the Kontoerstellen button
    Then Check that the Konto erstellen page title is displayed
    And The user enters valid details
    And The user clicks the Weiter button
    Then A confirmation message is displayed with the text "Ihr Konto wurde erfolgreich erstellt"
    And The user is redirected to the  home page


  Scenario: Register with invalid data
    Given The user launches the browser
    When The user opens the gesundheitspraxis home page
    And The user clicks on the Login button
    And Check that the Login page title is displayed
    Then The user clicks on the Kontoerstellen button
    When The user enters invalid details:
      | Field         | Value                |
      | Vorname       |                      |
      | Nachname      |                      |
      | Email         | invalid-email-format |
      | Telefonnummer | abc123               |
      | Passwort      | short                |
    And The user clicks the Weiter button
    Then The following error messages are displayed:
      | Field         | Error Message                                     |
      | Vorname       | "Vorname darf nicht leer sein"                    |
      | Nachname      | "Nachname darf nicht leer sein"                   |
      | Email         | "Bitte geben Sie eine gültige E-Mail-Adresse ein" |
      | Telefonnummer | "Telefonnummer ist ungültig"                      |
      | Passwort      | "Passwort muss mindestens 8 Zeichen lang sein"    |