package reusableComponents
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

public class RM_Calendar {
	//	static WebElement SelectCalender=null

	@Keyword
	public static void handleDatepicker(TestObject calender, String exp_Day, String exp_Month,
			String exp_Year,String exp_Hour,String exp_Minutes, String exp_Seconds, String time_AM_or_PM)throws Exception{
		String expDate = null;
		int expMonth = 0;
		String expYear = null;
		WebElement datePicker;
		List<WebElement> noOfDays=null;

		boolean dateNotFound = true;
		List<String> monthList = Arrays.asList("None","Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec");
		def driver = DriverFactory.getWebDriver()
		WebElement SelectCalender = WebUiCommonHelper.findWebElement(calender, 20);
		JavascriptExecutor executor = ((JavascriptExecutor)driver);
		executor.executeScript("arguments[0].click();", SelectCalender);
		//SelectCalender.click()
		// Set your expected date, month and year.

		// If Zero Contains first letter it removes the Exp Date
		if(exp_Day.charAt(0)==0)
		{
			expDate = Integer.parseInt(exp_Day.substring(0));
			println "Exp month of the after removal of 0 is ::"+expMonth
		}else{
			expDate = Integer.parseInt(exp_Day);
		}
		// If Zero Contains first letter it removes the Exp Month
		if(exp_Month.charAt(0)==0)
		{
			expMonth = Integer.parseInt(exp_Month.substring(0));
			println "Exp month of the after removal of 0 is ::"+expMonth
		}else{
			expMonth = Integer.parseInt(exp_Month);
		}

		//	expMonth = Integer.parseInt(exp_Month);
		expYear = exp_Year;

		WebElement month_DropDown =(driver).findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/select[@class='form-control datetime-ui-datepicker-month input-width-20']"));


		WebDriverWait wait = new WebDriverWait(driver,10);

		wait.until(ExpectedConditions.visibilityOf(month_DropDown));
		Select select = new Select(month_DropDown);

		try {
			select.selectByVisibleText(monthList.get(expMonth));
		} catch (ArrayIndexOutOfBoundsException e) {
			Assert.fail("You are Passed wrong Number as ::"+expMonth+". Please pass from 1 to 12 Numbers because of Month numbers.")
		}catch (Exception e){
			Assert.fail("The Expected Month Text is not Find in Dropdown Please Contact Administrator.")
		}

		Thread.sleep(2000);
		WebElement year_DropDown =(driver).findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/div/select[@class='form-control datetime-ui-datepicker-year input-width-20']"));
		//select.getFirstSelectedOption()
		wait.until(ExpectedConditions.visibilityOf(year_DropDown));
		Select select1 = new Select(year_DropDown);
		try {
			select1.selectByVisibleText(expYear);
		} catch (Exception e) {
			Assert.fail("The Expected Year is Not in Drop Down. Please check Manual and Pass Available year.")
		}

		datePicker = (driver).findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody"));
		wait.until(ExpectedConditions.visibilityOf(datePicker));
		noOfDays = datePicker.findElements(By.xpath("//td/a[@class='datetime-ui-state-default']"));
		println "the Size of the days are :: "+noOfDays.size();


		firstloop:
		for (WebElement date : noOfDays) {
			if (date.getText().equalsIgnoreCase(expDate)) {
				date.click();
				break firstloop;
			}
		}


		WebElement input_Hour =(driver).findElement(By.xpath("//input[contains(@id,'timePicker_txtDateTime_hour')]"));
		input_Hour.clear();
		input_Hour.sendKeys(exp_Hour);

		WebElement input_Minutes =(driver).findElement(By.xpath("//input[contains(@id,'timePicker_txtDateTime_minute')]"));
		input_Minutes.clear();
		input_Minutes.sendKeys(exp_Minutes);

		WebElement input_Seconds =(driver).findElement(By.xpath("//input[contains(@id,'timePicker_txtDateTime_second')]"));
		input_Seconds.clear();
		input_Seconds.sendKeys(exp_Seconds);

		WebElement dropdown_Time =(driver).findElement(By.xpath("//select[contains(@id,'timePicker_txtDateTime_hour')]"));

		Select time = new Select(dropdown_Time);
		time.selectByVisibleText(time_AM_or_PM);

		WebElement button_Select =(driver).findElement(By.xpath("//button[contains(@onclick,'cmsdatepicker._saveAndClose')]"));
		button_Select.click();


	}

}
