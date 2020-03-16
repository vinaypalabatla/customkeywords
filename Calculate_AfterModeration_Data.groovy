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

public class Calculate_AfterModeration_Data {

	@Keyword
	public static boolean run(TestObject tableObject, String Moderation_Marks,int expTextColNum1,int expTextColNum2)
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

					for (int row = 0; row < rows_Count; row++) {
						List<WebElement> cols = rows.get(row).findElements(By.tagName("td"));

						//for(int col=0;cols<cols.size();col++)
						//{
						//}
						int Total_Marks =  Integer.parseInt(cols.get(expTextColNum1-1).getText());
						int actual_AfterModeration =  Integer.parseInt(cols.get(expTextColNum2-1).getText());
						int expected_AfterModerataion_Marks = Total_Marks+Integer.parseInt(Moderation_Marks);
						if(actual_AfterModeration == expected_AfterModerataion_Marks )
						{
							println"The Data is Matching for Total marks is ::"+Total_Marks+":: and added Moderation Marks is ::"+Moderation_Marks+":: and After Moderation Marks ::"+expected_AfterModerataion_Marks;
						}else{
							Assert.fail("The Data is Mismatch for the total marks ::"+Total_Marks+":: and added Moderation Marks is ::"+Moderation_Marks+":: and After Moderation Marks ::"+expected_AfterModerataion_Marks)
						}

						if(rows_Count == row+1)
						{
							status = true;
							page = false;
						}
					}
					if (status == true) {
						break;
					}
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			return false;
		}
		//return true;
	}
}
