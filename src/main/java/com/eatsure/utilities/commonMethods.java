package com.eatsure.utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class commonMethods {

	private WebDriver driver;
	private WebDriverWait wait;

    // Constructor
    public commonMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Default wait time
    }

    // Wait for element to be visible
    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    // Wait for list of elements to be visible
    public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    // Wait for element to be clickable
    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Click element
    public void clickElement(WebElement element) {
        waitForElementToBeClickable(element).click();
    }

    // Enter text
    public void enterText(WebElement element, String text) {
        WebElement webElement = waitForElementToBeVisible(element);
        webElement.clear();
        webElement.sendKeys(text);
    }

    // Wait for presence of element located by locator
    public WebElement waitForElementToBePresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for page to load completely
    public void waitForPageToLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    // Scroll to element
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Check if element is displayed
    public boolean isElementDisplayed(WebElement element) {
        try {
            return waitForElementToBeVisible(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Check id list of elements are displayed
    public boolean isElementDisplayed(List<WebElement> elements) {
        try {
            for (WebElement element : elements) {
                if (!waitForElementToBeVisible(element).isDisplayed()) {
                    return false; // Return false if any element is not displayed
                }
            }
            return true; // Return true if all elements are displayed
        } catch (Exception e) {
            return false; // Return false if an exception occurs
        }
    }
    
    // Click an element using JavaScript Executor
    public void clickElementUsingJavaScript(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }
    
    // Wait for an element to be clickable and click it using JavaScript.
    public void waitAndClickElementUsingJavaScript(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        clickElementUsingJavaScript(element);
    }
    
    // Normal wait (Thread.sleep) for a specified number of seconds.
    public void normalWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted during normal wait", e);
        }
    }
}

