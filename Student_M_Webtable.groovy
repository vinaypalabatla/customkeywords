package reusableComponents

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.testng.Assert

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class Student_M_Webtable {
	@Keyword
	public static void Handle_Click(TestObject tableObject, String expText,int expTextColNum,int clickOperationColNum,String exp_ClickText)
	throws Exception {
		boolean status = false;
		boolean page = true;
		String actText = null;
		try {

			table: while (page) {
				def driver = DriverFactory.getWebDriver()
				WebElement table =WebUiCommonHelper.findWebElement(tableObject, 20);
				if (table.isDisplayed()) {
					List<WebElement> rows = table.findElements(By.tagName("tr"));
					int rows_Count = rows.size();
					println "rows Size is ::"+rows_Count
					for (int row = 0; row < rows_Count; row++) {
						List<WebElement> cols = rows.get(row).findElements(By.tagName("td"));
						println "cols Size is ::"+cols.size()
						if(cols.size() ==0) {
							println "the cols size is Zero"
						}else{
							actText = cols.get(expTextColNum-1).getText();
							if (actText.equals(expText)) {
								println ("Succesfully Matches the Actual text is :"+actText+" the Expected text is :"+expText)

								cols.get(clickOperationColNum-1).findElements(By.tagName("a")).get(0).click();

								List <WebElement> elements = cols.get(clickOperationColNum-1).findElements(By.xpath("//ul[@class='dropdown-menu pull-left']/li[*]"));

								for (WebElement element_Text in elements) {
									String actual = element_Text.getText().toLowerCase();
									String expected = exp_ClickText.toLowerCase();
									if(actual.contains(expected)) {
										element_Text.click();
										break;
									}
								}

								status = true;
								page = false;
								break table;
							}
						}
					}
					if (status == true) {
						break;
					}
				}

				if (actText != expText) {
					if (driver.findElements(By.xpath("//li[@class='next']/a")).size() != 0) {
						driver.findElement(By.xpath("//li[@class='next']/a")).click();
						Thread.sleep(1500);
					} else {

						Assert.fail(
								"The searching Text is ::" + expText + ":: not present in table");
					}
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
