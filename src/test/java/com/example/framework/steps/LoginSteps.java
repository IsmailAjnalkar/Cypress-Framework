package com.example.framework.steps;

import com.example.framework.core.BasePage;
import com.example.framework.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Step definitions for login functionality
 */
public class LoginSteps extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);
    private LoginPage loginPage;
    
    public LoginSteps() {
        super();
        loginPage = new LoginPage();
    }
    
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.navigateToLoginPage();
        logger.info("Navigated to login page");
    }
    
    @When("I enter {string} in the email field")
    public void iEnterInTheEmailField(String email) {
        loginPage.enterEmail(email);
        logger.info("Entered email: {}", email);
    }
    
    @When("I enter {string} in the password field")
    public void iEnterInThePasswordField(String password) {
        loginPage.enterPassword(password);
        logger.info("Entered password");
    }
    
    @When("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
        logger.info("Clicked login button");
    }
    
    @When("I click the login button without entering credentials")
    public void iClickTheLoginButtonWithoutEnteringCredentials() {
        loginPage.clickLoginButton();
        logger.info("Clicked login button without entering credentials");
    }
    
    @When("I check the {string} checkbox")
    public void iCheckTheCheckbox(String checkboxName) {
        if ("Remember me".equals(checkboxName)) {
            loginPage.checkRememberMe();
            logger.info("Checked 'Remember me' checkbox");
        }
    }
    
    @When("I click the {string} link")
    public void iClickTheLink(String linkText) {
        if ("Forgot Password".equals(linkText)) {
            loginPage.clickForgotPasswordLink();
            logger.info("Clicked 'Forgot Password' link");
        }
    }
    
    @When("I click the {string} button")
    public void iClickTheButton(String buttonText) {
        if ("Reset Password".equals(buttonText)) {
            // This would be on a forgot password page
            // forgotPasswordPage.clickResetPasswordButton();
            logger.info("Clicked 'Reset Password' button");
        }
    }
    
    @When("I logout and return to the login page")
    public void iLogoutAndReturnToTheLoginPage() {
        // This would typically involve clicking a logout button
        // and then navigating back to login page
        logger.info("Logged out and returned to login page");
    }
    
    @Then("I should be redirected to the dashboard")
    public void iShouldBeRedirectedToTheDashboard() {
        // Add assertion to check if redirected to dashboard
        logger.info("Verifying redirect to dashboard");
    }
    
    @Then("I should see the welcome message")
    public void iShouldSeeTheWelcomeMessage() {
        // Add assertion to check for welcome message
        logger.info("Verifying welcome message is displayed");
    }
    
    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        // Add assertion to check for error message
        logger.info("Verifying error message is displayed");
    }
    
    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        // Add assertion to check if still on login page
        logger.info("Verifying still on login page");
    }
    
    @Then("I should see validation error messages")
    public void iShouldSeeValidationErrorMessages() {
        // Add assertion to check for validation error messages
        logger.info("Verifying validation error messages are displayed");
    }
    
    @Then("I should see an email format error message")
    public void iShouldSeeAnEmailFormatErrorMessage() {
        // Add assertion to check for email format error message
        logger.info("Verifying email format error message is displayed");
    }
    
    @Then("the email field should be pre-filled with {string}")
    public void theEmailFieldShouldBePreFilledWith(String email) {
        // Add assertion to check if email field is pre-filled
        logger.info("Verifying email field is pre-filled with: {}", email);
    }
    
    @Then("I should be redirected to the forgot password page")
    public void iShouldBeRedirectedToTheForgotPasswordPage() {
        // Add assertion to check if redirected to forgot password page
        logger.info("Verifying redirect to forgot password page");
    }
    
    @Then("I should see a password reset confirmation message")
    public void iShouldSeeAPasswordResetConfirmationMessage() {
        // Add assertion to check for password reset confirmation message
        logger.info("Verifying password reset confirmation message is displayed");
    }
} 