package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

import com.training.dataproviders.LoginDataProviders;
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
import com.training.readexcel.ApachePOIExcelRead;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RTTC_078 {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private OrdersPagePOM orderpagePOM;
	private CategoryGeneralPOM categoryGeneralPOM;
	private ProductsGeneralPOM productsGeneralPOM;
	private ProductsLinksPOM productsLinksPOM;	
	
	//
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
		  categoryGeneralPOM = new CategoryGeneralPOM(driver);
		  productsGeneralPOM = new ProductsGeneralPOM(driver);
		  productsLinksPOM = new ProductsLinksPOM(driver);
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
		
		WebElement catelogicon = driver.findElement(By.xpath("//i[@class='fa fa-tags fw']"));
		Actions act = new Actions (driver);
		act.moveToElement(catelogicon).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'Categories')]")).click();
		
		
		driver.findElement(By.xpath("//i[@class='fa fa-plus']")).click();
	}
	
	
	@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void loginDBTest(String categoryName, String megatagTitlec, String megatagTitledesc, String productName, String megatagTitle, String categories ) throws InterruptedException {
		categoryGeneralPOM.sendcategoryname(categoryName);
		categoryGeneralPOM.sendmegatagTitle(megatagTitlec);
		categoryGeneralPOM.sendmegatagTitledesc(megatagTitledesc);
		categoryGeneralPOM.savebuttonclick();
		
		String actual = driver.getTitle();
		String expected = "Categories";
		Assert.assertEquals(actual, expected);
		System.out.println("Titles matched for Categories Page");
		
		WebElement productsicon = driver.findElement(By.xpath("//i[@class='fa fa-tags fw']"));
		Actions act = new Actions (driver);
		act.moveToElement(productsicon).build().perform();
		driver.findElement(By.xpath("//li[@class='active open']//a[contains(text(),'Products')]")).click();
		driver.findElement(By.xpath("//i[@class='fa fa-plus']")).click();
		
		productsGeneralPOM.sendproductName(productName);
		productsGeneralPOM.sendmegatagTitle(megatagTitle);
		driver.findElement(By.xpath("//a[contains(text(),'Links')]")).click();
		
		productsLinksPOM.sendcategories(categories);
		driver.findElement(By.xpath("//div[@id='tab-links']//li[1]//a[1]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Data')]")).click();
		driver.findElement(By.xpath("//input[@id='input-model']")).sendKeys("Samsung");
		driver.findElement(By.xpath("//i[@class='fa fa-save']")).click();	
		driver.findElement(By.xpath("//span[@class='hidden-xs hidden-sm hidden-md']")).click();
		String actual1 = driver.getTitle();
		String expected1 = "Products";
		Assert.assertEquals(actual1, expected1);
		System.out.println("Titles matched for Products Page");
		
		screenShot.captureScreenShot("New Product created");
		
		
	}
	
	@AfterMethod
	  public void afterMethod() throws InterruptedException {
		  Thread.sleep(1000);
			driver.quit();
	  }

}
