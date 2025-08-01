# BDD Selenium Framework with Java

A comprehensive Behavior Driven Development (BDD) automation framework built with Selenium WebDriver, Cucumber, and Java. This framework provides a robust foundation for web application testing with support for multiple browsers, parallel execution, and detailed reporting.

## 🚀 Features

### Core Features
- **BDD Approach**: Write tests in Gherkin syntax using Cucumber
- **Multi-Browser Support**: Chrome, Firefox, Edge, Safari
- **Page Object Model**: Maintainable and reusable page objects
- **Parallel Execution**: Run tests in parallel for faster execution
- **Cross-Platform**: Works on Windows, macOS, and Linux

### Advanced Features
- **Automatic Driver Management**: WebDriverManager handles driver setup
- **Smart Waits**: Explicit and implicit wait strategies
- **Screenshot Capture**: Automatic screenshots on test failures
- **Video Recording**: Optional video recording of test execution
- **Data-Driven Testing**: Support for Excel, CSV, and JSON test data
- **API Testing**: Integrated API testing capabilities
- **Database Testing**: Database connectivity and validation

### Reporting & Monitoring
- **Allure Reports**: Beautiful and interactive HTML reports
- **Cucumber Reports**: Detailed feature and scenario reports
- **TestNG Reports**: Comprehensive test execution reports
- **Logging**: Structured logging with Logback
- **Email Notifications**: Test result notifications via email

## 📁 Project Structure

```
bdd-selenium-framework/
├── src/
│   ├── main/java/com/example/framework/
│   │   ├── core/
│   │   │   ├── DriverManager.java
│   │   │   └── BasePage.java
│   │   ├── config/
│   │   │   └── TestConfig.java
│   │   ├── pages/
│   │   │   └── LoginPage.java
│   │   └── listeners/
│   │       ├── TestListener.java
│   │       └── AllureListener.java
│   └── test/
│       ├── java/com/example/framework/
│       │   ├── steps/
│       │   │   ├── CommonSteps.java
│       │   │   └── LoginSteps.java
│       │   └── runners/
│       │       └── TestRunner.java
│       └── resources/
│           ├── features/
│           │   └── login.feature
│           └── testdata/
├── pom.xml
├── testng.xml
├── config.properties
└── README.md
```

## 🛠️ Prerequisites

- **Java 11 or higher**
- **Maven 3.6 or higher**
- **Git** (for version control)

## 📦 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd bdd-selenium-framework
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Verify installation**
   ```bash
   mvn test
   ```

## 🚀 Quick Start

### Running Tests

1. **Run all tests**
   ```bash
   mvn test
   ```

2. **Run specific test suite**
   ```bash
   mvn test -DsuiteXmlFile=testng.xml
   ```

3. **Run with specific browser**
   ```bash
   mvn test -Dbrowser=firefox
   ```

4. **Run in headless mode**
   ```bash
   mvn test -Dheadless=true
   ```

5. **Run in parallel**
   ```bash
   mvn test -Dparallel=true -DthreadCount=4
   ```

### Running Specific Tests

1. **Run smoke tests**
   ```bash
   mvn test -Dcucumber.filter.tags="@smoke"
   ```

2. **Run regression tests**
   ```bash
   mvn test -Dcucumber.filter.tags="@regression"
   ```

3. **Run specific feature**
   ```bash
   mvn test -Dcucumber.features="src/test/resources/features/login.feature"
   ```

## 📋 Configuration

### Browser Configuration
```properties
# config.properties
browser=chrome
headless=false
window.size=1920x1080
```

### Wait Configuration
```properties
implicit.wait=10
explicit.wait=10
page.load.timeout=30
script.timeout=30
```

### Parallel Execution
```properties
parallel.execution=false
thread.count=1
```

## 📝 Writing Tests

### Feature Files (Gherkin)
```gherkin
Feature: Login Functionality
  As a user
  I want to be able to login to the application
  So that I can access my account

  @smoke @login
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter "testuser@example.com" in the email field
    And I enter "password123" in the password field
    And I click the login button
    Then I should be redirected to the dashboard
    And I should see the welcome message
```

### Step Definitions
```java
@When("I enter {string} in the email field")
public void iEnterInTheEmailField(String email) {
    loginPage.enterEmail(email);
    logger.info("Entered email: {}", email);
}
```

### Page Objects
```java
public class LoginPage extends BasePage {
    private static final By EMAIL_FIELD = By.id("email");
    
    public void enterEmail(String email) {
        type(EMAIL_FIELD, email);
        logger.info("Entered email: {}", email);
    }
}
```

## 📊 Reports

### Allure Reports
```bash
# Generate Allure report
mvn allure:report

# Open Allure report
mvn allure:serve
```

### Cucumber Reports
Reports are automatically generated in `target/cucumber-reports/`

### TestNG Reports
Reports are automatically generated in `target/surefire-reports/`

## 🔧 Framework Components

### DriverManager
- Manages WebDriver instances
- Supports multiple browsers
- Handles driver lifecycle

### BasePage
- Common web element interactions
- Wait strategies
- Screenshot capabilities

### TestConfig
- Configuration management
- Environment-specific settings
- Property file handling

### Page Objects
- Encapsulate page elements
- Provide business logic methods
- Maintain element locators

## 🧪 Test Categories

### Smoke Tests
- Critical functionality tests
- Quick execution
- Basic validation

### Regression Tests
- Comprehensive test coverage
- All functionality validation
- Longer execution time

### API Tests
- REST API testing
- Data validation
- Integration testing

### Visual Tests
- Screenshot comparison
- UI validation
- Visual regression testing

## 📈 Best Practices

### Test Organization
1. **Feature-based structure**
2. **Clear naming conventions**
3. **Reusable step definitions**
4. **Data-driven approach**

### Page Object Model
1. **Single responsibility**
2. **Encapsulation**
3. **Reusability**
4. **Maintainability**

### Test Data Management
1. **External test data**
2. **Environment-specific data**
3. **Data validation**
4. **Clean test data**

## 🔍 Troubleshooting

### Common Issues

1. **WebDriver not found**
   ```bash
   mvn clean install
   ```

2. **Tests not running**
   ```bash
   mvn clean test
   ```

3. **Browser compatibility**
   - Update WebDriver versions
   - Check browser compatibility

4. **Parallel execution issues**
   - Reduce thread count
   - Check resource availability

### Debug Mode
```bash
mvn test -Ddebug=true
```

## 🚀 CI/CD Integration

### GitHub Actions
```yaml
name: BDD Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: mvn test
      - uses: actions/upload-artifact@v2
        with:
          name: test-reports
          path: target/
```

### Jenkins Pipeline
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'cucumber-pretty.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }
    }
}
```

## 📚 Additional Resources

- [Selenium Documentation](https://selenium.dev/documentation/)
- [Cucumber Documentation](https://cucumber.io/docs/)
- [TestNG Documentation](https://testng.org/doc/)
- [Allure Documentation](https://docs.qameta.io/allure/)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Selenium WebDriver team
- Cucumber team
- TestNG team
- Allure reporting team
- WebDriverManager team

---

**Happy Testing! 🧪✨** 