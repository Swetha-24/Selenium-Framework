package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPOM {
	
private WebDriver driver; 
	
	public RegistrationPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	
		
		@FindBy(id="input-firstname")
		private WebElement firstName;
		
		@FindBy(id="input-lastname")
		private WebElement lastName;
		
		@FindBy(id="input-email")
		private WebElement email;
		
		@FindBy(id="input-telephone")
		private WebElement telephone;
		
		@FindBy(id="input-address-1")
		private WebElement address1;
		
		@FindBy(id="input-address-2")
		private WebElement address2;
		
		@FindBy(id="input-city")
		private WebElement city;
		
		@FindBy(id="input-postcode")
		private WebElement postcode;
		
		@FindBy(id="input-country")
		private WebElement country;
		
		@FindBy(id="input-zone")
		private WebElement state;
		
		@FindBy(id="input-password")
		private WebElement password;
		
		@FindBy(id="input-confirm")
		private WebElement confirmpassword;
		
		@FindBy(xpath="//label[contains(text(),'No')]")
		private WebElement radiobutton;
		
		@FindBy(xpath="//input[@name='agree']")
		private WebElement checkbox;
		
		@FindBy(xpath="//input[@class='btn btn-primary']")
		private WebElement continuebtn;
		
		
		
		
		
		
		
		
		
		public void sendfirstName(String firstName) {
			
			this.firstName.sendKeys(firstName);
		}
		
		public void sendlastName(String lastName) {
			
			this.lastName.sendKeys(lastName);
		}
		
		public void sendemail(String email) {
			
			this.email.sendKeys(email);
		}
		
		public void sendtelephone(String telephone) {
		
			this.telephone.sendKeys(telephone);
		}
		
		public void sendaddress1(String address1) {
			
			this.address1.sendKeys(address1);
		}
		
		public void sendaddress2(String address2) {
			
			this.address2.sendKeys(address2);
		}
		
		public void sendcity(String city) {
			
			this.city.sendKeys(city);
		}
		
		public void sendpostcode(String postcode) {
			
			this.postcode.sendKeys(postcode);
		}
		
		public void selectcountry(String cn) {
			
			Select sel = new Select(country);
			sel.selectByVisibleText(cn);
			
		}
		
		public void selectstate(String st) {
			
			Select sel1 = new Select(state);
			sel1.selectByVisibleText(st);
			
		}
		
		public void sendpassword(String password) {
			
			this.password.sendKeys(password);
		}
		
		public void sendconfirmpassword(String confirmpassword) {
			
			this.confirmpassword.sendKeys(confirmpassword);
		}
		
		
		public void clickradiobutton() {
			this.radiobutton.click();
			
		}
		
		public void clickcheckbox() {
			this.checkbox.click();
			
		}
		
		public void continueclk() {
			this.continuebtn.click();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}



