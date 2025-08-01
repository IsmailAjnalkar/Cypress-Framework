package com.example.framework.listeners;

import io.qameta.allure.Attachment;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allure Listener for enhanced reporting
 */
public class AllureListener implements TestLifecycleListener {
    private static final Logger logger = LoggerFactory.getLogger(AllureListener.class);
    
    @Override
    public void beforeTestStop(TestResult result) {
        // Add screenshot to Allure report on test failure
        if (result.getStatus() == io.qameta.allure.model.Status.FAILED) {
            try {
                byte[] screenshot = takeScreenshot();
                saveScreenshot(screenshot);
                logger.info("Screenshot added to Allure report for failed test: {}", result.getName());
            } catch (Exception e) {
                logger.error("Failed to add screenshot to Allure report: {}", e.getMessage());
            }
        }
    }
    
    /**
     * Take screenshot
     * @return Screenshot as byte array
     */
    private byte[] takeScreenshot() {
        try {
            TakesScreenshot ts = (TakesScreenshot) com.example.framework.core.DriverManager.getDriver();
            return ts.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
            return new byte[0];
        }
    }
    
    /**
     * Save screenshot to Allure report
     * @param screenshot Screenshot as byte array
     */
    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }
} 