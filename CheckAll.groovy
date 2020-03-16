package reusableComponents

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import java.util.List;

import org.eclipse.persistence.internal.oxm.record.json.JSONParser.object_return
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait
import com.kms.katalon.core.webui.driver.DriverFactory
import org.testng.Assert

public class CheckAll {

	@Keyword
	public void checkbox() throws Exception {
		//FirefoxDriver fd= new FirefoxDriver();
		//fd.manage().window().maximize();
		//fd.get(“http://www.flipkart.com/laptops/lenovo~brand/pr?sid=6bo,b5g&otracker=hp_nmenu_sub_electronics_0_Lenovo&#8221;);
		//Thread.sleep(10000);
		def driver = DriverFactory.getWebDriver()
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='Checkbox']"));
		//List<WebElement> checkBoxes = WebUiCommonHelper.findWebElements(objectTo, 30)
		System.out.println(checkBoxes.size());
		for(int i=0; i<checkBoxes.size(); i++){
			System.out.println(checkBoxes.get(i).getText());
			checkBoxes.get(i).click();
		}
	}

	@Keyword
	public static void Select_The_CheckBox_from_List(List<String> listOfOptions) throws InterruptedException{
		def driver = DriverFactory.getWebDriver()
		List<String> j = listOfOptions;
		for(String i : j){
			String Xpath = "//*[@type='checkbox']["+i+"]";
			WebElement checkBoxes = (driver).findElement(By.xpath(Xpath)).click();
			i++;
		}
	}

	@Keyword
	public static void Select_The_CheckBox_from_List_ByValue(List<String> listOfOptions) throws InterruptedException{
		def driver = DriverFactory.getWebDriver()
		List<String> j = listOfOptions;
		for(String i : j){
			String Xpath = "//*[@type='checkbox' and contains(@value, '"+i+"')]";
			WebElement checkBoxes = (driver).findElement(By.xpath(Xpath)).click();
			i++;
		}
	}

}
