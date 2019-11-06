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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CreateOrderPOM;
import com.training.pom.LoginPOM;
import com.training.pom.OrderPaymentPOM;
import com.training.pom.OrderShippingDetailsPOM;
import com.training.pom.OrderTotalsPagePOM;
import com.training.pom.OrdersPagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class Filterorder_RTTC0016 {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private OrdersPagePOM orderpagePOM;
	
	
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
		  orderpagePOM = new OrdersPagePOM(driver);
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
		
		public void filtertest() {
		
		WebElement ordericon = driver.findElement(By.xpath("//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-sale']/a[1]"));
		Actions act = new Actions (driver);
		act.moveToElement(ordericon).build().perform();
		driver.findElement(By.xpath("//li[@id='menu-sale']//ul//li//a[contains(text(),'Orders')]")).click();
		
		orderpagePOM.sendorderID("296");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		orderpagePOM.clickFilterBtn();
		assertEquals("296",driver.findElement(By.xpath("//td[contains(text(),'296')]")).getText(),"Unable to find order");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		orderpagePOM.sendcustomername("Swetha Chintada 06");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		orderpagePOM.clickFilterBtn();
		assertEquals("Swetha Chintada 06",driver.findElement(By.xpath("//td[contains(text(),'Swetha Chintada 06')]")).getText(),"Unable to find order");
		
		
		
		screenShot.captureScreenShot("OrderFiltered");
		
			
		}
		
		
		@AfterTest
		  public void afterMethod() throws InterruptedException {
			  Thread.sleep(1000);
				driver.quit();
		  }

}
