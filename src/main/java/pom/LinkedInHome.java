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
	public WebElement txtSearch;
	
	@FindBy(xpath="//button/span[text()='Jobs']")
	public WebElement btnjob;
	
	@FindBy(xpath = "//button[contains(@aria-label,'Experience')]")
	public WebElement drpExperience;
	
	@FindBy(xpath = "//button[@data-control-name='clear_filters']/span/span")
	public WebElement clearFilterCount;
	
	@FindBy(xpath = "//span[contains(@class,'search-vertical-filter')]")
	public WebElement selectedfilter;
	
	@FindBy(xpath = "//span[text()='All filters']")
	public WebElement allFilter;
	
	@FindBy(xpath = "//span[text()='Full-time']")
	public WebElement fullTimeFilter;
	
	@FindBy(xpath = "//button[contains(@class,'search-advanced')]/span[text()='Apply']")
	public WebElement btnApplyAdvancedFilter;
	
	public LinkedInHome(WebDriver driver) {
		super(driver);
		this.driver=driver;
	     PageFactory.initElements(driver, this);
	}
	
	public void searchFilter(String searchText) {
		txtSearch.click();
		txtSearch.sendKeys(searchText);
		txtSearch.sendKeys(Keys.ENTER);
	}
	
	public void clickJobFilter() throws InterruptedException{
		super.waitForVisible(btnjob);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnjob);
		super.waitForVisible(drpExperience);
	}
	
	public void selectExperienceLevel(String text){
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
	
	public void filterCountValidation(String ExcpectedCount){
		super.waitForVisible(clearFilterCount);
		Assert.assertEquals(clearFilterCount.getText(), ExcpectedCount);
	}
	


}
