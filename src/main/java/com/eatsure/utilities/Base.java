package com.eatsure.utilities;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.eatsure.config.configReader;

import org.apache.commons.io.FileUtils;


public class Base {

	protected WebDriver driver;
	protected ExtentReports extent;
    protected ExtentTest test;
    public configReader configReader;

	@BeforeMethod(groups = {"regression"})
	public void inittilizeBrowserAndOpenWebURL(ITestResult result)
	{
		configReader = new configReader(driver);
		String browserName = configReader.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		driver.get(configReader.getProperty("web_url"));
		
		ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        test = extent.createTest(result.getMethod().getMethodName());
		
	}
	
	 @AfterMethod(groups = {"regression"}) 
     public void teardown(ITestResult result) {
         if (result.getStatus() == ITestResult.FAILURE) {
             test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
             String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
             test.addScreenCaptureFromPath(screenshotPath);
         } else if (result.getStatus() == ITestResult.SUCCESS) {
             test.log(Status.PASS, "Test Passed");
         } else if (result.getStatus() == ITestResult.SKIP) {
             test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
         }
         extent.flush();

         if (driver != null) {
             driver.quit();
         }
     }

	 private String takeScreenshot(String methodName) {
         File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
         String destPath = "screenshots/" + methodName + ".png";
         try {
             FileUtils.copyFile(srcFile, new File(destPath));
         } catch (IOException e) {
             e.printStackTrace();
         }
         return destPath;
     }
 }
	

