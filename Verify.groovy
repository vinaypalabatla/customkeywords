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
import internal.GlobalVariable
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By.*
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.openqa.selenium.Keys
import applicationlogin.WriteExcel
import org.openqa.selenium.interactions.Actions;
import org.eclipse.persistence.internal.oxm.record.json.JSONParser.object_return
import org.testng.annotations.Test;
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.util.KeywordUtil

public class Verify {
	@Keyword
	public static void main(String[] args) throws InterruptedException {
		def driver = DriverFactory.getWebDriver()

		//Actions actions = new Actions(driver);
		String url = "";
		HttpURLConnection huc = null;
		String HomeTitle = "Home | NAMI: National Alliance on Mental Illness";
		int respCode = 200;
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		String parentWinHandle = driver.getWindowHandle();

		driver.manage().window().maximize();
		//driver.get(homepage);
		WebElement header= driver.findElement(By.xpath("//*[@class='main']"));
		//Get list of web-elements with tagName  - a
		List<WebElement> allLinks = header.findElements(By.tagName("a"));
		String[] linkTexts = new String[allLinks.size()];
		println(linkTexts)
		int	i = 0;

		//extract the link texts of each link element
		for (WebElement e : allLinks) {
			linkTexts[i] = e.getText();
			i++;
		}

		//test each link
		for (String t : linkTexts) {
			driver.findElement(By.linkText(t)).click();
			String current_Window = driver.getCurrentUrl();
			KeywordLogger log = new KeywordLogger()
			log.logInfo(current_Window)
			if (driver.getTitle().equals(HomeTitle)) {
				System.out.println("\"" + t + "\""
						+ " is under construction.");
			} else {
				System.out.println("\"" + t + "\""
						+ " is working.");
			}

			driver.navigate().back();
		}
		driver.quit();
	}
}
