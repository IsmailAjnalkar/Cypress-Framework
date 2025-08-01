package com.example.framework.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * WebDriver Manager class for handling browser initialization and management
 */
public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    
    private DriverManager() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Initialize WebDriver based on browser type
     * @param browserType Type of browser to initialize
     * @return WebDriver instance
     */
    public static WebDriver initializeDriver(String browserType) {
        WebDriver webDriver = null;
        
        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                webDriver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
                
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                webDriver = new EdgeDriver(edgeOptions);
                break;
                
            case "safari":
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                webDriver = new SafariDriver(safariOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        
        // Set implicit wait
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        // Set page load timeout
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        
        // Set script timeout
        webDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        
        driver.set(webDriver);
        
        // Initialize WebDriverWait
        wait.set(new WebDriverWait(webDriver, Duration.ofSeconds(10)));
        
        return webDriver;
    }
    
    /**
     * Get current WebDriver instance
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver not initialized. Call initializeDriver() first.");
        }
        return driver.get();
    }
    
    /**
     * Get WebDriverWait instance
     * @return WebDriverWait instance
     */
    public static WebDriverWait getWait() {
        if (wait.get() == null) {
            throw new IllegalStateException("WebDriverWait not initialized. Call initializeDriver() first.");
        }
        return wait.get();
    }
    
    /**
     * Quit WebDriver and clean up resources
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            wait.remove();
        }
    }
    
    /**
     * Navigate to URL
     * @param url URL to navigate to
     */
    public static void navigateTo(String url) {
        getDriver().get(url);
    }
    
    /**
     * Get current page title
     * @return Page title
     */
    public static String getPageTitle() {
        return getDriver().getTitle();
    }
    
    /**
     * Get current page URL
     * @return Current URL
     */
    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
    
    /**
     * Refresh current page
     */
    public static void refreshPage() {
        getDriver().navigate().refresh();
    }
    
    /**
     * Navigate back
     */
    public static void navigateBack() {
        getDriver().navigate().back();
    }
    
    /**
     * Navigate forward
     */
    public static void navigateForward() {
        getDriver().navigate().forward();
    }
} 