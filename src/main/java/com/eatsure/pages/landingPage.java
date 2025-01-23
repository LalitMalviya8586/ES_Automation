package com.eatsure.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eatsure.config.configReader;
import com.eatsure.utilities.Base;
import com.eatsure.utilities.commonMethods;

public class landingPage extends Base {

	public WebDriver driver;
	private commonMethods commonMethods;
    private configReader configReader;

    public landingPage(WebDriver driver) {
    	this.commonMethods = new commonMethods(driver);
    	this.configReader = new configReader(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

        
    @FindBy(xpath = "//input[@data-qa='locationTextBox']")
    private WebElement locationSearchField;
    
    @FindBy(xpath = "//ul[@data-qa='autoSuggestions']")
    private WebElement autoSuggestions;

    @FindBy(xpath = "//ul[@data-qa='autoSuggestions']//li") 
    private List<WebElement> locationSearchResults;

    @FindBy(id = "locationErrorMessage")
    private WebElement locationErrorMessage;
    
    @FindBy(xpath = "//div[contains(text(),'New Location Detected')]")
	private WebElement newLocationDetectedPopupText;
	
	@FindBy(xpath = "//div[contains(text(),'New Location Detected')]/parent::div/div[4]/child::button[2]")
	private WebElement yesButtonFromNewLocationDetectedPopup;
	
    
    
    
    public void enterValidLocation()
    {
    	String validLocation = configReader.getProperty("validLocation");
    	commonMethods.enterText(locationSearchField,validLocation);
    }
    
    public void enterInValidLocation()
    {
    	String inValidLocation = configReader.getProperty("InvalidLocation");
    	commonMethods.enterText(locationSearchField,inValidLocation);
    }
    
    public void enterNotServiceableLocation()
    {
    	String notServiceableLocation = configReader.getProperty("notServicableLocation");
    	commonMethods.enterText(locationSearchField,notServiceableLocation);
    }
    
    public boolean getSearchResult()
    {
    	 commonMethods.waitForElementsToBeVisible(locationSearchResults);
    	 return true;
    }
    
    public boolean isResultDisplayed()
    {
    	return commonMethods.isElementDisplayed(autoSuggestions);
    }    
    
    public void selectLocation()
    {
    	String validLocation = configReader.getProperty("validLocation");
    	String notServicableLocation = configReader.getProperty("notServicableLocation");
    	
    	if(locationSearchResults.size()>=0)    
    	{
    		for(WebElement result : locationSearchResults)
    		{
    			// Replace all commas and hyphens with whitespace and remove extra spaces
    			String resultText = result.getText().toLowerCase().replaceAll("[,-]", " ").replaceAll("\\s+", " ").trim(); // Sanitize the result text
    			
    			if(resultText.contains(validLocation))
    			{
    				result.click();
    				//commonMethods.waitForElementToBeClickable(yesButtonFromNewLocationDetectedPopup);
    				break;
    			}
    			else if(resultText.contains(notServicableLocation))
    			{
    				result.click();
    				break;
    			}
    		}
    	}
    	commonMethods.normalWait(3);
    }
	
}
