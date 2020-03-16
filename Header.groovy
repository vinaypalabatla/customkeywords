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
import internal.GlobalVariable
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.remote.RemoteWebElement
import com.kms.katalon.core.util.KeywordUtil
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys

public class Header {


	@Keyword

	public static void main(String[] args) throws InterruptedException{
		String New_Window = null;
		//WebUI.openBrowser(applicationURL)
		//WebUI.waitForPageLoad(60)
		//WebUI.maximizeWindow()
		def driver = DriverFactory.getWebDriver()
		WebElement header= driver.findElement(By.xpath("//div[contains(@id,'siteNavigation')]"));  // Get header element which contains all footer links
		System.out.println(header.findElements(By.tagName("a")).size()) ;
		List<WebElement> headlinks = header.findElements(By.tagName("a"));
		int i = header.findElements(By.tagName("a")).size(); //Get number of links

		for(int j = 0;j<i;j++){    //create loop based upon number of links to traverse all links
			header= driver.findElement(By.xpath("//div[contains(@id,'siteNavigation')]"));   // We are re-creating "header" webelement as DOM changes after navigate.back()

			header.findElements(By.tagName("a")).get(j).getText();
			//footer.findElements(By.tagName("a")).get(j).click();

			String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
			String parentWinHandle = driver.getWindowHandle();
			header.findElements(By.tagName("a")).get(j).sendKeys(selectLinkOpeninNewTab);
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
				//System.out.println("Closing the new window...");
				//driver.close();
				System.out.println(driver.getTitle());
				if(driver.getTitle().contains("404")) {
					System.out.println("404 Found");
				}
				//System.out.println("Closing the new window...");
				//driver.close();
				//driver.switchTo().window(baseUrl);

				//}
				//driver.navigate().back();

				Thread.sleep(4000);
			}
		}

	}
}

