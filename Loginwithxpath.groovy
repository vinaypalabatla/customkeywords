package applicationlogin

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import internal.GlobalVariable
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By.*
public class Loginwithxpath {

	@Keyword
	public static void Login (String applicationURL, String exp_Email, String exp_Password)throws Exception {
		WebUI.openBrowser(applicationURL)
		WebUI.waitForPageLoad(60)
		WebUI.maximizeWindow()
		def driver = DriverFactory.getWebDriver()

		try {
			try {
				WebElement input_login = (driver).findElement(By.xpath("//a[contains(@href,'login') or href='#' or @data-toggle='modal']")).click();
			}
			catch (error){
				WebElement input_login = (driver).findElement(By.xpath("//a[contains(@class,'sign-in d-none d-lg-block') or @href='login' or href='#' or @data-toggle='modal']")).click();
			}
			finally {
				print ("Login link not present")
			}
			WebUI.waitForPageLoad(60)
			WebUI.delay(2)
			WebElement input_Email =(driver).findElement(By.xpath("//input[contains (@id,'UserName') or @placeholder='Email' or @placeholder='Username' or contains(@id,'Username') or @id='email']"));
			input_Email.clear();
			input_Email.sendKeys(exp_Email);
			try {
				WebElement input_Password =(driver).findElement(By.xpath("//input[@placeholder='Password']"));
				input_Password.clear();
				input_Password.sendKeys(exp_Password);
				WebElement input_Submit =(driver).findElement(By.xpath("//*[@value='Login' or @value = 'Sign In' or @id='submit' or contains(@class,'loginButton')]")).click()
			}
			catch(error) {
				WebElement input_Password =(driver).findElement(By.xpath("//input[@type='password']"));
				input_Password.clear();
				input_Password.sendKeys(exp_Password);
				WebElement input_Submit =(driver).findElement(By.xpath("//*[@value='Login' or @value = 'Sign In' or @id='submit' or contains(@class,'loginButton')]")).click()
			}
			finally {
				print ("Element not present")
			}
		}
		catch (error) {
			WebElement input_Email =(driver).findElement(By.xpath("//input[contains (@id,'UserName') or @placeholder='Email' or @placeholder='Username' or contains(@id,'Username') or @id='email']"));
			input_Email.clear();
			input_Email.sendKeys(exp_Email);
			try {
				WebElement input_Password =(driver).findElement(By.xpath("//input[@placeholder='Password']"));
				input_Password.clear();
				input_Password.sendKeys(exp_Password);
				WebElement input_Submit =(driver).findElement(By.xpath("//*[@value='Login' or @value = 'Sign In' or @id='submit' or contains(@class,'loginButton')]")).click()
			}
			catch(nextelement) {
				WebElement input_Password =(driver).findElement(By.xpath("//input[@type='password']"));
				input_Password.clear();
				input_Password.sendKeys(exp_Password);
				WebElement input_Submit =(driver).findElement(By.xpath("//*[@value='Login' or @value = 'Sign In' or @id='submit' or contains(@class,'loginButton')]")).click()
			}
			finally {
				print ("Element not present")
			}
		}

		finally {
			print("test case fail")
		}
	}
	// this for passing data from global
	@Keyword
	def static void loginIntoApplicationWithGlobalVariable(){
		Login(GlobalVariable.urlLogin, GlobalVariable.username, GlobalVariable.password)
	}
}