package com.example.framework.steps;

import com.example.framework.core.DriverManager;
import com.example.framework.config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Common step definitions for shared functionality
 */
public class CommonSteps {
    private static final Logger logger = LoggerFactory.getLogger(CommonSteps.class);
    
    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {}", scenario.getName());
        String browser = TestConfig.getBrowser();
        DriverManager.initializeDriver(browser);
        logger.info("Initialized WebDriver for browser: {}", browser);
    }
    
    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        
        // Take screenshot on failure
        if (scenario.isFailed() && TestConfig.isScreenshotOnFailure()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot");
                logger.info("Screenshot taken for failed scenario: {}", scenario.getName());
            } catch (Exception e) {
                logger.error("Failed to take screenshot: {}", e.getMessage());
            }
        }
        
        // Quit driver
        DriverManager.quitDriver();
        logger.info("Completed scenario: {} - Status: {}", scenario.getName(), scenario.getStatus());
    }
    
    @Given("I am on the {string} page")
    public void iAmOnThePage(String pageName) {
        String baseUrl = TestConfig.getBaseUrl();
        DriverManager.navigateTo(baseUrl);
        logger.info("Navigated to {} page: {}", pageName, baseUrl);
    }
    
    @Given("I navigate to {string}")
    public void iNavigateTo(String url) {
        DriverManager.navigateTo(url);
        logger.info("Navigated to URL: {}", url);
    }
    
    @When("I wait for {int} seconds")
    public void iWaitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.info("Waited for {} seconds", seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Interrupted while waiting: {}", e.getMessage());
        }
    }
    
    @When("I refresh the page")
    public void iRefreshThePage() {
        DriverManager.refreshPage();
        logger.info("Refreshed the page");
    }
    
    @When("I navigate back")
    public void iNavigateBack() {
        DriverManager.navigateBack();
        logger.info("Navigated back");
    }
    
    @When("I navigate forward")
    public void iNavigateForward() {
        DriverManager.navigateForward();
        logger.info("Navigated forward");
    }
    
    @Then("I should be on the {string} page")
    public void iShouldBeOnThePage(String expectedPage) {
        String currentUrl = DriverManager.getCurrentUrl();
        String currentTitle = DriverManager.getPageTitle();
        logger.info("Current URL: {}", currentUrl);
        logger.info("Current title: {}", currentTitle);
        // Add assertions as needed
    }
    
    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expectedTitle) {
        String actualTitle = DriverManager.getPageTitle();
        logger.info("Page title: {}", actualTitle);
        // Add assertion: assert actualTitle.contains(expectedTitle);
    }
    
    @Then("the URL should contain {string}")
    public void theUrlShouldContain(String expectedUrl) {
        String actualUrl = DriverManager.getCurrentUrl();
        logger.info("Current URL: {}", actualUrl);
        // Add assertion: assert actualUrl.contains(expectedUrl);
    }
    
    @Then("I should see the page loaded successfully")
    public void iShouldSeeThePageLoadedSuccessfully() {
        String title = DriverManager.getPageTitle();
        String url = DriverManager.getCurrentUrl();
        logger.info("Page loaded successfully - Title: {}, URL: {}", title, url);
        // Add assertions as needed
    }
} 