package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CreateOrderPOM;
import com.training.pom.LoginPOM;
import com.training.pom.OrdersPagePOM;

import com.training.pom.ReturnsPagesearchPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ReturnOrder_RTTC018 {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private ReturnsPagesearchPOM returnsPagesearchPOM;
	
	
	
	@BeforeSuite
	  public void beforeClass() throws IOException {
		  properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/others.properties");
			properties.load(inStream);
	  }
	
	
	@BeforeClass
	  public void beforeMethod() {
		  driver = DriverFactory.getDriver(DriverNames.CHROME);
		  loginPOM = new LoginPOM(driver); 
		 
		  returnsPagesearchPOM = new ReturnsPagesearchPOM(driver);
			baseUrl = properties.getProperty("baseURL");			
			screenShot = new ScreenShot(driver); 
			// open the browser 
			driver.get(baseUrl);
	 }
	
	@Test
	public void alogintest() throws InterruptedException {
		
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		String actual = driver.getTitle();
		String expected = "Dashboard";
		Assert.assertEquals(actual, expected);
		System.out.println("Titles matched");
	}
	
	@Test
	
	public void createreturntest() {
		
		WebElement ordericon = driver.findElement(By.xpath("//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-sale']/a[1]"));
		Actions act = new Actions (driver);
		act.moveToElement(ordericon).build().perform();
		driver.findElement(By.xpath("//li[@id='menu-sale']//ul//li//a[contains(text(),'Returns')]")).click();
		
		String actual = driver.getTitle();
		String expected = "Product Returns";
		Assert.assertEquals(actual, expected);
		System.out.println("Titles matched");
	
		
		returnsPagesearchPOM.sendreturnID("219");
		returnsPagesearchPOM.clickFilterBtn();
		
		assertEquals("Swetha Chintada 06",driver.findElement(By.xpath("//td[contains(text(),'Swetha Chintada 06')]")).getText(),"Unable to find order");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		returnsPagesearchPOM.sendcustomername("Swetha Chintada 06");
		returnsPagesearchPOM.clickFilterBtn();
		assertEquals("Swetha Chintada 06",driver.findElement(By.xpath("//td[contains(text(),'Swetha Chintada 06')]")).getText(),"Unable to find order");
		
		
		
		
		screenShot.captureScreenShot("Returnfiltered");
		
	}
	
	
	
	
	
	@AfterTest
	  public void afterMethod() throws InterruptedException {
		  Thread.sleep(1000);
			driver.quit();
	  }

}
