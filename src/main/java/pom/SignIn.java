package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.TestFactory;

public class SignIn extends TestFactory{
	
	WebDriver driver;
	
	@FindBy(xpath="//a[text()='Sign in']")
	WebElement btnSignIn;
	
	@FindBy(id = "username")
	WebElement txtEmail;
	
	@FindBy(id = "password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//button[contains(text(),'Sign in')]")
	WebElement btnSignin;
	
	public SignIn(WebDriver driver) {
		super(driver);
		this.driver=driver;
	     PageFactory.initElements(driver, this);
	}
	
	public void loginValidation(String email, String password) throws InterruptedException{
		btnSignIn.click();
		super.waitForVisible(txtEmail);
		txtEmail.click();
		txtEmail.sendKeys(email);
		txtPassword.sendKeys(password);
		btnSignin.click();
	}
	
	

}
