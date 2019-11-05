package com.training.sanity.tests;

// SINCE WE CANNOT ACCESS SITE'S DATABASE, DID NOT WRITE CODE FOR DB

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

public class RTTC_076 {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private OrdersPagePOM orderpagePOM;
	private CreateOrderPOM createOrderPOM;
	private OrderPaymentPOM orderPaymentPOM;
	private OrderShippingDetailsPOM orderShippingDetailsPOM;
	private OrderTotalsPagePOM orderTotalsPagePOM;
	
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
		  orderpagePOM = new OrdersPagePOM(driver) ;	
		  orderPaymentPOM = new OrderPaymentPOM(driver);
		  orderShippingDetailsPOM = new OrderShippingDetailsPOM(driver);
		  orderTotalsPagePOM = new OrderTotalsPagePOM(driver);
		  createOrderPOM = new CreateOrderPOM(driver);
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
		System.out.println("Titles matched for main page");
	}
	
	@Test
	
	public void filtercreatedordertest() {
		
		WebElement ordericon = driver.findElement(By.xpath("//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-sale']/a[1]"));
		Actions act = new Actions (driver);
		act.moveToElement(ordericon).build().perform();
		
		driver.findElement(By.xpath("//li[@id='menu-sale']//ul//li//a[contains(text(),'Orders')]")).click();
		
		String actual = driver.getTitle();
		String expected = "Orders";
		Assert.assertEquals(actual, expected);
		System.out.println("Titles matched for Orders page");
		
		orderpagePOM.sendorderID("296");
		
		orderpagePOM.clickFilterBtn();
		assertEquals("296",driver.findElement(By.xpath("//td[contains(text(),'296')]")).getText(),"Unable to find order");
		System.out.println("Order found with order no 296");
		
	}
	
	@Test
	
	public void modifyordertest() throws InterruptedException {
		
		driver.findElement(By.xpath("//i[@class='fa fa-pencil']")).click();
		
		Thread.sleep(3000);
		
		driver.navigate().refresh();
		
		Thread.sleep(3000);
		
		createOrderPOM.continueclk();
		
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//i[@class='fa fa-minus-circle']")).click();
		
		
		
		createOrderPOM.selectinputproduct("Samsung");
		driver.findElement(By.xpath("//div[@class='tab-content']//li[4]//a[1]")).click();
		
		Thread.sleep(3000);
		
		createOrderPOM.clickaddproduct();
		createOrderPOM.continueproductBtn();
		
		orderPaymentPOM.continueclk();
		
		orderShippingDetailsPOM.continueclk();
		
		orderTotalsPagePOM.selectshippingmethod("Free Shipping - Rs.0");
		orderTotalsPagePOM.saveclk();
		
		screenShot.captureScreenShot("Order Modified");
		
				
		
		
	}
	@AfterMethod
	  public void afterMethod() throws InterruptedException {
		  Thread.sleep(1000);
			driver.quit();
	  }

}
