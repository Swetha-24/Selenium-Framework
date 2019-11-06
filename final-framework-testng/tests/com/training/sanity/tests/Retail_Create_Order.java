package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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
import com.training.pom.OrderPaymentPOM;
import com.training.pom.OrderShippingDetailsPOM;
import com.training.pom.OrderTotalsPagePOM;
import com.training.pom.OrdersPagePOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


public class Retail_Create_Order {
	
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
		  orderpagePOM = new OrdersPagePOM(driver);
		  createOrderPOM = new CreateOrderPOM(driver);
		  orderPaymentPOM = new OrderPaymentPOM(driver);
		  orderTotalsPagePOM = new OrderTotalsPagePOM(driver);
		  orderShippingDetailsPOM = new OrderShippingDetailsPOM(driver);
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
		
		WebElement ordericon = driver.findElement(By.xpath("//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-sale']/a[1]"));
		Actions act = new Actions (driver);
		act.moveToElement(ordericon).build().perform();
		driver.findElement(By.xpath("//li[@id='menu-sale']//ul//li//a[contains(text(),'Orders')]")).click();
			
		}
	
	@Test
	public void createordertest() throws InterruptedException {
		
		driver.findElement(By.xpath("//i[@class='fa fa-plus']")).click();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		createOrderPOM.sendfirstname("Swetha");
		createOrderPOM.sendlastname("Chintada 0183C9");
		createOrderPOM.sendemail("revasharma@gmail.com");
		createOrderPOM.sendtelephone("8768767");
		
		createOrderPOM.continueclk();
		
		createOrderPOM.selectinputproduct("Samsung");
		driver.findElement(By.xpath("//body//div[@class='tab-content']//div[@class='tab-content']//li[1]")).click();
		createOrderPOM.sendquantity("2");
		createOrderPOM.clickaddproduct();
		createOrderPOM.continueproductBtn();
		
		orderPaymentPOM.sendfirstname("Swetha");
		orderPaymentPOM.sendlastname("Chintada");
		orderPaymentPOM.sendaddress1("bangalore");
		orderPaymentPOM.sendcity("bangalore");
		orderPaymentPOM.selectcountry("India");
		orderPaymentPOM.selectzone("Karnataka");
		orderPaymentPOM.continueclk();
		
		orderShippingDetailsPOM.sendfirstname("Swetha");
		orderShippingDetailsPOM.sendlastname("Chintada");
		orderShippingDetailsPOM.sendaddress1("bangalore");
		orderShippingDetailsPOM.sendcity("bangalore");
		orderShippingDetailsPOM.selectcountry("India");
		orderShippingDetailsPOM.selectzone("Karnataka");
		orderShippingDetailsPOM.continueclk();
		
		orderTotalsPagePOM.selectshippingmethod("Free Shipping - Rs.0");
		orderTotalsPagePOM.selectpaymentmethod("Cash On Delivery");
		orderTotalsPagePOM.saveclk();
		
		screenShot.captureScreenShot("OrderCreated");
	}
	
		
	@AfterTest
	  public void afterMethod() throws InterruptedException {
		  Thread.sleep(1000);
			driver.quit();
	  }

}
