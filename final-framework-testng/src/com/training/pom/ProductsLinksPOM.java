package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsLinksPOM {
	
	private WebDriver driver;

	public ProductsLinksPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-category")
	private WebElement categories;
	
	public void sendcategories(String categories) {		
		this.categories.sendKeys(categories);
	}

}
