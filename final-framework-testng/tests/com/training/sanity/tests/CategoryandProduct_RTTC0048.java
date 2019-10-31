package com.training.sanity.tests;

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
import com.training.pom.CategoryGeneralPOM;
import com.training.pom.LoginPOM;
import com.training.pom.OrdersPagePOM;
import com.training.pom.ProductsGeneralPOM;
import com.training.pom.ProductsLinksPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CategoryandProduct_RTTC0048 {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private CategoryGeneralPOM categoryGeneralPOM;
	private ProductsGeneralPOM productsGeneralPOM;
	private ProductsLinksPOM productsLinksPOM;
	
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
		  categoryGeneralPOM = new CategoryGeneralPOM(driver);
		  productsGeneralPOM = new ProductsGeneralPOM(driver);
		  productsLinksPOM = new ProductsLinksPOM(driver);
			baseUrl = properties.getProperty("baseURL");			
			screenShot = new ScreenShot(driver); 
			// open the browser 
			driver.get(baseUrl);
	 }
	
	@Test(priority=1)
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
	
	@Test(priority=2)
	public void categorytest() throws InterruptedException {
		
		WebElement categoryicon = driver.findElement(By.xpath("//i[@class='fa fa-tags fw']"));
		Actions act = new Actions (driver);
		act.moveToElement(categoryicon).build().perform();
		
		driver.findElement(By.xpath("//a[contains(text(),'Categories')]")).click();
		
		
		driver.findElement(By.xpath("//i[@class='fa fa-plus']")).click();
		Thread.sleep(3000);
		
		categoryGeneralPOM.sendcategoryname("Ornaments");
		categoryGeneralPOM.sendmegatagTitle("Ornaments");
		categoryGeneralPOM.sendmegatagTitledesc("ornaments for ladies");
		
		driver.findElement(By.xpath("//a[contains(text(),'Data')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[contains(text(),'Design')]")).click();
		Thread.sleep(2000);
		categoryGeneralPOM.savebuttonclick();
		screenShot.captureScreenShot("Category created");
		
		String actual = driver.getTitle();
		String expected = "Categories";
		Assert.assertEquals(actual, expected);
		System.out.println("Titles matched for Categories Page");
		
		
	}
	
	@Test(priority=3)
	public void createproducttest() throws InterruptedException {
		
		WebElement categoryicon = driver.findElement(By.xpath("//i[@class='fa fa-tags fw']"));
		Actions act = new Actions (driver);
		act.moveToElement(categoryicon).build().perform();
		driver.findElement(By.xpath("//li[@id='menu-catalog']//ul//li//a[contains(text(),'Products')]")).click();
		driver.findElement(By.xpath("//i[@class='fa fa-plus']")).click();
		
		productsGeneralPOM.sendproductName("Finger Ring");
		productsGeneralPOM.sendmegatagTitle("Finger Ring for Ladies");
		
		driver.findElement(By.xpath("//a[contains(text(),'Links')]")).click();
		
		productsLinksPOM.sendcategories("Ornaments");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='tab-links']//li[1]//a[1]")).click();
		
		driver.findElement(By.xpath("//div[@class='pull-right']//button[@class='btn btn-primary']")).click();
		
		screenShot.captureScreenShot("Product created");
		
		String actual = driver.getTitle();
		String expected = "Products";
		Assert.assertEquals(actual, expected);
		System.out.println("Titles matched for Products Page");
		
		
		
	}
	
	@AfterTest
	  public void afterMethod() throws InterruptedException {
		  Thread.sleep(1000);
			driver.quit();
	  }


}
