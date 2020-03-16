package reusableComponents

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

public class RM_Add_Image_From_Gallery {
	 public static int count =0;
	@Keyword
	public static void Images_List(TestObject objectTo,String expected_Text) {
		
		
		def driver = DriverFactory.getWebDriver()
		WebElement gallery_Button = WebUiCommonHelper.findWebElement(objectTo, 20);
		
		JavascriptExecutor executor = ((JavascriptExecutor)driver);
		executor.executeScript("arguments[0].click();", gallery_Button);
		
		//gallery_Button.click();
		
		WebElement header_Title =(driver).findElement(By.xpath("//*[@id='myModalLabel'][contains(.,'Select Images')]"));
		 
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(header_Title));
		Thread.sleep(1000)
		//Actions action = new Actions(driver);
		
		
			//List<WebElement> elements = driver.findElements(By.xpath("//*[@id='Modalimagesselect']/div[*]/label"))
			 //	WebUiCommonHelper.findWebElement(tableObject, 20);
			// println("the Size of the Elements of 1st loop are "+elements.size())
			// action.moveToElement(elements.get(elements.size()-1)).build().perform();
			// println " the last element of the loop 1st of text is ::"+elements.get(elements.size()-1).getText(); 
			 Thread.sleep(5000) 
			
		List<WebElement> final_elements = driver.findElements(By.xpath("//*[@id='Modalimagesselect']/div[*]/label"))
		
		println("the Size of the Final list Elements are "+final_elements.size())
		listloop:
		for(WebElement image_Element: final_elements)
		{
			String actual_Text = image_Element.getText();
			println "The Actual Text is ::"+actual_Text
			if(actual_Text.contains(expected_Text))
			{
				image_Element.click();
				Thread.sleep(1000)
				break listloop;
			}
			int imagescheck_Count = ++count
			println "the count is ::"+imagescheck_Count
			if(final_elements.size().equals(imagescheck_Count))
			{
				Assert.fail("Given Expected text is ::"+expected_Text+" Not Presnet in the List please verify  the Name");
			}
		}
		
		WebElement button_Select =(driver).findElement(By.xpath("//button[contains(@onclick,'showSelectedImages')]"));
		 button_Select.click();
	}
}
