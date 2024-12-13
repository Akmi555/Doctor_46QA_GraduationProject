
# Doctor Selenium

## Website Overview

This project automates the testing for a web application using Selenium. The tests validate essential functionalities, including user registration, login, appointment booking, cancellation, and the ability to modify registered user data.

## Project Overview

**Doctor Selenium** is a test automation project designed for testing a medical clinic website, ensuring the key functionalities such as user registration, login, navigation, appointment booking, cancellation of appointments, and the ability to modify registered user data work smoothly. It employs **Selenium** for browser automation, written in Java, and integrates with **Jenkins** for continuous integration and test reporting. **Allure** is used for generating beautiful and detailed test reports.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Setup](#project-setup)
- [Running Tests](#running-tests)
- [Test Scenarios](#test-scenarios)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This project automates the testing of various functionalities on the website using **Selenium**. The following core functionalities are covered:

- User registration and login.
- Navigation through the site.
- Appointment booking and cancellation functionality.
- Ability to modify registered user data.

---

## Features

- **Automated browser testing** with Selenium.
- Modular **Page Object Model (POM)** for clean and maintainable test code.
- Support for multiple browsers via **WebDriverManager**.
- Integrated with **JUnit** for test execution.
- Continuous integration with **Jenkins** for running tests and generating reports.
- **Allure reports** for detailed visualization of test results.
- Ability to book and cancel appointments for registered users.

---

## Technologies Used

- **Java**: Core programming language.
- **Selenium WebDriver**: For browser automation.
- **JUnit**: For running tests and assertions.
- **Gradle**: Build automation tool.
- **WebDriverManager**: For managing WebDriver binaries.
- **Jenkins**: For continuous integration and test reporting.
- **Allure**: For generating detailed test reports.

---

## Project Setup

### Prerequisites

1. Install **Java 11** or later.
2. Install **Gradle** from [Gradle website](https://gradle.org/).
3. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/Doctor_Selenium.git
   cd Doctor_Selenium
   ```

### Configure the Project

1. Ensure the `build.gradle` file includes all required dependencies:

   ```groovy
   dependencies {
       implementation 'org.seleniumhq.selenium:selenium-java:4.x.x'
       testImplementation 'org.junit.jupiter:junit-jupiter-api:5.x.x'
       testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.x.x'
       implementation 'io.github.bonigarcia:webdrivermanager:5.x.x'
       testImplementation 'io.qameta.allure:allure-junit5:2.x.x'
   }
   ```

2. Sync the dependencies using Gradle:

   ```bash
   gradle build
   ```

3. Place your test files under `src/test/java`.

---

## Running Tests

To run tests, use the following commands:

1. **Run all tests**:

   ```bash
   gradle clean test
   ```

2. **Run tests with specific classes**:

   ```bash
   gradle clean test -Dtest.single=YourTestClassName
   ```

3. **Generate reports**:

   After tests are run, Jenkins will automatically generate reports, including **Allure** reports. You can view detailed reports with the following command:

   ```bash
   allure serve
   ```

   Allure reports provide beautiful visualizations of test results, including test status, execution times, and detailed logs.

---

## Test Scenarios

Test files are located in the `src/test/java` directory and include scenarios for:

- **Login**: Verifies the user login functionality.
- **Registration**: Tests user registration.
- **Navigation**: Checks navigation between pages.
- **Appointment Booking**: Automates the appointment booking process.
- **Appointment Cancellation**: Verifies the ability to cancel appointments.
- **User Data Modification**: Verifies the ability to modify registered user data after login.

---

## Contributing

We welcome contributions to improve this project! To contribute:

1. Fork the repository.
2. Create a feature branch:

   ```bash
   git checkout -b feature/your-feature
   ```

3. Commit your changes:

   ```bash
   git commit -m "Add your feature"
   ```

4. Push your changes:

   ```bash
   git push origin feature/your-feature
   ```

5. Open a pull request.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
