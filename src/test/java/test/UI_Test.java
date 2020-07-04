package test;

import org.testng.annotations.Test;

import pom.LinkedInHome;
import pom.SignIn;
import utility.PropertyRead;

import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class UI_Test {
	
    WebDriver driver;
    SignIn si;
    LinkedInHome lh;
	PropertyRead pr = new PropertyRead();

	
     @BeforeClass
	  public void beforeClass() throws InterruptedException, IOException {
    	 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe");
    	 driver = new ChromeDriver();
    	 driver.manage().window().maximize();
    	 driver.get(pr.getPropertyData("URL"));
    	 si = new SignIn(driver);
    	 lh = new LinkedInHome(driver);
    	 si.loginValidation(pr.getPropertyData("Email"), pr.getPropertyData("Password"));
    	 si.takeScreenshotAtEndOfTest();
	  }
	
     @Test
     public void linkedInValidation() throws InterruptedException, IOException {
    	 lh.titleValidation("LinkedIn");
    	 lh.searchFilter("jobs");
    	 lh.clickJobFilter();
    	 driver.findElement(By.xpath("//button[contains(@aria-label,'Experience')]")).click();
    	 Assert.assertEquals(driver.findElement(By.xpath("//span[contains(@class,'search-vertical-filter')]")).getText(), "Jobs");
    	 Thread.sleep(5000);
    	 lh.selectExperienceLevel("Mid-Senior level");
    	 lh.filterCountValidation("1");    	 
    	 driver.findElement(By.xpath("//span[text()='All filters']")).click();
    	 driver.findElement(By.xpath("//span[text()='Full-time']")).click();
    	 driver.findElement(By.xpath("//button[contains(@class,'search-advanced')]/span[text()='Apply']")).click();
    	 lh.filterCountValidation("2");
    	 si.takeScreenshotAtEndOfTest();
    	 driver.findElement(By.xpath("//div[@id='nav-settings__dropdown']/button")).click();
    	 driver.findElement(By.xpath("//a[text()='Sign out']")).click();
    	 
     }
  

     @AfterClass
     public void afterClass() {
    	 driver.close();
     }

}
