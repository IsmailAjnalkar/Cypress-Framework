package com.example.framework.listeners;

import com.example.framework.core.DriverManager;
import com.example.framework.config.TestConfig;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TestNG Listener for test execution monitoring and reporting
 */
public class TestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);
    
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: {} - Method: {}", result.getName(), result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {} - Duration: {}ms", result.getName(), result.getEndMillis() - result.getStartMillis());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {} - Method: {}", result.getName(), result.getMethod().getMethodName());
        
        // Take screenshot on failure
        if (TestConfig.isScreenshotOnFailure()) {
            takeScreenshot(result.getName());
        }
        
        // Log exception details
        if (result.getThrowable() != null) {
            logger.error("Test failure reason: {}", result.getThrowable().getMessage());
            result.getThrowable().printStackTrace();
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {} - Method: {}", result.getName(), result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn("Test failed but within success percentage: {} - Method: {}", 
                   result.getName(), result.getMethod().getMethodName());
    }
    
    @Override
    public void onStart(ITestContext context) {
        logger.info("Starting test suite: {} - Start time: {}", context.getName(), context.getStartDate());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        logger.info("Finished test suite: {} - End time: {}", context.getName(), context.getEndDate());
        logger.info("Test suite summary - Passed: {}, Failed: {}, Skipped: {}", 
                   context.getPassedTests().size(), 
                   context.getFailedTests().size(), 
                   context.getSkippedTests().size());
    }
    
    /**
     * Take screenshot and save to file
     * @param testName Test name for file naming
     */
    private void takeScreenshot(String testName) {
        try {
            // Create screenshots directory if it doesn't exist
            String screenshotsDir = TestConfig.getScreenshotsDirectory();
            Path dir = Paths.get(screenshotsDir);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            
            // Generate filename with timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String filename = testName.replaceAll("[^a-zA-Z0-9.-]", "_") + "_" + timestamp + ".png";
            Path screenshotPath = dir.resolve(filename);
            
            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            
            // Save screenshot
            Files.write(screenshotPath, screenshot);
            logger.info("Screenshot saved: {}", screenshotPath.toString());
            
        } catch (IOException e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Error taking screenshot: {}", e.getMessage());
        }
    }
    
    /**
     * Get test method name
     * @param result Test result
     * @return Method name
     */
    private String getTestMethodName(ITestResult result) {
        return result.getMethod().getMethodName();
    }
    
    /**
     * Get test class name
     * @param result Test result
     * @return Class name
     */
    private String getTestClassName(ITestResult result) {
        return result.getTestClass().getName();
    }
    
    /**
     * Get test parameters
     * @param result Test result
     * @return Parameters as string
     */
    private String getTestParameters(ITestResult result) {
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object param : parameters) {
                sb.append(param.toString()).append(", ");
            }
            return sb.substring(0, sb.length() - 2);
        }
        return "";
    }
    
    /**
     * Get test duration in milliseconds
     * @param result Test result
     * @return Duration in milliseconds
     */
    private long getTestDuration(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }
    
    /**
     * Get test duration in seconds
     * @param result Test result
     * @return Duration in seconds
     */
    private double getTestDurationInSeconds(ITestResult result) {
        return getTestDuration(result) / 1000.0;
    }
    
    /**
     * Format duration for logging
     * @param durationInMillis Duration in milliseconds
     * @return Formatted duration string
     */
    private String formatDuration(long durationInMillis) {
        long seconds = durationInMillis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        
        if (minutes > 0) {
            return String.format("%dm %ds", minutes, seconds);
        } else {
            return String.format("%ds", seconds);
        }
    }
} 