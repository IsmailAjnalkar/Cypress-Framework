package com.example.framework.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Base Page Object class providing common web element interactions
 */
public abstract class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    
    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = DriverManager.getWait();
        this.actions = new Actions(driver);
    }
    
    /**
     * Wait for element to be visible
     * @param locator Element locator
     * @return WebElement
     */
    protected WebElement waitForElementVisible(By locator) {
        logger.info("Waiting for element to be visible: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for element to be clickable
     * @param locator Element locator
     * @return WebElement
     */
    protected WebElement waitForElementClickable(By locator) {
        logger.info("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be present in DOM
     * @param locator Element locator
     * @return WebElement
     */
    protected WebElement waitForElementPresent(By locator) {
        logger.info("Waiting for element to be present: {}", locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Click on element
     * @param locator Element locator
     */
    protected void click(By locator) {
        logger.info("Clicking element: {}", locator);
        WebElement element = waitForElementClickable(locator);
        element.click();
    }
    
    /**
     * Click on element using JavaScript
     * @param locator Element locator
     */
    protected void clickWithJavaScript(By locator) {
        logger.info("Clicking element with JavaScript: {}", locator);
        WebElement element = waitForElementPresent(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    
    /**
     * Type text into element
     * @param locator Element locator
     * @param text Text to type
     */
    protected void type(By locator, String text) {
        logger.info("Typing text '{}' into element: {}", text, locator);
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Type text into element without clearing
     * @param locator Element locator
     * @param text Text to type
     */
    protected void typeWithoutClear(By locator, String text) {
        logger.info("Typing text '{}' into element without clearing: {}", text, locator);
        WebElement element = waitForElementVisible(locator);
        element.sendKeys(text);
    }
    
    /**
     * Get text from element
     * @param locator Element locator
     * @return Element text
     */
    protected String getText(By locator) {
        logger.info("Getting text from element: {}", locator);
        WebElement element = waitForElementVisible(locator);
        return element.getText();
    }
    
    /**
     * Get attribute value from element
     * @param locator Element locator
     * @param attribute Attribute name
     * @return Attribute value
     */
    protected String getAttribute(By locator, String attribute) {
        logger.info("Getting attribute '{}' from element: {}", attribute, locator);
        WebElement element = waitForElementPresent(locator);
        return element.getAttribute(attribute);
    }
    
    /**
     * Check if element is displayed
     * @param locator Element locator
     * @return true if element is displayed
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            return element.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("Element not displayed: {}", locator);
            return false;
        }
    }
    
    /**
     * Check if element is enabled
     * @param locator Element locator
     * @return true if element is enabled
     */
    protected boolean isElementEnabled(By locator) {
        try {
            WebElement element = waitForElementVisible(locator);
            return element.isEnabled();
        } catch (TimeoutException e) {
            logger.warn("Element not enabled: {}", locator);
            return false;
        }
    }
    
    /**
     * Select option from dropdown by visible text
     * @param locator Element locator
     * @param visibleText Visible text to select
     */
    protected void selectByVisibleText(By locator, String visibleText) {
        logger.info("Selecting option '{}' from dropdown: {}", visibleText, locator);
        WebElement element = waitForElementVisible(locator);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }
    
    /**
     * Select option from dropdown by value
     * @param locator Element locator
     * @param value Value to select
     */
    protected void selectByValue(By locator, String value) {
        logger.info("Selecting option with value '{}' from dropdown: {}", value, locator);
        WebElement element = waitForElementVisible(locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }
    
    /**
     * Select option from dropdown by index
     * @param locator Element locator
     * @param index Index to select
     */
    protected void selectByIndex(By locator, int index) {
        logger.info("Selecting option at index '{}' from dropdown: {}", index, locator);
        WebElement element = waitForElementVisible(locator);
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    
    /**
     * Check checkbox
     * @param locator Element locator
     */
    protected void checkCheckbox(By locator) {
        logger.info("Checking checkbox: {}", locator);
        WebElement element = waitForElementClickable(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }
    
    /**
     * Uncheck checkbox
     * @param locator Element locator
     */
    protected void uncheckCheckbox(By locator) {
        logger.info("Unchecking checkbox: {}", locator);
        WebElement element = waitForElementClickable(locator);
        if (element.isSelected()) {
            element.click();
        }
    }
    
    /**
     * Hover over element
     * @param locator Element locator
     */
    protected void hoverOver(By locator) {
        logger.info("Hovering over element: {}", locator);
        WebElement element = waitForElementVisible(locator);
        actions.moveToElement(element).perform();
    }
    
    /**
     * Right click on element
     * @param locator Element locator
     */
    protected void rightClick(By locator) {
        logger.info("Right clicking element: {}", locator);
        WebElement element = waitForElementVisible(locator);
        actions.contextClick(element).perform();
    }
    
    /**
     * Double click on element
     * @param locator Element locator
     */
    protected void doubleClick(By locator) {
        logger.info("Double clicking element: {}", locator);
        WebElement element = waitForElementVisible(locator);
        actions.doubleClick(element).perform();
    }
    
    /**
     * Drag and drop element
     * @param sourceLocator Source element locator
     * @param targetLocator Target element locator
     */
    protected void dragAndDrop(By sourceLocator, By targetLocator) {
        logger.info("Dragging element {} to {}", sourceLocator, targetLocator);
        WebElement source = waitForElementVisible(sourceLocator);
        WebElement target = waitForElementVisible(targetLocator);
        actions.dragAndDrop(source, target).perform();
    }
    
    /**
     * Get all elements matching locator
     * @param locator Element locator
     * @return List of WebElements
     */
    protected List<WebElement> findElements(By locator) {
        logger.info("Finding elements: {}", locator);
        return driver.findElements(locator);
    }
    
    /**
     * Wait for page to load
     */
    protected void waitForPageLoad() {
        logger.info("Waiting for page to load");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
    
    /**
     * Scroll to element
     * @param locator Element locator
     */
    protected void scrollToElement(By locator) {
        logger.info("Scrolling to element: {}", locator);
        WebElement element = waitForElementPresent(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Scroll to bottom of page
     */
    protected void scrollToBottom() {
        logger.info("Scrolling to bottom of page");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
    
    /**
     * Scroll to top of page
     */
    protected void scrollToTop() {
        logger.info("Scrolling to top of page");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }
    
    /**
     * Wait for element to disappear
     * @param locator Element locator
     */
    protected void waitForElementToDisappear(By locator) {
        logger.info("Waiting for element to disappear: {}", locator);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for text to be present in element
     * @param locator Element locator
     * @param text Text to wait for
     */
    protected void waitForTextToBePresent(By locator, String text) {
        logger.info("Waiting for text '{}' to be present in element: {}", text, locator);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }
    
    /**
     * Wait for URL to contain text
     * @param text Text to wait for in URL
     */
    protected void waitForUrlToContain(String text) {
        logger.info("Waiting for URL to contain: {}", text);
        wait.until(ExpectedConditions.urlContains(text));
    }
    
    /**
     * Wait for title to contain text
     * @param text Text to wait for in title
     */
    protected void waitForTitleToContain(String text) {
        logger.info("Waiting for title to contain: {}", text);
        wait.until(ExpectedConditions.titleContains(text));
    }
    
    /**
     * Accept alert
     */
    protected void acceptAlert() {
        logger.info("Accepting alert");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
    
    /**
     * Dismiss alert
     */
    protected void dismissAlert() {
        logger.info("Dismissing alert");
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }
    
    /**
     * Get alert text
     * @return Alert text
     */
    protected String getAlertText() {
        logger.info("Getting alert text");
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
    
    /**
     * Switch to frame by index
     * @param index Frame index
     */
    protected void switchToFrame(int index) {
        logger.info("Switching to frame at index: {}", index);
        driver.switchTo().frame(index);
    }
    
    /**
     * Switch to frame by name or id
     * @param nameOrId Frame name or id
     */
    protected void switchToFrame(String nameOrId) {
        logger.info("Switching to frame: {}", nameOrId);
        driver.switchTo().frame(nameOrId);
    }
    
    /**
     * Switch to default content
     */
    protected void switchToDefaultContent() {
        logger.info("Switching to default content");
        driver.switchTo().defaultContent();
    }
    
    /**
     * Switch to window by title
     * @param title Window title
     */
    protected void switchToWindowByTitle(String title) {
        logger.info("Switching to window with title: {}", title);
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().contains(title)) {
                return;
            }
        }
        driver.switchTo().window(currentWindow);
    }
} 