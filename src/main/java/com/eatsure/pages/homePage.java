package com.eatsure.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eatsure.config.configReader;
import com.eatsure.utilities.Base;
import com.eatsure.utilities.commonMethods;

public class homePage extends Base {
	
	private commonMethods commonMethods;
	private configReader configReader;
	WebDriver driver;
	
	public homePage(WebDriver driver)
	{
		this.commonMethods = new commonMethods(driver);
    	this.configReader = new configReader(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	

	@FindBy(xpath = "//div[@data-qa='allBrandsIcon']/a")
	private List<WebElement> brandList;
	
	@FindBy(xpath = "//div[contains(text(),'New Location Detected')]")
	private WebElement newLocationDetectedPopupText;
	
	@FindBy(xpath = "//div[contains(text(),'New Location Detected')]/parent::div/div[4]/child::button[2]")
	private WebElement yesButtonFromNewLocationDetectedPopup;
	
	
	public void clickOnbrand()
	{
		commonMethods.waitForElementToBeClickable(yesButtonFromNewLocationDetectedPopup);
		boolean newLocationDetected = commonMethods.isElementDisplayed(yesButtonFromNewLocationDetectedPopup);
    	if(newLocationDetected==true)
    	{
    		commonMethods.clickElement(yesButtonFromNewLocationDetectedPopup);
    	}
    	commonMethods.normalWait(3);
		if(brandList.size()>0)
		{
			for(WebElement brand : brandList)
			{
				String brandName = brand.getText().toLowerCase();
				if(brandName.equalsIgnoreCase("Pizzas"))
				{
					commonMethods.clickElementUsingJavaScript(brand);
					//commonMethods.clickElement(brand);
					//brand.click();
					break;
				}
			}
		}
	}
	
	
	
}
