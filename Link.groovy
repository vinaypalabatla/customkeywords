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
import com.kms.katalon.core.logging.KeywordLogger
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Link {
	@Keyword
	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = DriverFactory.getWebDriver()

		//Actions actions = new Actions(driver);
		String url = "";
		HttpURLConnection huc = null;
		String HomeTitle = "Home | NAMI: National Alliance on Mental Illness";
		int respCode = 200;
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		String parentWinHandle = driver.getWindowHandle();

		driver.manage().window().maximize();
		//driver.get(homepage);
		//WebElement header= driver.findElement(By.xpath("//div[@id='siteNavigation']"));
		//Get list of web-elements with tagName  - a
		//List allLinks = header.findElements(By.tagName("a"));

		//List allLinks = driver.findElements(By.tagName("a"));


		//System.out.println("All links found on web page are: " + allLinks.size() + " links");
		
		
		java.util.List<WebElement> links = driver.findElements(By.xpath("//div[@id='siteNavigation']"));
		
		System.out.println(links.size());
		for (int i = 0; i<=links.size(); i++)
		
		{
		
		System.out.println(links.get(i).click());
		
		}
		
		}
		
	   }

		/*for (WebElement link : allLinks) {

			//print the links i.e. http://example.com or https://www.example.com
			GlobalVariable.url = link.getAttribute("href");

			//print the links text
			//GlobalVariable.url = System.out.println(link.getText());
			KeywordLogger log2 = new KeywordLogger()
			log2.logInfo(GlobalVariable.url)
			for (String k : GlobalVariable.url){
				WebUI.navigateToUrl(k)
				String current_Window = driver.getCurrentUrl();
				KeywordLogger log = new KeywordLogger()
				log.logInfo(current_Window)

			}
		}*/
	
