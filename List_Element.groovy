package reusableComponents

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement
import org.testng.Assert

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.common.WebUiCommonHelper

public class List_Element {


	@Keyword
	public static void Select_ExpectText_In_List(TestObject objectTo,String expected_Text) {
		int count = 0;

		List<WebElement> elements = WebUiCommonHelper.findWebElements(objectTo,30)
		//	WebUiCommonHelper.findWebElement(tableObject, 20);
		listloop:
		for(WebElement element: elements)
		{
			String actual_Text = element.getText();
			if(actual_Text.equals(expected_Text))
			{
				element.click();
				break listloop;
			}

			int imagescheck_Count = ++count
			//println "the count is ::"+imagescheck_Count
			if(elements.size().equals(imagescheck_Count))
			{
				Assert.fail("Given Expected text is ::"+expected_Text+" Not Presnet in the List please verify  the Name");
			}
		}
	}

	@Keyword
	public static void Verify_ExpectText_In_List(TestObject objectTo,String expected_Text) {
		int count = 0;
		List<WebElement> elements = WebUiCommonHelper.findWebElements(objectTo,30)
		//	WebUiCommonHelper.findWebElement(tableObject, 20);
		listloop:
		for(WebElement element: elements)
		{
			String actual_Text = element.getText();
			//println "the actual text is ::"+actual_Text
			if(actual_Text.equals(expected_Text))
			{
				println "Expected Text is ::"+expected_Text+":: and Actual Text is ::"+actual_Text+":: present in the list";
				break listloop;
			}

			int imagescheck_Count = ++count
			//println "the count is ::"+imagescheck_Count
			if(elements.size().equals(imagescheck_Count))
			{
				Assert.fail("Given Expected text is ::"+expected_Text+":: Not Presnet in the List please verify  the Name");
			}
		}
	}


	@Keyword
	public static void VerifyExpectedAndActual_List(TestObject objectto, List<String> listOfOptions) {
		try {
			WebElement element = WebUiCommonHelper.findWebElements(objectto, 20);

			List<String> expectedOptions = listOfOptions;
			List<String> actualOptions = new ArrayList<String>();
			for (WebElement option :element) {
				System.out.println("Dropdown options are: " + option.getText());
				actualOptions.add(option.getText());
			}

			Assert.assertEquals(expectedOptions.toArray(), actualOptions.toArray());
		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

}
