package com.eatsure.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private List<WebElement> productNameList;
	
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
	
	@FindBy(xpath = "//div[@data-qa='closeWindowPopup']")
	private WebElement closePopupBtn;
	
	@FindBy(xpath = "//div[@data-qa='totalItemsInCart']")
	private WebElement cartStripCount;
	
	@FindBy(xpath = "//button[@data-qa='ContinueButton']")
	private WebElement viewCartStrip;
	
	@FindBy(xpath = "//div[@data-qa='totalItemsInCart']/following-sibling::div")
	private WebElement priceOnCartStrip;
	
	
	
	public String getBrandPageTitle()
	{
		  String brand = brandName.getText();
		  return brand;
	}
	
	public Map<String, Object> addNormalProduct(String targetProductName) {
	    Map<String, Object> result = new HashMap<>();
	    String pQty = null;
	    String pName = null;

	    // Ensure product list is not empty
	    if (!productNameList.isEmpty()) {
	        // Get the first product name and compare
	        pName = productNameList.get(0).getText();
	        
	        if (pName.equalsIgnoreCase(targetProductName)) {
	            // Click "Add" button for the first product
	            commonMethods.clickElementUsingJavaScript(addButton.get(0));

	            // Wait and get the product details
	            commonMethods.waitForElementToBeVisible(productQty);
	            pQty = productQty.getText(); // Fetch product quantity from PLP

	            // Verify if the quantity matches the cart strip count
	            if (cartStripCount.getText().contains(pQty)) {
	                System.out.println(pName + " (" + pQty + ") - Product added successfully!");
	                result.put("productName", pName);
	                result.put("productQty", pQty);
	                return result;
	            } else {
	                System.out.println("Failed to verify product in cart.");
	            }
	        } else {
	            System.out.println("First product (" + pName + ") does not match the desired product (" + targetProductName + ").");
	        }
	    } else {
	        System.out.println("No products available to add.");
	    }

	    return null; // Return null if the product was not added
	}
	
	
	
	public void increaseQty()
	{
		//addNormalProduct();
		//commonMethods.clickElement(productQty);
		
	}

	public void clickOnViewCart() {
		commonMethods.clickElementUsingJavaScript(viewCartStrip);
		
	}
	    
		
}
