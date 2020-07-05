package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.TestFactory;

public class SignIn extends TestFactory{
	
	WebDriver driver;
	
	@FindBy(xpath="//a[text()='Sign in']")
	public WebElement btnSignIn;
	
	@FindBy(id = "username")
	public WebElement txtEmail;
	
	@FindBy(id = "password")
	public WebElement txtPassword;
	
	@FindBy(xpath = "//button[contains(text(),'Sign in')]")
	public WebElement btnSignin;
	
	public SignIn(WebDriver driver) {
		super(driver);
		this.driver=driver;
	     PageFactory.initElements(driver, this);
	}
	
}
