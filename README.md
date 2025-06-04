## Use CMD terminal
- Run all tests using Maven: mvn test
- Run a specific test class: mvn -Dtest="*SauceEndToEndTest" test

## SOLID Principles

### Single Responsibility Principle (SRP):
- **Each class has one responsibility.**
    - `LoginPageSauce`, `LoginPageAE`, `LoginPageLiverpool`: Each handles login logic for its specidfic site.
    - `ScreenshoUtil`: Handles only screenshot functionality.
    - `BaseTest`: Handles test setup and teardown
    - `LoginLocators`: Only responsible for storing locators.
    - `TestData`: Only responsible for storing test data (URLs and credentials).

### Open/Close Principle (OCP):
- **Classes are open for extension, closed for modification.**
    - `LoginFacade`: To add support for a new site, create a new page class implementing `LoginPage` interface,
        so any can be used wherever a `LoginPage` is expected.
    - `LoginLocators`: Add new site locators without modifying existing ones.
    - `TestData`: Add new data without modifying the existing one

### Liskov Substitution Principle (LSP):
- **Subtypes can replace their base types without breaking the code.**
    - All login page classes (`LoginPageSauce`, `LoginPageAE`, `LoginPageLiverpool`) implement the `LoginPage` interface,
        so any can be used wherever a `LoginPage` is expected.

### Interface Segregation Principle (ISP):
- **Clients should not be forced to depend on interfaces they do not use.**
    - Interface like `LoginPage` and `LogoutPage` are small and focused.
        Only pages that support implement `LogoutPage`
    - `LoginLocators`: Only exposes locators needed for login/logout
    - `TestData`: Only exposes relevant constants.

### Dependency Inversion Principle (DIP):
- **High-level modules depend on abstractions, not concrete implementations.**
    - `LoginFacade` depends on the `LoginPage` interface, not specific page classes.

## Design Patterns

### Singleton Patter:
- **Ensures a class has only one instance and provides a global point of access.**
    - `DriverManager` Manages a single WebDriver instance for the framework.

### Facade Pattern:
- **Provides a unified interface to a set of interfaces in a subsistem.**
    - `LoginFacade`: Simplifies login acctions for different sites behind a single interface.

### Page Object Model (POM):
- **Encapsulates page elements and actions in classes.**
    - `LoginPageSauce`, `LoginPageAE`, `LoginPageLiverpool`: Each represents a web page and its actions.

### Utility Pattern:
- **Provides reusable utility methods.**
    - `ScreenshotUtil`: Contains statis methods for taking screenshots.

### Object Repository pattern:
- **Centralize the storage and management of web element locators separately from the test scripts**
    - `LoginLocators`: Contains and manage the login locators for each site/page.

### Constants/Configuration Patter:
- **Design approach that centralizes all constant values and configuration settings used throughout the test**
    **automtaion framework, making them easily maintainable and reusable**
    - `TestData`: Contains URLs and credentials for each site/page.

## Framework Flow

1. **Test Initialization**
    - `BaseTest` (in `src/test/java/com/yourcompany/base`) sets up the WebDriver using the `DriverManager` singleton before each test and tears it down after.

2. **Test Execution**
    - Test classes (e.g., `LoginSauceTest`) extend `BaseTest` and use the WebDriver instance.
    - The test navigates to the target site and uses `LoginFacade` to perform login actions.

3. **Login Process**
    - `LoginFacade` receives the WebDriver and user credentials.
    - It creates the appropriate page object (e.g., `LoginPageSauce`) via the `LoginPage` interface and calls its `login` method.

4. **Page Actions**
    - The page object interacts with the web page using Selenium WebDriver, encapsulating all element locators and actions.

5. **Utilities**
    - If a screenshot is needed, `ScreenshotUtil.takeScreenshot(driver, filePath)` is called.

6. **Test Teardown**
    - After the test, `BaseTest` ensures the WebDriver is properly closed.


# Project Structure

SeleniumFramework/
│
├── pom.xml
├── .gitignore
├── reports/
│    └── ExtentManager.java
├── src/
│   ├── main/
│   │   └── java/
│   │        └── com/
│   │             └── yourcompany/
│   │                  ├── constants/
│   │                  │    └── TestData.java
│   │                  ├── driver/
│   │                  │    └── DriverManager.java
│   │                  ├── facade/
│   │                  │    └── LoginFacade.java
│   │                  ├── locators/
│   │                  │    └── LoginLocators.java
│   │                  ├── pages/
│   │                  │    ├── saucedemopages/
│   │                  │    │    └── LoginPageSauce.java
│   │                  │    ├── automationexercisepages/
│   │                  │    │    └── LoginPageAE.java
│   │                  │    ├── liverpoolpages/
│   │                  │    │    └── LoginPageLiverpool.java
│   │                  │    └── LoginPage.java   # (interface)
│   │                  └── utils/
│   │                       └── ScreenshotUtil.java
│   └── test/
│        └── java/
│             └── com/
│                  └── yourcompany/
│                       ├── base/
│                       │    └── BaseTest.java
│                       ├── sauceTests/
│                       │    └── LoginSauceTest.java
│                       ├── liverpoolTests/
│                       │    └── LoginLiverpoolTest.java
│                       └── ... (other test packages and classes)
│
└── target/
     └── surefire-reports/
          ├── emailable-report.html
          ├── index.html
          └── ... (other test reports)

# Keypoints
### This structure is modular, follows best practices, and is ready for scaling and maintenance.

- src/main/java/com/yourcompany/ contains all your main framework code:
    - constants/: Centralized test data (URLs, credentials).
    - driver/: WebDriver singleton manager.
    - facade/: Facade pattern for login flows.
    - locators/: Object repository for element locators.
    - pages/: Page Object Model classes for each site.
    -utils/: Utility classes (e.g., screenshots).
- src/test/java/com/yourcompany/ contains all your test code:
    -base/: Base test class (TestNG logic, setup/teardown).
    - sauceTests/, liverpoolTests/, etc.: Test classes for each site or feature.
- reports/: Contains ExtentManager (for ExtentReports setup).
- target/surefire-reports/: Maven Surefire and TestNG test reports (auto-generated).
- .gitignore: Should be at the root, alongside pom.xml.


## Framework Flow Diagram

+---------------------+
|     Test Class      |  (e.g., LoginSauceTest)
+----------+----------+
           |
           v
+---------------------+
|      BaseTest       |  (sets up/tears down WebDriver via DriverManager Singleton)
+----------+----------+
           |
           v
+---------------------+
|   DriverManager     |  (Singleton: provides WebDriver instance)
+----------+----------+
           |
           v
+---------------------+
|   LoginFacade       |  (Facade: simplifies login for different sites)
+----------+----------+
           |
           v
+-----------------------+
| LoginPage (interface) |  (Abstraction for login pages)
+-----------+-----------+
           |
   +-------+-------+-------+
   |       |       |       |
   v       v       v       v
+-----------------+-----------------+-------------------+
| LoginPageSauce  |  LoginPageAE    |LoginPageLiverpool |
| (POM)           |  (POM)          |(POM)              | 
+-----------------+-----------------+-------------------+
           |                |
           |                |
           v                v
+---------------------+   +---------------------+
|   LoginLocators     |   |     TestData        |
| (Object Repository) |   | (Constants/Config)  |
+---------------------+   +---------------------+
           |                |
           |                v
           |              +---------------------+
           |              |  (Used for URLs,    |
           |              |   credentials, etc) |
           |              +---------------------+
           v
+---------------------+
| Selenium WebDriver  |  (Interacts with the browser)
+---------------------+

[Optional at any step]
           |
           v
+---------------------+
|  ScreenshotUtil     |  (Utility: takes screenshots)
+---------------------+