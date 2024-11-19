Feature: Portfolio

  Background:
    Given The user launches the browser
    When The user opens the gesundheitspraxis home page
    Then Check that the home page title is displayed

  Scenario: Check that Portfolio is displayed on home page
    Then The user clicks on the Portfolio
    Then Check that the title Meine Leistungen is displayed
   # And The user closes the browser

  Scenario: Check that "Infusionstherapie" is displayed on home page
    Then The user clicks on the Portfolio
    Then Check that the title Meine Leistungen is displayed
    Then The user clicks on the Infusionstherapie
    Then Check that the title Infusionstherapie is displayed
    And The user closes the browser

  Scenario: Check that "Neuraltherapie" is displayed on home page
    Then The user clicks on the Portfolio
    Then Check that the title Meine Leistungen is displayed
    Then The user clicks on the Neuraltherapie
    Then Check that the title Neuraltherapie is displayed
    And The user closes the browser

  Scenario: Check that "Schrüpftherapie" is displayed on home page
    And The user clicks on the Portfolio
    And The user click on the Schröpftherapie
    Then Check that Schrüpftherapie is displayed is displayed
    And The user closes the browser

  Scenario: Check that "Phytotherapie" is displayed on home page
    Given The user launches the browser
    When The user opens the gesundheitspraxis home page
    Then Check that "Phytotherapie" is displayed on the home page
    And The user closes the browser

  Scenario: Check that "Aromatherapie" is displayed on home page
    Given The user launches the browser
    When The user opens the gesundheitspraxis home page
    Then Check that "Aromatherapie" is displayed on the home page
    And The user closes the browser

  Scenario: Check that "Ernährungsberatung" is displayed on home page
    Given The user launches the browser
    When The user opens the gesundheitspraxis home page
    Then Check that "Ernährungsberatung" is displayed on the home page
    And The user closes the browser

  Scenario: Check that "Labordiagnostik" is displayed on home page
    Given The user launches the browser
    When The user opens the gesundheitspraxis home page
    Then Check that "Labordiagnostik" is displayed on the home page
    And The user closes the browser
