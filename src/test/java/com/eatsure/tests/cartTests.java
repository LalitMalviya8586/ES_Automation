package com.eatsure.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.eatsure.pages.brandPage;
import com.eatsure.pages.cartPage;
import com.eatsure.pages.homePage;
import com.eatsure.pages.landingPage;
import com.eatsure.utilities.Base;

public class cartTests extends Base {
	
	landingPage landingPage;
	brandPage brand;
	homePage home;
	cartPage cart;
	
	@Test
	public void testAddSingleNormalProductToCartAndVerify() {
		landingPage = new landingPage(driver);
		home = new homePage(driver);
		brand = new brandPage(driver);
		cart = new cartPage(driver);
		
	    // 1. Navigate to product page
		home.clickOnbrand();
	    // 2. Add product to cart
		Map<String, Object> result = brand.addNormalProduct(null);
		// Extract values from the returned Map
        Boolean isProductAdded = (Boolean) result.get("isProductAdded");
        String plpQty = (String) result.get("productQty"); // Quantity from PLP
		// 3. Go to cart and verify the product is added
        
		brand.clickOnViewCart();
				
		
	}
	
	@Test
	public void testAddMultipleProductsToCartAndValidateTotalPrice() {
	    // Steps:
	    // 1. Add multiple products to cart
	    // 2. Navigate to cart page
	    // 3. Validate that all items are displayed correctly with correct price
	}
	
	@Test
	public void testIncreaseProductQuantityAndValidatePrice() {
	    // Steps:
	    // 1. Add a product to cart
	    // 2. Increase quantity (e.g., from 1 to 3)
	    // 3. Verify total price is updated correctly
	}
	

}
