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
import com.training.pom.LoginPOM;
import com.training.pom.OrdersPagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class FilterOrder_RTTC0047_Medium {
	
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
	
	public void filtertest() throws InterruptedException {
		
		WebElement ordericon = driver.findElement(By.xpath("//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-sale']/a[1]"));
		Actions act = new Actions (driver);
		act.moveToElement(ordericon).build().perform();
		driver.findElement(By.xpath("//li[@id='menu-sale']//ul//li//a[contains(text(),'Orders')]")).click();
		
		
		//filter by orderID
		orderpagePOM.sendorderID("298");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		orderpagePOM.clickFilterBtn();
		assertEquals("298",driver.findElement(By.xpath("//td[contains(text(),'298')]")).getText(),"Unable to find order");		
		System.out.println("order filtered by order ID");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		screenShot.captureScreenShot("OrderFiltered bby orderID");
		
		
		
		//filter by customer name		
		orderpagePOM.sendcustomername("Swetha Ch 123");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		orderpagePOM.clickFilterBtn();
		assertEquals("Swetha Ch 123",driver.findElement(By.xpath("//td[contains(text(),'Swetha Ch 123')]")).getText(),"Unable to find order");		
		System.out.println("order filtered by customer name");
		screenShot.captureScreenShot("OrderFiltered by Customer");
		
		
		//filter by order status
		orderpagePOM.selectorderstatus("Pending");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		orderpagePOM.clickFilterBtn();
		assertEquals("Swetha Ch 123",driver.findElement(By.xpath("//td[contains(text(),'Swetha Ch 123')]")).getText(),"Unable to find order");	
		System.out.println("order filtered by order status");
		screenShot.captureScreenShot("OrderFiltered by order status");
	
	
	
	//filter order by date added
		orderpagePOM.selectdateadded("29/10/2019");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//span[1]//button[1]//i[1]")).click();
		driver.findElement(By.xpath("//div[@class='bootstrap-datetimepicker-widget dropdown-menu picker-open bottom pull-right']//td[@class='day'][contains(text(),'29')]")).click();
		orderpagePOM.clickFilterBtn();
		assertEquals("Swetha Ch 123",driver.findElement(By.xpath("//td[contains(text(),'Swetha Ch 123')]")).getText(),"Unable to find order");	
		System.out.println("order filtered by date added");
		screenShot.captureScreenShot("OrderFiltered by date added");
		
	//filter order by date modified
		orderpagePOM.selectdatemodified("29/10/2019");
		driver.findElement(By.xpath("//div[@class='panel-body']//div[2]//div[1]//span[1]//button[1]//i[1]")).click();
		driver.findElement(By.xpath("//div[@class='bootstrap-datetimepicker-widget dropdown-menu picker-open bottom pull-right']//td[@class='day'][contains(text(),'29')]")).click();
		orderpagePOM.clickFilterBtn();
		assertEquals("Swetha Ch 123",driver.findElement(By.xpath("//td[contains(text(),'Swetha Ch 123')]")).getText(),"Unable to find order");	
		System.out.println("order filtered by date modified");
		screenShot.captureScreenShot("OrderFiltered by date modified");
	
	}
	
	@AfterTest
	  public void afterMethod() throws InterruptedException {
		  Thread.sleep(1000);
			driver.quit();
	  }

}
