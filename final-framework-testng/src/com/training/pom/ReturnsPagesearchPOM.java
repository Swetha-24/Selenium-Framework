package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReturnsPagesearchPOM {
	
	private WebDriver driver;

	public ReturnsPagesearchPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-return-id")
	private WebElement returnID; 
	
	@FindBy(id="input-customer")
	private WebElement customername;
	
	@FindBy(id="button-filter")
	private WebElement filterBtn; 
	
public void sendreturnID(String orderID) {
		
		this.returnID.sendKeys(orderID);
	}
	
	public void sendcustomername(String customername) {
		this.returnID.clear();
		 
		this.customername.sendKeys(customername); 
	}
	
	public void clickFilterBtn() {
		this.filterBtn.click(); 
	}

}
