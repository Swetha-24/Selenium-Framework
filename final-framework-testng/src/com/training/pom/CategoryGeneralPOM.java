package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryGeneralPOM {

	private WebDriver driver;

	public CategoryGeneralPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-name1")
	private WebElement categoryName;
	
	@FindBy(id="input-meta-title1")
	private WebElement megatagTitle;
	
	@FindBy(id="input-meta-description1")
	private WebElement megatagTitledesc;
	
	@FindBy(xpath="//i[@class='fa fa-save']")
	private WebElement saveBtn;
	
	
	public void sendcategoryname(String categoryName) {		
		this.categoryName.sendKeys(categoryName);
	}
	
	public void sendmegatagTitle(String megatagTitle) {		
		this.megatagTitle.sendKeys(megatagTitle);
	}
	
	public void sendmegatagTitledesc(String megatagTitledesc) {		
		this.megatagTitledesc.sendKeys(megatagTitledesc);
	}

	public void savebuttonclick() {		
		this.saveBtn.click();
	}

	
	
	
	
	
	
	
	
	
}
