package com.eatsure.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eatsure.config.configReader;
import com.eatsure.utilities.commonMethods;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class brandPage {
	
	private commonMethods commonMethods;
	private configReader configReader;
	WebDriver driver;

	public brandPage(WebDriver driver)
	{
		this.commonMethods = new commonMethods(driver);
    	this.configReader = new configReader(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//figure[@data-qa='smallProductCard']/div/child::div[2]/child::button")
	private WebElement productButtonList;
	
	@FindBy(xpath = "//figure[@data-qa='smallProductCard']")
	private List<WebElement> productCardList;
	
	@FindBy(xpath = "//div[@data-qa='productName']")
	private List<WebElement> productName;
	
	@FindBy(xpath = "//div[@data-qa='productPricePlp']")
	private WebElement productPricePlpList;
	
	@FindBy(xpath = "//div[@data-qa='resetCustomizationCTA']")
	private WebElement customizationWindow;
	
	@FindBy(xpath = "//div[@data-qa='brandName']")
	private WebElement brandName;
	
	@FindBy(xpath = "//button[@data-qa='addButton']")
	private List<WebElement> addButton;
	////div[contains(@class,'CardBottom')]/child::div[2]
	////button[@data-qa='addButton']
	
	@FindBy(xpath = "//div[@data-qa = 'quantityToShow']")
	private WebElement productQty;
	
	public String getBrandPageTitle()
	{
		  String brand = brandName.getText();
		  return brand;
	}
	
	public void addNormalProduct() {
	    
		System.out.println(addButton.size());
		
		// Iterate through each Add button and click
        for (int i = 0; i < addButton.size(); i++) {
           commonMethods.clickElementUsingJavaScript(addButton.get(i));
           commonMethods.waitForElementToBeClickable(productQty);
           if(productQty.isDisplayed())
           {
        	   System.out.println("Normal product added");
        	  break;
           }
           
        }
       
        }

}
