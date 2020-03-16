package applicationlogin

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.List

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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement
import org.openqa.selenium.By
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

public class VerifyDropdownvalues {

	@Keyword
	public static void main(TestObject Dropdown, List<String> listOfOptions) throws InterruptedException {
		def driver = DriverFactory.getWebDriver()

		WebElement dropdown = WebUiCommonHelper.findWebElement(Dropdown, 20);
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		loop: for(WebElement we:options){
			for (int i=0; i<listOfOptions.size; i++){
				if (we.getText().equals(listOfOptions[i])){
					String output = System.out.println("Matched");
					if (we.getText().equals(listOfOptions[i])){
						output = System.out.println("Matched");
						if(output.equals("Matched")){
							println("passed")
						}
						else {
							println("Failed")
						}
					}
				}
			}
		}
	}
	@Keyword
	def static void VerifyMulitpleDropdownvalues(){
		def driver = DriverFactory.getWebDriver()		
		String[] exp = ['Super Admin', 'man', 'are', 'User'];
		WebElement dropdown = driver.findElement(By.id("role"));
		Select select = new Select(dropdown);
		java.util.List<WebElement> options = select.getOptions();
		loop:for(WebElement we:options){
			start: for (int i=0; i<exp.length; i++){
				if (we.getText().equalsIgnoreCase(exp[i])){
					String output = System.out.println("Matched  :"  + exp[i]);
					if(output.equals("Matched")){
						println("passed")
						
					}
					else{
						String fail = System.out.println("failed  :" + exp[i+1] )
						/*KeywordLogger log = new KeywordLogger()
						log.logFailed(errMsg)
						 log.logFailed("failed" : + exp[i+1] )*/
						
						}
					
				}
			}
		}
	}
}
