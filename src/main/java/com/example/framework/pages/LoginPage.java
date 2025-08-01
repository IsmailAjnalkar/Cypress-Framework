package com.example.framework.pages;

import com.example.framework.core.BasePage;
import com.example.framework.config.TestConfig;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page Object for Login Page
 */
public class LoginPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    
    // Locators
    private static final By EMAIL_FIELD = By.id("email");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By REMEMBER_ME_CHECKBOX = By.id("remember-me");
    private static final By FORGOT_PASSWORD_LINK = By.linkText("Forgot Password");
    private static final By ERROR_MESSAGE = By.className("error-message");
    private static final By VALIDATION_ERROR = By.className("validation-error");
    private static final By EMAIL_FORMAT_ERROR = By.className("email-format-error");
    private static final By WELCOME_MESSAGE = By.className("welcome-message");
    private static final By LOGIN_FORM = By.id("login-form");
    private static final By LOGIN_PAGE_TITLE = By.tagName("h1");
    
    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        String loginUrl = TestConfig.getBaseUrl() + "/login";
        DriverManager.navigateTo(loginUrl);
        waitForPageLoad();
        logger.info("Navigated to login page: {}", loginUrl);
    }
    
    /**
     * Enter email in email field
     * @param email Email to enter
     */
    public void enterEmail(String email) {
        type(EMAIL_FIELD, email);
        logger.info("Entered email: {}", email);
    }
    
    /**
     * Enter password in password field
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        type(PASSWORD_FIELD, password);
        logger.info("Entered password");
    }
    
    /**
     * Click login button
     */
    public void clickLoginButton() {
        click(LOGIN_BUTTON);
        logger.info("Clicked login button");
    }
    
    /**
     * Check remember me checkbox
     */
    public void checkRememberMe() {
        checkCheckbox(REMEMBER_ME_CHECKBOX);
        logger.info("Checked 'Remember me' checkbox");
    }
    
    /**
     * Uncheck remember me checkbox
     */
    public void uncheckRememberMe() {
        uncheckCheckbox(REMEMBER_ME_CHECKBOX);
        logger.info("Unchecked 'Remember me' checkbox");
    }
    
    /**
     * Click forgot password link
     */
    public void clickForgotPasswordLink() {
        click(FORGOT_PASSWORD_LINK);
        logger.info("Clicked 'Forgot Password' link");
    }
    
    /**
     * Get email field value
     * @return Email field value
     */
    public String getEmailFieldValue() {
        return getAttribute(EMAIL_FIELD, "value");
    }
    
    /**
     * Get password field value
     * @return Password field value
     */
    public String getPasswordFieldValue() {
        return getAttribute(PASSWORD_FIELD, "value");
    }
    
    /**
     * Check if remember me checkbox is checked
     * @return true if checkbox is checked
     */
    public boolean isRememberMeChecked() {
        try {
            return Boolean.parseBoolean(getAttribute(REMEMBER_ME_CHECKBOX, "checked"));
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if error message is displayed
     * @return true if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(ERROR_MESSAGE);
    }
    
    /**
     * Get error message text
     * @return Error message text
     */
    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }
    
    /**
     * Check if validation error is displayed
     * @return true if validation error is displayed
     */
    public boolean isValidationErrorDisplayed() {
        return isElementDisplayed(VALIDATION_ERROR);
    }
    
    /**
     * Get validation error text
     * @return Validation error text
     */
    public String getValidationError() {
        return getText(VALIDATION_ERROR);
    }
    
    /**
     * Check if email format error is displayed
     * @return true if email format error is displayed
     */
    public boolean isEmailFormatErrorDisplayed() {
        return isElementDisplayed(EMAIL_FORMAT_ERROR);
    }
    
    /**
     * Get email format error text
     * @return Email format error text
     */
    public String getEmailFormatError() {
        return getText(EMAIL_FORMAT_ERROR);
    }
    
    /**
     * Check if welcome message is displayed
     * @return true if welcome message is displayed
     */
    public boolean isWelcomeMessageDisplayed() {
        return isElementDisplayed(WELCOME_MESSAGE);
    }
    
    /**
     * Get welcome message text
     * @return Welcome message text
     */
    public String getWelcomeMessage() {
        return getText(WELCOME_MESSAGE);
    }
    
    /**
     * Check if login form is displayed
     * @return true if login form is displayed
     */
    public boolean isLoginFormDisplayed() {
        return isElementDisplayed(LOGIN_FORM);
    }
    
    /**
     * Get login page title
     * @return Login page title
     */
    public String getLoginPageTitle() {
        return getText(LOGIN_PAGE_TITLE);
    }
    
    /**
     * Check if email field is enabled
     * @return true if email field is enabled
     */
    public boolean isEmailFieldEnabled() {
        return isElementEnabled(EMAIL_FIELD);
    }
    
    /**
     * Check if password field is enabled
     * @return true if password field is enabled
     */
    public boolean isPasswordFieldEnabled() {
        return isElementEnabled(PASSWORD_FIELD);
    }
    
    /**
     * Check if login button is enabled
     * @return true if login button is enabled
     */
    public boolean isLoginButtonEnabled() {
        return isElementEnabled(LOGIN_BUTTON);
    }
    
    /**
     * Clear email field
     */
    public void clearEmailField() {
        WebElement element = waitForElementVisible(EMAIL_FIELD);
        element.clear();
        logger.info("Cleared email field");
    }
    
    /**
     * Clear password field
     */
    public void clearPasswordField() {
        WebElement element = waitForElementVisible(PASSWORD_FIELD);
        element.clear();
        logger.info("Cleared password field");
    }
    
    /**
     * Clear all form fields
     */
    public void clearAllFields() {
        clearEmailField();
        clearPasswordField();
        uncheckRememberMe();
        logger.info("Cleared all form fields");
    }
    
    /**
     * Wait for login form to be visible
     */
    public void waitForLoginForm() {
        waitForElementVisible(LOGIN_FORM);
        logger.info("Login form is visible");
    }
    
    /**
     * Wait for error message to be visible
     */
    public void waitForErrorMessage() {
        waitForElementVisible(ERROR_MESSAGE);
        logger.info("Error message is visible");
    }
    
    /**
     * Wait for validation error to be visible
     */
    public void waitForValidationError() {
        waitForElementVisible(VALIDATION_ERROR);
        logger.info("Validation error is visible");
    }
    
    /**
     * Wait for welcome message to be visible
     */
    public void waitForWelcomeMessage() {
        waitForElementVisible(WELCOME_MESSAGE);
        logger.info("Welcome message is visible");
    }
    
    /**
     * Check if current page is login page
     * @return true if current page is login page
     */
    public boolean isLoginPage() {
        String currentUrl = DriverManager.getCurrentUrl();
        return currentUrl.contains("/login") || currentUrl.contains("login");
    }
    
    /**
     * Get current page URL
     * @return Current page URL
     */
    public String getCurrentUrl() {
        return DriverManager.getCurrentUrl();
    }
    
    /**
     * Get current page title
     * @return Current page title
     */
    public String getCurrentTitle() {
        return DriverManager.getPageTitle();
    }
} 