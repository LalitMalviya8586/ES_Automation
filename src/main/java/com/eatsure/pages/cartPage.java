package com.eatsure.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.eatsure.config.configReader;
import com.eatsure.utilities.commonMethods;

public class cartPage {
	
	private commonMethods commonMethods;
	private configReader configReader;
	WebDriver driver;

	public cartPage(WebDriver driver)
	{
		this.commonMethods = new commonMethods(driver);
    	this.configReader = new configReader(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//button[@data-qa='ContinueButton']")
	private WebElement viewCartStrip;
	
	
	
	

}
