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
		
	}
	
	
	@Test(priority = 2, enabled = true, groups = {"regression"})
	public void verifyValidLocationTakesUserToTheHome() throws InterruptedException
	{
		landingPage = new landingPage(driver);
		landingPage.enterValidLocation();
			
		boolean searchResult = landingPage.getSearchResult();
		Assert.assertTrue(searchResult);
		
		landingPage.selectLocation();
		
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
