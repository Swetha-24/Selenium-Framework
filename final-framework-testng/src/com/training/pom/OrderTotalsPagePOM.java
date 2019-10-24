package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrderTotalsPagePOM {
	
	private WebDriver driver;

	public OrderTotalsPagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-shipping-method")
	private WebElement shippingmethod;
	
	@FindBy(id="input-payment-method")
	private WebElement paymentmethod;
	
	@FindBy(id="button-save")
	private WebElement savebtn;
	
	public void selectshippingmethod(String sm) {		
		Select sel = new Select(shippingmethod);
		sel.selectByVisibleText(sm);		
	}
	
	public void selectpaymentmethod(String pm) {		
		Select sel = new Select(paymentmethod);
		sel.selectByVisibleText(pm);		
	}
	
	public void saveclk() {
		this.savebtn.click();
	}
	
	
	
	
	
	
	
	
	
	

}
