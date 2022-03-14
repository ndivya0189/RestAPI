import utils.ExcelUtils;



import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class testSample {
	@DataProvider(name = "data_for_positive")
	public Object[][] dataForGet() throws IOException {
		String Excelpath = "./data/Api_lms_RestAssured.xlsx/";    
		XSSFWorkbook workbook = new XSSFWorkbook(Excelpath);
		System.out.println(workbook);
		XSSFSheet getsheet = workbook.getSheet("Sheet1");
		DataFormatter formatter= new DataFormatter();
		int rowNum = getsheet.getLastRowNum();
		int colNum = getsheet.getRow(0).getLastCellNum();
		System.out.println("the row count is " +rowNum);
		System.out.println("The Column count is "+colNum);

		String data[][]=new String [rowNum][colNum];

		for (int i=1;i<=rowNum;i++) {
			for(int j=0;j<colNum;j++) {

		String parameter = (formatter.formatCellValue(getsheet.getRow(i).getCell(j)));
		System.out.println("\n"+parameter);
		data[i-1][j]= (formatter.formatCellValue(getsheet.getRow(i).getCell(j)));
		workbook.close();
			}} 
		return(data);

	}
	@DataProvider(name = "Data_for_negative")
	public Object[][] dataForGetNeg() throws IOException {

		String Excelpath = "./data/Api_lms_RestAssured.xlsx/";     
		XSSFWorkbook workbook = new XSSFWorkbook(Excelpath);
		System.out.println(workbook);
		XSSFSheet getsheet = workbook.getSheet("Sheet2");
		DataFormatter formatter= new DataFormatter();
		int rowNum = getsheet.getLastRowNum();
		int colNum = getsheet.getRow(0).getLastCellNum();
		System.out.println("the row count is " +rowNum);
		System.out.println("The Column count is "+colNum);
		String data[][]=new String [rowNum][colNum];

		for (int i=1;i<=rowNum;i++) {
			for(int j=0;j<colNum;j++) {

		String parameter = 
		(formatter.formatCellValue(getsheet.getRow(i).getCell(j)));
		System.out.println("\n"+parameter);
		data[i-1][j]= 

		(formatter.formatCellValue(getsheet.getRow(i).getCell(j)));
		workbook.close();
			}} 
		return(data);

	}

}
