package pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import cucumber.api.Transform;
import utility.TestFactory;

public class LinkedInHome extends TestFactory{
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement txtSearch;
	
	@FindBy(xpath="//button/span[text()='Jobs']")
	WebElement btnjob;
	
	@FindBy(xpath = "//button[contains(@aria-label,'Experience')]")
	WebElement drpExperience;
	
	@FindBy(xpath = "//button[@data-control-name='clear_filters']/span/span")
	WebElement clearFilterCount;
	
	public LinkedInHome(WebDriver driver) {
		super(driver);
		this.driver=driver;
	     PageFactory.initElements(driver, this);
	}
	
	public void searchFilter(String searchText) throws InterruptedException{
		super.waitForVisible(txtSearch);
		txtSearch.click();
		txtSearch.sendKeys(searchText);
		txtSearch.sendKeys(Keys.ENTER);
	}
	
	public void clickJobFilter() throws InterruptedException{
		super.waitForVisible(btnjob);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnjob);
		super.waitForVisible(drpExperience);
	}
	
	public void selectExperienceLevel(String text) throws InterruptedException{
		List<WebElement> exp = driver.findElements(By.xpath("//label[contains(@for,'experience')]/p/span[1]"));
		int temp=0;
		for(int i=0; i<exp.size(); i++){
			//System.out.println(exp.get(i).getText());
			if(exp.get(i).getText().equals("Mid-Senior level")){
				temp=i+1;
			}
		}
		WebElement ele = driver.findElement(By.xpath("//input[@id='experience-"+temp+"']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		ele.sendKeys(Keys.ENTER);
	}
	
	public void filterCountValidation(String ExcpectedCount) throws InterruptedException{
		super.waitForVisible(clearFilterCount);
		Assert.assertEquals(clearFilterCount.getText(), ExcpectedCount);
	}
	


}
