package applicationlogin

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.lang.ref.ReferenceQueue.Null

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.HasInputDevices
import org.openqa.selenium.interactions.*;

import internal.GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
//import org.openqa.selenium.internal.Locatable updated Locatable as below
import com.sun.xml.internal.bind.Locatable;

public class VerifyDataInWebTable {

	public static WebDriver driver =null;
	@Keyword
	def mouseOver(TestObject to) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(to, 30)
		Locatable hoverItem = (Locatable) element
		Mouse mouse = ((HasInputDevices) driver).getMouse()
		mouse.mouseMove(hoverItem.getCoordinates())
	}
	@Keyword
	public static boolean verify_expText_In_Colum_Table(TestObject tableObject,String expText,int expTextColNum)
	throws Exception {
		boolean status = false;
		boolean page = true;
		String actText = null;
		try {

			table: while (page) {
				driver = DriverFactory.getWebDriver()
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
								return true ;
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
						return false;
					} else {

						Assert.fail(
								"The searching Element is ::" + expText + ":: not present in table");
						return false;
					}
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			return false;
		}
		//return true;
	}

	@Keyword
	public static void verify_Deleted_ExpText_In_Colum_Table(TestObject tableObject,String expText,int expTextColNum)
	throws Exception {
		boolean status = false;
		boolean page = true;
		String actText = null;
		try {

			table: while (page) {
				driver = DriverFactory.getWebDriver()
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
								Assert.fail("The searching Expected Text is ::" + expText + ":: present in table");
								println ("Succesfully Matches the Actual text is :"+actText+" the Expected text is :"+expText)
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
								"The searching Element is ::" + expText + ":: not present in table");
					}
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	@Keyword
	public static void verify_MultipleTexts_In_Colum_Table(TestObject tableObject,String expText1,int expText1ColNum,String expText2,int expText2ColNum)
	throws Exception {
		boolean status = false;
		boolean page = true;
		String actText1 = null,actText2 = null;
		try {

			table: while (page) {
				driver = DriverFactory.getWebDriver()
				WebElement table =WebUiCommonHelper.findWebElement(tableObject, 20);
				if (table.isDisplayed()) {
					List<WebElement> rows = table.findElements(By.tagName("tr"));
					int rows_Count = rows.size();
					//println ('the row size is '+rows_Count)
					for (int row = 0; row < rows_Count; row++) {
						List<WebElement> cols = rows.get(row).findElements(By.tagName("td"));

						if(cols.size() ==0)
						{
							println "the cols size is Zero"
						}else{
							//	println ('the cols size is '+cols.size())
							actText1 = cols.get(expText1ColNum-1).getText().replace("\n", " ");
							actText2 = cols.get(expText2ColNum-1).getText().replace("\n", " ");;
							println "actual text 1 is "+actText1+" actual text 2 is "+actText2
							println "Expected text 1 is "+expText1+" Expected text 2 is "+expText2
							if ((actText1.equals(expText1))&& (actText2.equals(expText2)) ) {
								println ("Succesfully Matches the First Actual text is :"+actText1+" the First Expected text is :"+expText1)
								println ("Succesfully Matches the Second Actual text is :"+actText2+" the Second Expected text is :"+expText2)
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
				if (((actText1 != expText1)|| (actText2 != expText2))||((actText1 != expText1)&& (actText2 != expText2))) {
					if (driver.findElements(By.xpath("//li[@class='next']/a")).size() != 0) {
						driver.findElement(By.xpath("//li[@class='next']/a")).click();
						Thread.sleep(1500);
					} else {
						Assert.fail(
								"The searching Elements are ::" + expText1 + ":: ::" + expText2 + ":: not present in table");
					}
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Keyword
	public static void verify_Multiple_Headings_In_Colum_Table(TestObject tableObject,String expText1,int expText1ColNum,String expText2,int expText2ColNum)
	throws Exception {
		boolean status = false;
		boolean page = true;
		String actText1 = null,actText2 = null;
		try {

			table: while (page) {
				driver = DriverFactory.getWebDriver()
				WebElement table =WebUiCommonHelper.findWebElement(tableObject, 20);
				if (table.isDisplayed()) {
					List<WebElement> rows = table.findElements(By.tagName("tr"));
					int rows_Count = rows.size();
					//println ('the row size is '+rows_Count)
					for (int row = 0; row < rows_Count; row++) {
						List<WebElement> cols = rows.get(row).findElements(By.tagName("th"));

						if(cols.size() ==0)
						{
							println "the cols size is Zero"
						}else{
							//	println ('the cols size is '+cols.size())
							actText1 = cols.get(expText1ColNum-1).getText().replace("\n", " ");
							actText2 = cols.get(expText2ColNum-1).getText().replace("\n", " ");;
							println "actual text 1 is "+actText1+" actual text 2 is "+actText2
							println "Expected text 1 is "+expText1+" Expected text 2 is "+expText2
							if ((actText1.equals(expText1))&& (actText2.equals(expText2)) ) {
								println ("Succesfully Matches the First Actual text is :"+actText1+" the First Expected text is :"+expText1)
								println ("Succesfully Matches the Second Actual text is :"+actText2+" the Second Expected text is :"+expText2)
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
				if (((actText1 != expText1)|| (actText2 != expText2))||((actText1 != expText1)&& (actText2 != expText2))) {
					if (driver.findElements(By.xpath("//li[@class='next']/a")).size() != 0) {
						driver.findElement(By.xpath("//li[@class='next']/a")).click();
						Thread.sleep(1500);
					} else {
						Assert.fail(
								"The searching Elements are ::" + expText1 + ":: ::" + expText2 + ":: not present in table");
					}
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Keyword
	public static String verify_MultipleTexts_Contains_GetText_In_Colum_Table(TestObject tableObject,String expText1,int expText1ColNum,String expText2,int expText2ColNum)
	throws Exception {
		boolean status = false;
		boolean page = true;
		String actText1 = null,actText2 = null;
		try {
			table: while (page) {
				driver = DriverFactory.getWebDriver()
				WebElement table =WebUiCommonHelper.findWebElement(tableObject, 20);
				if (table.isDisplayed()) {
					List<WebElement> rows = table.findElements(By.tagName("tr"));
					int rows_Count = rows.size();
					//println ('the row size is '+rows_Count)
					for (int row = 0; row < rows_Count; row++) {
						List<WebElement> cols = rows.get(row).findElements(By.tagName("td"));

						if(cols.size() ==0)
						{
							println "the cols size is Zero"
						}else{
							//	println ('the cols size is '+cols.size())
							actText1 = cols.get(expText1ColNum-1).getText();
							actText2 = cols.get(expText2ColNum-1).getText();
							println "actual text 1 is "+actText1+" actual text 2 is "+actText2
							println "Expected text 1 is "+expText1+" Expected text 2 is "+expText2
							if ((actText1.contains(expText1))&& (actText2.contains(expText2)) ) {
								println ("Succesfully Contains the First Expected text is :"+expText1+"  in the First Actual text is :"+actText1)
								println ("Succesfully Matches the Second Actual text is :"+expText2+" the Second Expected text is :"+actText2)
								return actText1;
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

				if (((actText1 != expText1)|| (actText2 != expText2))||((actText1 != expText1)&& (actText2 != expText2))) {
					if (driver.findElements(By.xpath("//li[@class='next']/a")).size() != 0) {
						driver.findElement(By.xpath("//li[@class='next']/a")).click();
						Thread.sleep(1500);
					} else {
						Assert.fail(
								"The searching Elements are ::" + expText1 + ":: ::" + expText2 + ":: not present in table");

					}
				}
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			return null;
		}
	}

	@Keyword
	public static void Set_Text_In_WebTable(TestObject tableObject, String expText,int expTextColNum,int setText_Col_Num,String exp_Set_Text)
	throws Exception {
		boolean status = false;
		boolean page = true;
		String actText = null;
		try {

			table: while (page) {
				driver = DriverFactory.getWebDriver()
				WebElement table =WebUiCommonHelper.findWebElement(tableObject, 20);
				if (table.isDisplayed()) {
					List<WebElement> rows = table.findElements(By.tagName("tr"));
					int rows_Count = rows.size();

					for (int row = 0; row < rows_Count; row++) {
						List<WebElement> cols = rows.get(row).findElements(By.tagName("td"));
						if(cols.size() ==0)
						{
							println "the cols size is Zero"
						}else{
							actText = cols.get(expTextColNum-1).getText();
							if (actText.equals(expText)) {
								println ("Succesfully Matches the Actual text is :"+actText+" the Expected text is :"+expText)
								WebElement input_Element = cols.get(setText_Col_Num-1).findElement(By.tagName("input"));
								input_Element.clear();
								input_Element.sendKeys(exp_Set_Text)
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
	@Keyword
	public static void click_Buttons_Operation_In_WebTable(TestObject tableObject, String expText,int expTextColNum,String clickOperation,int clickOperationColNum,String clickAttribute)
	throws Exception {
		boolean status = false;
		boolean page = true;
		String actText = null;
		try {

			table: while (page) {
				driver = DriverFactory.getWebDriver()
				WebElement table =WebUiCommonHelper.findWebElement(tableObject, 20);
				if (table.isDisplayed()) {
					List<WebElement> rows = table.findElements(By.tagName("tr"));
					int rows_Count = rows.size();

					for (int row = 0; row < rows_Count; row++) {
						List<WebElement> cols = rows.get(row).findElements(By.tagName("td"));
						if(cols.size() ==0)
						{
							println "the cols size is Zero"
						}else{
							actText = cols.get(expTextColNum-1).getText();
							if (actText.equals(expText)) {
								println ("Succesfully Matches the Actual text is :"+actText+" the Expected text is :"+expText)

								String expClickText = clickOperation.toLowerCase();
								println "the expClickText is ::"+expClickText
								List<WebElement> elements =cols.get(clickOperationColNum-1).findElements(By.tagName("a"));
								println "the no of elements is ::"+elements.size();
								for(WebElement element: elements)
								{
									String actual_AttributeText = element.getAttribute(clickAttribute);
									println "the actual Attribute Text is ::"+actual_AttributeText
									if(actual_AttributeText.contains(expClickText))
									{
										element.click();

										status = true;
										page = false;
										break table;
									}
								}
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
