package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateOrderPOM {
	
	private WebDriver driver;

	public CreateOrderPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-customer")
	private WebElement customername;
	
	@FindBy(id="input-customer-group")
	private WebElement customergroup;
	
	@FindBy(id="input-firstname")
	private WebElement firstname;
	
	@FindBy(id="input-lastname")
	private WebElement lastname;
	
	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(id="input-telephone")
	private WebElement telephone;
	
	@FindBy(id="button-customer")
	private WebElement continueBtn;
	
	@FindBy(id="input-product")
	private WebElement inputproduct;
	
	@FindBy(id="input-quantity")
	private WebElement productquantity;
	
	@FindBy(id="button-product-add")
	private WebElement addproductBtn;
	
	@FindBy(id="button-cart")
	private WebElement continueproductBtn;
	
	public void sendreturnID(String customername) {
		this.customername.sendKeys(customername);
	}
	
	
	public void selectcustomergroup(String cg) {		
		Select sel = new Select(customergroup);
		sel.selectByVisibleText(cg);		
	}
	
	public void sendfirstname(String firstname) {
		this.firstname.sendKeys(firstname);
	}
	
	public void sendlastname(String lastname) {
		this.lastname.sendKeys(lastname);
	}
	
	public void sendemail(String email) {
		this.email.sendKeys(email);
	}
	
	public void sendtelephone(String telephone) {
		this.telephone.sendKeys(telephone);
	}
	
	public void continueclk() {
		this.continueBtn.click();
	}
	
	public void selectinputproduct() {		
		this.inputproduct.click();
		
	}
	
	public void sendquantity(String productquantity) {
		this.productquantity.sendKeys(productquantity);
	}
	
	public void clickaddproduct() {
		this.addproductBtn.click();
	}
	
	public void continueproductBtn() {
		this.continueproductBtn.click();
	}
	
	
	
	
	
	
	
	
	
	
	

}
