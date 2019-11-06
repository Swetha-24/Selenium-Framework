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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CategoryGeneralPOM;
import com.training.pom.CreateOrderPOM;
import com.training.pom.LoginPOM;
import com.training.pom.OrderPaymentPOM;
import com.training.pom.OrderShippingDetailsPOM;
import com.training.pom.OrderTotalsPagePOM;
import com.training.pom.OrdersPagePOM;
import com.training.pom.ProductsGeneralPOM;
import com.training.pom.ProductsLinksPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_077 {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private OrdersPagePOM orderpagePOM;
	private CreateOrderPOM createOrderPOM;
	private OrderTotalsPagePOM orderTotalsPagePOM;
	private OrderPaymentPOM orderPaymentPOM;
	private OrderShippingDetailsPOM orderShippingDetailsPOM;
	
	
	@DataProvider(name="inputs")
	public Object[][] getData() {
		return new Object[][] {
			{"Samsung", "1"},
			{"Onida", "1"},
			{"Bat", "1"},
			{"Asus", "1"}
		};}
	
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
		 orderTotalsPagePOM = new OrderTotalsPagePOM(driver);
		 orderPaymentPOM = new OrderPaymentPOM(driver);
		 orderShippingDetailsPOM = new OrderShippingDetailsPOM(driver);
			baseUrl = properties.getProperty("baseURL");			
			screenShot = new ScreenShot(driver); 
			// open the browser 
			driver.get(baseUrl);
	 }
	
	@BeforeMethod
	public void alogintest() {

		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		
		
		String actual = driver.getTitle();
		String expected = "Dashboard";
		Assert.assertEquals(actual, expected);
		System.out.println("Titles matched for main page");
		
		WebElement ordericon = driver.findElement(By.xpath("//body/div[@id='container']/nav[@id='column-left']/ul[@id='menu']/li[@id='menu-sale']/a[1]"));
		Actions act = new Actions (driver);
		act.moveToElement(ordericon).build().perform();
		driver.findElement(By.xpath("//li[@id='menu-sale']//ul//li//a[contains(text(),'Orders')]")).click();
		
		orderpagePOM.sendorderID("316");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		orderpagePOM.clickFilterBtn();
		assertEquals("316",driver.findElement(By.xpath("//td[contains(text(),'316')]")).getText(),"Unable to find order");
		
		screenShot.captureScreenShot("OrderFiltered");
	}
	
	
		
	@Test(dataProvider="inputs")
	public void producttest(String inputproduct, String productquantity) throws InterruptedException {		
		
		driver.findElement(By.xpath("//i[@class='fa fa-pencil']")).click();
		Thread.sleep(2000);
		driver.navigate().refresh();
		
		createOrderPOM.continueclk();
		Thread.sleep(2000);
		try {
			if(driver.findElement(By.xpath("//div[@class='alert alert-danger']/button")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[@class='alert alert-danger']/button")).click();
			}
		}
		catch(Exception e)
		{
			
		}
		createOrderPOM.continueclk();
		driver.findElement(By.xpath("//i[@class='fa fa-minus-circle']")).click();
		
		createOrderPOM.selectinputproduct(inputproduct);
		driver.findElement(By.xpath("//body//div[@class='tab-content']//div[@class='tab-content']//li[1]//a[1]")).click();
		createOrderPOM.sendquantity(productquantity);
		createOrderPOM.clickaddproduct();
		createOrderPOM.continueproductBtn();
		orderPaymentPOM.continueclk();
		orderShippingDetailsPOM.continueclk();
		
		orderTotalsPagePOM.selectshippingmethod("Free Shipping - Rs.0");
		orderTotalsPagePOM.saveclk();
		driver.findElement(By.xpath("//body/div[@id='container']/header[@id='header']/ul[@class='nav pull-right']/li[4]/a[1]")).click();
		String actual = driver.getTitle();
		String expected = "Orders";
		Assert.assertEquals(actual, expected);
		System.out.println("Titles matched for Orders");
		screenShot.captureScreenShot("Product changed");
		
	}

	
	@AfterMethod
	  public void afterMethod() throws InterruptedException {
		  Thread.sleep(1000);
			driver.quit();
	  }
}
