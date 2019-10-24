package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPagePOM {
	
	private WebDriver driver;

	public OrdersPagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-order-id")
	private WebElement orderID; 
	
	@FindBy(id="input-customer")
	private WebElement customername;
	
	@FindBy(id="button-filter")
	private WebElement filterBtn; 
	
public void sendorderID(String orderID) {
		
		this.orderID.sendKeys(orderID);
	}
	
	public void sendcustomername(String customername) {
		this.orderID.clear();
		this.customername.sendKeys(customername); 
	}
	
	public void clickFilterBtn() {
		this.filterBtn.click(); 
	}

}
