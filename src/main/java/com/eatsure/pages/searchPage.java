package com.eatsure.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eatsure.config.configReader;
import com.eatsure.utilities.commonMethods;

public class searchPage {
	
	private commonMethods commonMethods;
	private configReader configReader;
	WebDriver driver;

	public searchPage(WebDriver driver)
	{
		this.commonMethods = new commonMethods(driver);
    	this.configReader = new configReader(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//p[@data-qa='searchOption']")
	private WebElement searchIcon;
	
	@FindBy(xpath = "//input[@type='text']")
	private WebElement searchInputBox;
	
	@FindBy(xpath = "//div[contains(@class,'style__ProdName-sc-1nogo41-15 bnhaAe')]")////a//div//span/../b
	private List<WebElement> productNameList;
	
	
	public void clickOnSearch()
	{
		commonMethods.clickElement(searchIcon);
	}
	
	public void enterProdcutNameToSearch()
	{
		String productName = configReader.getProperty("searchProduct");
		commonMethods.enterText(searchInputBox, productName);
	}
	
	public boolean productSearchDispalyed()
	{
		return commonMethods.isElementDisplayed(productNameList);
	}
	
	public void clickOnProduct()
	{
		String productName = configReader.getProperty("searchProduct");
		if(productNameList.size()>0)
		{
			for(WebElement product : productNameList)
    		{
    			// Replace all commas and hyphens with whitespace and remove extra spaces
    			String productText = product.getText().toLowerCase().replaceAll("[,-]", " ").replaceAll("\\s+", " ").trim(); // Sanitize the result text
    			
    			if(productText.contains(productName))
    			{
    				product.click();
    				break;
    			}
				/*
				 * else if(productText.contains(notServicableLocation)) { result.click(); break;
				 * }
				 */
		}
	}
	
	}
}
