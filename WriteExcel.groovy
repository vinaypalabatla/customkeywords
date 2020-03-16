package applicationlogin

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import internal.GlobalVariable

public class WriteExcel {
	private  static int i=0;

	@Keyword
	def void writeToExcel(int iRow, int iCell, String iText ){

		FileInputStream file = new FileInputStream (new File("C:\\Users\\vinay\\Katalon Studio\\RedFM\\Data Files\\Testdata.xlsx"))

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		XSSFSheet sheet = workbook.getSheet("sheet1")

		//Write data to excel’

		Row oRow;

		oRow = sheet.getRow(iRow);

		if(oRow == null){

			sheet.createRow(iRow);

			oRow = sheet.getRow(iRow);


		}

		Cell oCell;

		oCell = oRow.getCell(iCell - 1);

		if(oCell == null ){

			oRow.createCell(iCell - 1);

			oCell = oRow.getCell(iCell - 1);

		}

		oCell.setCellValue(iText);

		FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\vinay\\Katalon Studio\\RedFM\\Data Files\\Testdata.xlsx"));

		workbook.write(outFile);

		outFile.close();

	}

}
