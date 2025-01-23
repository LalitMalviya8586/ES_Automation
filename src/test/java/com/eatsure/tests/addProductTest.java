package com.eatsure.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eatsure.pages.brandPage;
import com.eatsure.pages.homePage;
import com.eatsure.pages.landingPage;
import com.eatsure.utilities.Base;

public class addProductTest extends Base {

	landingPage landingPage;
	brandPage brand;
	homePage home;
	
	@Test
	public void verifyNavigetingToBrandPage()
	{
		landingPage = new landingPage(driver);
		home = new homePage(driver);
		brand = new brandPage(driver);
		
		//enter location
		landingPage.enterValidLocation();
		landingPage.selectLocation();
		home.clickOnbrand();
		String brandName = brand.getBrandPageTitle();
		Assert.assertTrue(brandName.toLowerCase().contains("faasos"), "Brand name does not contain the expected brand name");
	}
	
	@Test
	public void verifyAddingTheProductToTheCartFromPlp()
	{
		landingPage = new landingPage(driver);
		home = new homePage(driver);
		brand = new brandPage(driver);
		
		//enter location
		landingPage.enterValidLocation();
		landingPage.selectLocation();
		home.clickOnbrand();
		brand.addNormalProduct();
		
		
	}
}
