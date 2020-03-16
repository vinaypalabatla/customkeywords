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


public class Headerlinks {


	@Keyword
	public static void main(String[] args, String applicationURL) throws InterruptedException{

		WebUI.openBrowser(applicationURL)
		WebUI.waitForPageLoad(60)
		WebUI.maximizeWindow()

		def driver = DriverFactory.getWebDriver()
		Actions actions = new Actions(driver);

		String current_URL = driver.getCurrentUrl();
		String baseUrl = current_URL;
		WebElement header= driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']"));  // Get Footer element which contains all footer links
		int count =driver.findElements(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li")).size();
		System.out.println(count);
		int count1 =driver.findElements(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li/ul/li")).size();
		System.out.println(count1);
		WebElement subheader= driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li/ul"));  // Get Footer element which contains all footer links
		System.out.println(header.findElements(By.tagName("a")).size()) ;
		List<WebElement> headlinks = header.findElements(By.tagName("a"));
		println("Execute For Loop");
		//	int d=1;
		//while (d<=count){
		loop: for (int d=1;d<=count;d++){
			WebElement Mousehover= driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li["+d+"]"));  // Get Footer element which contains all footer links
			actions.moveToElement(Mousehover).build().perform()
			int k= subheader.findElements(By.tagName("a")).size(); //Get number of sub links
			List<WebElement>list = driver.findElements(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li["+d+"]/ul/li"));
			int count2 = list.size();
			println(count2)

			if (subheader.displayed)
			{
				println("if is started")
				int i = subheader.findElements(By.tagName("a")).size(); //Get number of links

				for(int j = 0;j<i;j++){ //create loop based upon number of links to traverse all links
					println("first For loop is started")
					for(int e=1; e<=count2; e++){
						println("second For loop is Started")
						actions.moveToElement(driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li["+d+"]"))).build().perform()
						subheader= driver.findElement(By.xpath("//div[@class='col-md-12 no-float  ']/ul/li["+d+"]/ul/li["+e+"]"));   // We are re-creating "footer" webelement as DOM changes after navigate.back()
						subheader.findElements(By.tagName("a")).get(j).getText();
						//header.findElements(By.tagName("a")).get(j).click();
						String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
						String parentWinHandle = driver.getWindowHandle();
						subheader.findElements(By.tagName("a")).get(j).sendKeys(selectLinkOpeninNewTab);
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

			}
			else

			{
				println("else is started")
				Mousehover.click();
				String current_Window = driver.getCurrentUrl();
				KeywordLogger log = new KeywordLogger()
				log.logInfo(current_Window)
			}
			d++;
			break loop;
		}

	}

}



