package com.eatsure.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eatsure.pages.landingPage;
import com.eatsure.pages.searchPage;
import com.eatsure.utilities.Base;

public class searchTests extends Base {
	
	searchPage search;
	landingPage landingPage;
	
	@Test(groups = {"regression"}, dependsOnGroups = {"regression"})
	public void verifySearchListWithValidProduct()
	{
		search = new searchPage(driver);
		landingPage = new landingPage(driver);
		
		//enter location
		landingPage.enterValidLocation();
		landingPage.selectLocation();
		search.clickOnSearch();
		search.enterProdcutNameToSearch();
		Assert.assertTrue(search.productSearchDispalyed(), "No search result found!");
		
	}
	
	@Test(groups = {"regression"})
	public void verifySearchIfValidProductMatchesAndClickOnit()
	{
		search = new searchPage(driver);
		landingPage = new landingPage(driver);
		
		//enter location
		landingPage.enterValidLocation();
		landingPage.selectLocation();
		search.clickOnSearch();
		search.enterProdcutNameToSearch();
		search.clickOnProduct();
	}
	
	public void verifySearchingNonExistingProduct()
	{
		
	}

}
