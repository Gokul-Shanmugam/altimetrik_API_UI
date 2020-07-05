package test;

import org.testng.annotations.Test;

import pom.LinkedInHome;
import pom.LogOut;
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
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class UI_Test {
	
    WebDriver driver;
    SignIn oSignIn;
    LinkedInHome oLinkedInHome;
    LogOut oLogout;
	PropertyRead pr = new PropertyRead();

	
     @BeforeClass
	  public void beforeClass() throws InterruptedException, IOException {
    	 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\chromedriver.exe");
    	 driver = new ChromeDriver();
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	 driver.get(pr.getPropertyData("URL"));
    	 oSignIn = new SignIn(driver);
    	 oLinkedInHome = new LinkedInHome(driver);
    	 oLogout = new LogOut(driver);
    	 Reporter.log("Linked In page Loaded successfully");
    	 
	  }
     
      @Test(priority = 0)
      public void login() throws IOException, InterruptedException {
    	  PageFactory.initElements(driver, this.oSignIn);
    	  oSignIn.btnSignIn.click();
    	  oSignIn.oFluentwait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
    	  Thread.sleep(5000L);
    	  oSignIn.txtEmail.click();
    	  oSignIn.txtEmail.sendKeys(pr.getPropertyData("Email"));
    	  oSignIn.txtPassword.sendKeys(pr.getPropertyData("Password"));
    	  oSignIn.btnSignin.click();
    	  oSignIn.takeScreenshotAtEndOfTest();
    	  Reporter.log("Logged In successfully");
      }
	
     @Test(priority = 1)
     public void linkedInValidation() throws IOException {
    	 PageFactory.initElements(driver, this.oLinkedInHome);
    	 PageFactory.initElements(driver, this.oLogout);
    	 
    	 //title validation
    	 oLinkedInHome.titleComparison("LinkedIn");
    	 Reporter.log("Title validation is successful");
    	 
    	 //Search for Jobs
    	 oLinkedInHome.oFluentwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search']")));
    	 oLinkedInHome.searchFilter("Jobs");
    	 Reporter.log("Search Filter is applied as Jobs");
    	 
    	 //Selecting the Experience As Mid- Senior Level
    	 oLinkedInHome.oFluentwait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@aria-label,'Experience')]")));
    	 oLinkedInHome.btnjob.click();
    	 oLinkedInHome.drpExperience.click();
    	 oLinkedInHome.selectExperienceLevel("Mid-Senior level");
    	 Reporter.log("Experience level is selected Mid-Senior level");
    	 
    	 //verify the Filter is Jobs
    	 oLinkedInHome.oFluentwait.until(ExpectedConditions.textToBePresentInElement(oLinkedInHome.selectedfilter, "Jobs"));
    	 oLinkedInHome.webElementTextComparision(oLinkedInHome.selectedfilter, "Jobs");
    	 Reporter.log("Filter is validated as Jobs");
    	 
    	 //verify the applied filter as 1
    	 oLinkedInHome.filterCountValidation("1");
    	 Reporter.log("Verifying that one applied filter");
    	 
    	 //click on ALL filter and select Job as Full Time
    	 oLinkedInHome.waitForVisible( oLinkedInHome.allFilter);
    	 oLinkedInHome.allFilter.click();
    	 oLinkedInHome.fullTimeFilter.click();
    	 oLinkedInHome.btnApplyAdvancedFilter.click();
    	 Reporter.log("clicked on ALL filter and select Job as Full Time");

    	 ////verify the applied filter as 2
    	 oLinkedInHome.filterCountValidation("2");
    	 oLinkedInHome.takeScreenshotAtEndOfTest();
    	 Reporter.log("Verifying that there are two applied filter");
    	 
    	 //logout
    	 oLogout.profileIcon.click();
    	 oLogout.btnSignOut.click();
    	 Reporter.log("Logged out successfully");
    	 
     }
  

     @AfterClass
     public void afterClass() {
    	 driver.close();
     }

}
