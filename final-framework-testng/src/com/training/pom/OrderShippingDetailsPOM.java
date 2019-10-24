package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrderShippingDetailsPOM {
	
	private WebDriver driver;

	public OrderShippingDetailsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-shipping-firstname")
	private WebElement firstname;
	
	@FindBy(id="input-shipping-lastname")
	private WebElement lastname;
	
	@FindBy(id="input-shipping-address-1")
	private WebElement address1;
	
	@FindBy(id="input-shipping-city")
	private WebElement city;
	
	@FindBy(id="input-shipping-country")
	private WebElement country;
	
	@FindBy(id="input-shipping-zone")
	private WebElement zone;
	
	@FindBy(id="button-shipping-address")
	private WebElement continueBtn;
	
	public void sendfirstname(String firstname) {
		this.firstname.sendKeys(firstname);
	}
	
	public void sendlastname(String lastname) {
		this.lastname.sendKeys(lastname);
	}
	
	public void sendaddress1(String address1) {
		this.address1.sendKeys(address1);
	}
	
	public void sendcity(String city) {
		this.city.sendKeys(city);
	}
	
	public void selectcountry(String cn) {		
		Select sel = new Select(country);
		sel.selectByVisibleText(cn);		
	}
	
	public void selectzone(String zn) {		
		Select sel = new Select(zone);
		sel.selectByVisibleText(zn);		
	}
	
	public void continueclk() {
		this.continueBtn.click();
	}

}
