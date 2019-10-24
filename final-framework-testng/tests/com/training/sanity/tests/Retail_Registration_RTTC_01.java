package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;

import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class Retail_Registration_RTTC_01 {
	
	private WebDriver driver;
	private String baseUrl;
	private RegistrationPOM registrationPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	
	@BeforeClass
	  public void beforeClass() throws IOException {
		  properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			properties.load(inStream);
	  }
	
	 @BeforeMethod
	  public void beforeMethod() {
		  driver = DriverFactory.getDriver(DriverNames.CHROME);
		  registrationPOM = new RegistrationPOM(driver); 
			baseUrl = properties.getProperty("baseURL");			
			screenShot = new ScreenShot(driver); 
			// open the browser 
			driver.get(baseUrl);
	 }
			
			@AfterMethod
			  public void afterMethod() throws InterruptedException {
				  Thread.sleep(1000);
					driver.quit();
			  }
			
			@Test
			public void amouseover() throws InterruptedException {
				WebElement wb = driver.findElement(By.xpath("//i[@class='fa fa-user-o']"));
				Actions act = new Actions (driver);
				act.moveToElement(wb).build().perform();
				driver.findElement(By.xpath("//span[contains(text(),'LOGIN / REGISTER')]")).click();
				driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
			
	  
	  
	  registrationPOM.sendfirstName("reva");
	  registrationPOM.sendlastName("sharma");
	  registrationPOM.sendemail("revasharma@gmail.com");
	  registrationPOM.sendtelephone("9345677833");
	  registrationPOM.sendaddress1("Jayanagar");
	  registrationPOM.sendaddress2("bangalore");
	  registrationPOM.sendcity("bangalore");
	  registrationPOM.sendpostcode("560018");	  
	  registrationPOM.selectcountry("India");
	  registrationPOM.selectstate("Karnataka");
	  registrationPOM.sendpassword("reva123");
	  registrationPOM.sendconfirmpassword("reva123");	
	 
	  registrationPOM.clickradiobutton();
	 
	  registrationPOM.clickcheckbox();
	 
	  registrationPOM.continueclk();
	 
		screenShot.captureScreenShot("First");
		
		
  }
  
  
  
 
	  
  }

  

  



