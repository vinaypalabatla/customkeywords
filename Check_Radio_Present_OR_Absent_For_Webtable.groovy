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

public class Check_Radio_Present_OR_Absent_For_Webtable {

	@Keyword
	public static void run(TestObject tableObject, String expText,int expTextColNum,int checkOperationColNum,String check_Perform_Action_Name)
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
						if(cols.size() ==0) {
							println "the cols size is Zero"
						}else{
							actText = cols.get(expTextColNum-1).getText();
							if (actText.equals(expText)) {
								println ("Succesfully Matches the Actual text is :"+actText+" the Expected text is :"+expText)

								String expClickText = check_Perform_Action_Name.toLowerCase();
								println "the expClickText is ::"+expClickText
								List<WebElement> elements =cols.get(checkOperationColNum-1).findElements(By.xpath("//*[@type='radio']"));
								println "the no of elements is ::"+elements.size();


								String actual_AttributeText1 = elements.get(0).findElements(By.xpath("//*[@type='radio']/following-sibling::label")).get(0).getText();
								String actual_AttributeText2 = elements.get(0).findElements(By.xpath("//*[@type='radio']/following-sibling::label")).get(1).getText();

								//String actual_AttributeText = element.getAttribute(clickAttribute);
								println "the actual Attribute Text1 is ::"+actual_AttributeText1
								println "the actual Attribute Text2 is ::"+actual_AttributeText2
								if(actual_AttributeText1.contains(check_Perform_Action_Name)||  actual_AttributeText1.equalsIgnoreCase(check_Perform_Action_Name))
								{
									boolean present_Status = elements.get(0).isSelected();
									if(present_Status == true)
									{
										println 'No need to check because "Present" Radio Button is already checked'
									}else {
										elements.get(0).click();
									}


									status = true;
									page = false;
									break table;
								}else if (actual_AttributeText2.contains(check_Perform_Action_Name)||  actual_AttributeText2.equalsIgnoreCase(check_Perform_Action_Name))
								{
									boolean absent_Status = elements.get(1).isSelected();
									if(absent_Status == true)
									{
										println 'No need to check because "Absent" Radio Button is already checked'
									}else {
										elements.get(1).click();
									}
									status = true;
									page = false;
									break table;
								}

								/*for(WebElement element: elements)
								 {
								 String actual_AttributeText = element.findElement(By.xpath("//following-sibling::label")).getText();
								 //String actual_AttributeText = element.getAttribute(clickAttribute);
								 println "the actual Attribute Text is ::"+actual_AttributeText
								 if(actual_AttributeText.contains(check_Perform_Action_Name)||  actual_AttributeText.equalsIgnoreCase(check_Perform_Action_Name))
								 {
								 element.click();
								 status = true;
								 page = false;
								 break table;
								 }
								 }*/
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
