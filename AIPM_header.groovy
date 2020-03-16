package applicationlogin

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.remote.RemoteWebElement
import com.kms.katalon.core.util.KeywordUtil
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import internal.GlobalVariable
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By.*
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.openqa.selenium.Keys
import org.openqa.selenium.interactions.Actions;


public class AIPM_header {
	@Keyword
	public static void main( String applicationURL) throws InterruptedException{

		WebUI.openBrowser(applicationURL)
		WebUI.waitForPageLoad(60)
		WebUI.maximizeWindow()
		def driver = DriverFactory.getWebDriver()
		Actions actions = new Actions(driver);
		WebElement header= driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']"));  // Get Footer element which contains all footer links
		WebElement  subheader= driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li/ul"));  // Get Footer element which contains all footer links
		int count =driver.findElements(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li")).size();
		System.out.println(count);
		System.out.println(header.findElements(By.tagName("a")).size()) ;

		loop: for (int i=1;i<=count;i++){
			actions.moveToElement(driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li["+i+"]"))).build().perform()
			List<WebElement>list = driver.findElements(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li["+i+"]/ul/li"));
			int count1 = list.size();
			println(count1)
			if (driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li/ul")).isDisplayed()){
				for (int j=1;j<=count1;j++){
					WebElement child =driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li["+i+"]/ul/li["+j+"]"));
					String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
					String parentWinHandle = driver.getWindowHandle();
					child.sendKeys(selectLinkOpeninNewTab);
					Thread.sleep(3000);
					ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());

					//driver.switchTo().window(selectLinkOpeninNewTab);
					Set<String> winHandles = driver.getWindowHandles();
					// Loop through all handles
					for(String handle: winHandles){
						if(!handle.equals(parentWinHandle)){
							driver.switchTo().window(handle);
							Thread.sleep(1000);

							String current_Window = driver.getCurrentUrl();
							KeywordLogger log = new KeywordLogger()
							log.logInfo(current_Window)
							driver.switchTo().window(tabs2.get(1));
							driver.close();
							driver.switchTo().window(tabs2.get(0));
						}
						Thread.sleep(4000);
					}
				}
			}
			else{
				println("not found")
			}



		}

	}
}

