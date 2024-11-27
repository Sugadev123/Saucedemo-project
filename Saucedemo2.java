package TestNg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilities.Browserlaunch;

public class Saucedemo2 {
	
		WebDriver driver;
		ExtentSparkReporter reporter;
		ExtentReports extent;
		ExtentTest test;
		
		
		@BeforeClass
		public void launchbrowser() {
			reporter = new ExtentSparkReporter("C:\\Users\\PC\\eclipse-workspace\\Seleinumdemo1\\reports\\report2.html");
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			
			driver = Browserlaunch.launch("Chrome");
			driver.manage().window().maximize();
			driver.get("https://www.saucedemo.com/");
		}
		@Test(priority=1)
		public void login() {
			test=extent.createTest("Login the page");
			
			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			driver.findElement(By.id("login-button")).click();
			String title = driver.getTitle();
		   Assert.assertEquals(title, "Swag Labs");
		}
		@Test(priority=2)
		public void sortdemo() {
			test=extent.createTest("List of products");
			
			WebElement sortByDropDown= driver.findElement(By.className("product_sort_container"));
		    Select sort =new Select(sortByDropDown);
	       List <WebElement> Options =sort.getOptions();
			int num1= Options.size();
			Assert.assertEquals(num1,4);
			for(WebElement items: Options) {
				System.out.println("number of options:"+items.getText());
			}
		}
		
		
		
		@Test(priority=3)
		public void items() {
			test=extent.createTest("Number of product");
			
			List<WebElement> products = driver.findElements(By.className("inventory_item_label"));
			int num2= products.size();
			Assert.assertEquals(num2,6);
			
		}
		
		@Test(priority=4)
		public void cart() {
			test=extent.createTest("Select the product");
			
			driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();	
			driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
			driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
		String num3=driver.findElement(By.className("shopping_cart_badge")).getText();
		Assert.assertEquals(num3,"3");
			
		}
		@Test(priority=5)
		public void badge() {
			test=extent.createTest("Shopping cart");
			
			driver.findElement(By.className("shopping_cart_badge")).click();
			String tag= driver.getCurrentUrl();
		Assert.assertEquals(tag,"https://www.saucedemo.com/cart.html");
			
		}
		
		@Test(priority=6)
		public void checkout() {
			test=extent.createTest("Chechout the page");
			
			driver.findElement(By.xpath("//button[@id='checkout']")).click();
			String tag1= driver.getCurrentUrl();
			Assert.assertEquals(tag1,"https://www.saucedemo.com/checkout-step-one.html");
			
		}
		
		@Test(priority=7)
		public void details() {
			test=extent.createTest("Enter the detail");
			
			driver.findElement(By.id("first-name")).sendKeys("sugadev");
			driver.findElement(By.id("last-name")).sendKeys("mani");
			driver.findElement(By.id("postal-code")).sendKeys("0987");
			driver.findElement(By.id("continue")).click();
			String tag3= driver.getCurrentUrl();
			Assert.assertEquals(tag3,"https://www.saucedemo.com/checkout-step-two.html");
		}
		
		@Test(priority=8)
		public void competion() {
			test=extent.createTest("Click the finish");
			
			driver.findElement(By.id("finish")).click();
			String tag4=driver.findElement(By.className("complete-header")).getText();
			Assert.assertEquals(tag4, "Thank you for your order!");
			
	}
		@Test(priority=9)
		public void returnhome() {
			test=extent.createTest("Return to home page");
			
			driver.findElement(By.id("back-to-products")).click();
			String tag4= driver.getCurrentUrl();
			Assert.assertEquals(tag4,"https://www.saucedemo.com/inventory.html");
		}
		@Test(priority=10)
		public void logout() {
			test=extent.createTest("Logout the page");
			
			driver.findElement(By.id("react-burger-menu-btn")).click();
			driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
			String title2= driver.getTitle();
			   Assert.assertEquals(title2, "Swag Labs");
			
		}
		@AfterMethod
		public void captureTestStatus(ITestResult result) {
			if(result.getStatus() == ITestResult.SUCCESS) {
				test.log(Status.PASS,"test case passed");
			}else if(result.getStatus() == ITestResult.FAILURE) {
				test.log(Status.FAIL, "test case failed");
			}else if(result.getStatus() == ITestResult.SKIP) {
				test.log(Status.SKIP, "test case skipped");
			}
			
		}
		@AfterClass
		public void closure() {
			driver.close();
			extent.flush();
		}
	}