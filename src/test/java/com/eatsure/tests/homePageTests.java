package com.eatsure.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.eatsure.utilities.Base;

public class homePageTests extends Base {
	
	
	@Test(priority = 1, dependsOnMethods = {"verifyValidLocationSearch"})
	public void verifyLoginWithValidCredentials()
	{
		WebElement newLocationDetectedPopUP = driver.findElement(By.xpath("//div[contains(text(), \"New Location Detected\")]"));
		WebElement yesProceedbtn = driver.findElement(By.xpath("//button[text()='Yes, Proceed']"));
		if(newLocationDetectedPopUP.isDisplayed())
		{
			yesProceedbtn.click();
		}
	}

}
