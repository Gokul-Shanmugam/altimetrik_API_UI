package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.TestFactory;

public class LogOut extends TestFactory{
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@id='nav-settings__dropdown']/button")
	public WebElement profileIcon;
	
	@FindBy(xpath = "//a[text()='Sign out']")
	public WebElement btnSignOut;
	
	public LogOut(WebDriver driver) {
		super(driver);
		this.driver=driver;
	     PageFactory.initElements(driver, this);
	}
	
}
