package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
	
	@FindBy(id="input-order-status")
	private WebElement orderstatus;
	
	@FindBy(id="input-date-added")
	private WebElement dateadded;
	
	@FindBy(id="input-date-modified")
	private WebElement datemodified;
	
	@FindBy(id="button-filter")
	private WebElement filterBtn; 
	
public void sendorderID(String orderID) {		
		this.orderID.sendKeys(orderID);
	}
	
	public void sendcustomername(String customername) {
		this.orderID.clear();
		this.dateadded.clear();
		this.datemodified.clear();
		
		this.customername.sendKeys(customername); 
	}
	
	public void selectorderstatus(String os) {
		this.orderID.clear();
		this.dateadded.clear();
		this.datemodified.clear();
		this.customername.clear();
		Select sel1 = new Select(orderstatus);
		sel1.selectByVisibleText(os);
	}
	
	public void selectdateadded(String dateadded) {
		this.orderID.clear();
		this.datemodified.clear();
		this.customername.clear();
		
		this.dateadded.sendKeys(dateadded);
		
	}
	
	public void selectdatemodified(String datemodified) {
		this.orderID.clear();
		this.dateadded.clear();
		this.customername.clear();
		
		this.datemodified.sendKeys(datemodified);
		
	}
	
	
	public void clickFilterBtn() {
		this.filterBtn.click(); 
	}

}
