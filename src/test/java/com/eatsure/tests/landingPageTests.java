package com.eatsure.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eatsure.config.configReader;
import com.eatsure.pages.landingPage;
import com.eatsure.utilities.Base;

public class landingPageTests extends Base{
	
    landingPage landingPage;
    configReader configReader;
	
	
	@Test(priority = 1, groups = {"regression"})
	public void verifyLocationSearchResulWithValidLocationSearch()
	{	
		landingPage = new landingPage(driver);
		
		//enter location
		landingPage.enterValidLocation();
		
		//Assert location result are displayed
		boolean searchResult = landingPage.getSearchResult();
		Assert.assertTrue(searchResult);
		
		
		
		/*
		 * // Convert WebElement list to String list and clean up unwanted characters
		 * List<String> stringList = new ArrayList<>(); for (WebElement element :
		 * resultList) { String locationText = element.getText(); if (locationText !=
		 * null) { // Remove (, ), and - locationText =
		 * locationText.toLowerCase().replaceAll("[()\\-]", "").replaceAll("\\s+",
		 * " ").trim(); stringList.add(locationText); } }
		 * 
		 * // Print the cleaned-up String list
		 * 
		 * for (String str : stringList) { System.out.println(str); }
		 */
		 
	}
	
	
	@Test(priority = 2, enabled = true, groups = {"regression"})
	public void verifyValidLocationTakesUserToTheHome() throws InterruptedException
	{
		landingPage = new landingPage(driver);
		landingPage.enterValidLocation();
		//WebElement locationSearchBox = driver.findElement(By.xpath("//input[@data-qa='locationTextBox']"));
		//locationSearchBox.sendKeys("Hsr Layout");
		
		boolean searchResult = landingPage.getSearchResult();
		Assert.assertTrue(searchResult);
		
		landingPage.selectLocation();
		
		/*
		 * List<WebElement> resultList =
		 * driver.findElements(By.xpath("//ul[@data-qa='autoSuggestions']//li"));
		 * Assert.assertTrue(resultList.size()>0,
		 * "Location Search results not visible!");
		 * 
		 * for(WebElement result : resultList) { // Replace all commas and hyphens with
		 * whitespace and remove extra spaces String resultText =
		 * result.getText().toLowerCase().replaceAll("[,-]", " ").replaceAll("\\s+",
		 * " ").trim(); // Sanitize the result text
		 * 
		 * if(resultText.contains("hsr layout")) { result.click(); break; } }
		 */
		Thread.sleep(3000);
		String homeTitle = driver.getTitle().toLowerCase();
		Assert.assertTrue(homeTitle.contains("hsr layout"), "Title doesn't match the expected location!");
		
	}
	
	
	@Test(priority = 3, enabled = true)
	public void verifyInValidLocationSearch()
	{
		landingPage = new landingPage(driver);
		landingPage.enterInValidLocation();
		
		boolean searchResult = landingPage.isResultDisplayed();
		Assert.assertFalse(searchResult);
		
		/*
		 * //WebElement locationSearchBox =
		 * driver.findElement(By.xpath("//input[@data-qa='locationTextBox']"));
		 * //locationSearchBox.sendKeys("%(&^%(*&%&^(*"); List<WebElement> resultList =
		 * driver.findElements(By.xpath("//ul[@data-qa='autoSuggestions']//li"));
		 * 
		 * Assert.assertFalse(resultList.size()>0,
		 * "Location Search results found for invalid loaction!"); String
		 * expectedLocation = "Pali"; //Boolean locationFound = false; for(WebElement
		 * result : resultList) { if(result.getText().contains(expectedLocation)) {
		 * //System.out.println(result.getText()); result.click(); //locationFound =
		 * true; break; } }
		 */
	}
	
	
	@Test(priority = 4, enabled = true)
	public void verifyNotServicableLocationSearch()
	{
		landingPage = new landingPage(driver);
		
		landingPage.enterNotServiceableLocation();
		landingPage.selectLocation();
		
		driver.findElement(By.xpath("//button[normalize-space()='Confirm Location and Proceed']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-qa='notServiceableErrorHeading']")).isDisplayed(), "Location is servicable!");
	}
}
