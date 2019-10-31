package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsGeneralPOM {
	
	private WebDriver driver;

	public ProductsGeneralPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-name1")
	private WebElement productName;
	
	@FindBy(id="input-meta-title1")
	private WebElement megatagTitle;
	
	
	public void sendproductName(String productName) {		
		this.productName.sendKeys(productName);
	}
	
	public void sendmegatagTitle(String megatagTitle) {		
		this.megatagTitle.sendKeys(megatagTitle);
	}
}
