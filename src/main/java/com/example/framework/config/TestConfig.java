package com.example.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Test Configuration class for managing test properties and environment settings
 */
public class TestConfig {
    private static final Logger logger = LoggerFactory.getLogger(TestConfig.class);
    private static Properties properties;
    private static final String CONFIG_FILE = "config.properties";
    
    static {
        loadProperties();
    }
    
    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        properties = new Properties();
        try {
            // Try to load from classpath first
            InputStream inputStream = TestConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
            if (inputStream != null) {
                properties.load(inputStream);
                logger.info("Loaded properties from classpath: {}", CONFIG_FILE);
            } else {
                // Try to load from file system
                properties.load(new FileInputStream(CONFIG_FILE));
                logger.info("Loaded properties from file system: {}", CONFIG_FILE);
            }
        } catch (IOException e) {
            logger.warn("Could not load properties file: {}. Using default values.", CONFIG_FILE);
            setDefaultProperties();
        }
    }
    
    /**
     * Set default properties
     */
    private static void setDefaultProperties() {
        properties = new Properties();
        properties.setProperty("browser", "chrome");
        properties.setProperty("implicit.wait", "10");
        properties.setProperty("explicit.wait", "10");
        properties.setProperty("page.load.timeout", "30");
        properties.setProperty("script.timeout", "30");
        properties.setProperty("base.url", "https://www.google.com");
        properties.setProperty("headless", "false");
        properties.setProperty("window.size", "1920x1080");
        properties.setProperty("screenshot.on.failure", "true");
        properties.setProperty("video.recording", "false");
        properties.setProperty("parallel.execution", "false");
        properties.setProperty("thread.count", "1");
        properties.setProperty("retry.count", "0");
        properties.setProperty("allure.results.directory", "target/allure-results");
        properties.setProperty("cucumber.reports.directory", "target/cucumber-reports");
        properties.setProperty("screenshots.directory", "target/screenshots");
        properties.setProperty("videos.directory", "target/videos");
    }
    
    /**
     * Get property value
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Get property value with default
     * @param key Property key
     * @param defaultValue Default value if property not found
     * @return Property value or default
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Get integer property
     * @param key Property key
     * @return Integer value
     */
    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }
    
    /**
     * Get integer property with default
     * @param key Property key
     * @param defaultValue Default value
     * @return Integer value or default
     */
    public static int getIntProperty(String key, int defaultValue) {
        try {
            return Integer.parseInt(getProperty(key));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Get boolean property
     * @param key Property key
     * @return Boolean value
     */
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
    
    /**
     * Get boolean property with default
     * @param key Property key
     * @param defaultValue Default value
     * @return Boolean value or default
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
    
    /**
     * Get browser type
     * @return Browser type
     */
    public static String getBrowser() {
        return getProperty("browser", "chrome");
    }
    
    /**
     * Get base URL
     * @return Base URL
     */
    public static String getBaseUrl() {
        return getProperty("base.url", "https://www.google.com");
    }
    
    /**
     * Get implicit wait timeout
     * @return Implicit wait timeout in seconds
     */
    public static int getImplicitWait() {
        return getIntProperty("implicit.wait", 10);
    }
    
    /**
     * Get explicit wait timeout
     * @return Explicit wait timeout in seconds
     */
    public static int getExplicitWait() {
        return getIntProperty("explicit.wait", 10);
    }
    
    /**
     * Get page load timeout
     * @return Page load timeout in seconds
     */
    public static int getPageLoadTimeout() {
        return getIntProperty("page.load.timeout", 30);
    }
    
    /**
     * Get script timeout
     * @return Script timeout in seconds
     */
    public static int getScriptTimeout() {
        return getIntProperty("script.timeout", 30);
    }
    
    /**
     * Check if headless mode is enabled
     * @return true if headless mode is enabled
     */
    public static boolean isHeadless() {
        return getBooleanProperty("headless", false);
    }
    
    /**
     * Get window size
     * @return Window size
     */
    public static String getWindowSize() {
        return getProperty("window.size", "1920x1080");
    }
    
    /**
     * Check if screenshot on failure is enabled
     * @return true if screenshot on failure is enabled
     */
    public static boolean isScreenshotOnFailure() {
        return getBooleanProperty("screenshot.on.failure", true);
    }
    
    /**
     * Check if video recording is enabled
     * @return true if video recording is enabled
     */
    public static boolean isVideoRecording() {
        return getBooleanProperty("video.recording", false);
    }
    
    /**
     * Check if parallel execution is enabled
     * @return true if parallel execution is enabled
     */
    public static boolean isParallelExecution() {
        return getBooleanProperty("parallel.execution", false);
    }
    
    /**
     * Get thread count for parallel execution
     * @return Thread count
     */
    public static int getThreadCount() {
        return getIntProperty("thread.count", 1);
    }
    
    /**
     * Get retry count
     * @return Retry count
     */
    public static int getRetryCount() {
        return getIntProperty("retry.count", 0);
    }
    
    /**
     * Get Allure results directory
     * @return Allure results directory
     */
    public static String getAllureResultsDirectory() {
        return getProperty("allure.results.directory", "target/allure-results");
    }
    
    /**
     * Get Cucumber reports directory
     * @return Cucumber reports directory
     */
    public static String getCucumberReportsDirectory() {
        return getProperty("cucumber.reports.directory", "target/cucumber-reports");
    }
    
    /**
     * Get screenshots directory
     * @return Screenshots directory
     */
    public static String getScreenshotsDirectory() {
        return getProperty("screenshots.directory", "target/screenshots");
    }
    
    /**
     * Get videos directory
     * @return Videos directory
     */
    public static String getVideosDirectory() {
        return getProperty("videos.directory", "target/videos");
    }
    
    /**
     * Set property
     * @param key Property key
     * @param value Property value
     */
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
    
    /**
     * Set system property
     * @param key Property key
     * @param value Property value
     */
    public static void setSystemProperty(String key, String value) {
        System.setProperty(key, value);
    }
    
    /**
     * Get system property
     * @param key Property key
     * @return System property value
     */
    public static String getSystemProperty(String key) {
        return System.getProperty(key);
    }
    
    /**
     * Get system property with default
     * @param key Property key
     * @param defaultValue Default value
     * @return System property value or default
     */
    public static String getSystemProperty(String key, String defaultValue) {
        return System.getProperty(key, defaultValue);
    }
} 