package com.eatsure.tests;

import java.util.Map;

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
	public void verifyAddingProductToTheCartFromPlp()
	{
		landingPage = new landingPage(driver);
		home = new homePage(driver);
		brand = new brandPage(driver);
		
		//enter location
		landingPage.enterValidLocation();
		landingPage.selectLocation();
		home.clickOnbrand();
		String targetProduct = "Red Velvet Mousse";
		Map<String, Object> result = brand.addNormalProduct(targetProduct);
		// Extract values from the returned Map 
        //Boolean isProductAdded = (Boolean) result.get("isProductAdded");
        String plpQty = (String) result.get("productQty"); // Quantity from PLP
		String pName = (String) result.get("productName");// product name from PLP
		System.out.println(plpQty);
		System.out.println(pName);
		//Assert.assertTrue(isProductAdded, "Qty did not match on cart strip");
		
	}
}
