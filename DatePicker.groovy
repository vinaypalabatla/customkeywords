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
import java.util.Calendar;

import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.Test;
import internal.GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory

public class DatePicker {

	@Keyword

	public void testDAtePicker() throws Exception{

		//DAte and Time to be set in textbox
		def driver = DriverFactory.getWebDriver()

		String dateTime ="12/28/2019";

		//button to open calendar

		WebElement selectDate = driver.findElement(By.xpath("//input[contains(@id,'txtAppDate1')]"));

		selectDate.click();

		//button to move next in calendar

		//	WebElement nextLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-next')]"));

		//button to click in center of calendar header

		WebElement midLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-fast')]"));

		//button to move previous month in calendar

		//WebElement previousLink = driver.findElement(By.xpath("//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-prev')]"));

		//Split the date time to get only the date part
		def date_dd_MM_yyyy = [] as String[]


		date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");

		//get the year difference between current year and year to set in calander

		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);

		midLink.click();

		if(yearDiff!=0){

			//if you have to move next year

			if(yearDiff>0){

				for(int i=0;i< yearDiff;i++){

					System.out.println("Year Diff->"+i);

					nextLink.click();

				}

			}

			//if you have to move previous year

			else if(yearDiff<0){

				for(int i=0;i< (yearDiff*(-1));i++){

					System.out.println("Year Diff->"+i);

					previousLink.click();

				}

			}

		}

		Thread.sleep(1000);

		//Get all months from calendar to select correct one

		List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));

		list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();

		Thread.sleep(1000);

		//get all dates from calendar to select correct one

		List<WebElement> list_AllDateToBook = driver.findElements(By.xpath("//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));

		list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();

		///FOR TIME

		WebElement selectTime = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_timeview']"));

		//click time picker button

		selectTime.click();

		//get list of times

		List<WebElement> allTime = driver.findElements(By.xpath("//div[@data-role='popup'][contains(@style,'display: block')]//ul//li[@role='option']"));

		dateTime = dateTime.split(" ")[1]+" "+dateTime.split(" ")[2];

		//select correct time

		for (WebElement webElement : allTime) {

			if(webElement.getText().equalsIgnoreCase(dateTime))

			{

				webElement.click();

			}

		}

	}

}

