package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;


public class TestFactory {
	
	private WebDriver driver;
	private WebDriverWait oWebDriverWait;
	
	
	public TestFactory(WebDriver driver){
		this.driver = driver;
		this.oWebDriverWait = new WebDriverWait(driver, 30);;
	}
	
	
	public String getJSONValue(String res,String jPath){
		JsonPath js = new JsonPath(res);
		return js.getString(jPath);
	}
	
	public void waitForVisible(WebElement webElement) throws InterruptedException{
		Thread.sleep(5000L);
		oWebDriverWait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public void titleValidation(String expectedTitle){
		Assert.assertEquals(driver.getTitle(), expectedTitle);
	}
	
	public  void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

}
