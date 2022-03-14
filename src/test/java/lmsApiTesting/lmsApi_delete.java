package lmsApiTesting;



import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import utils.ApiConfig;
import utils.ExcelUtils;

public class lmsApi_delete {
	
	 @DataProvider(name = "deletedata")
	 
		String[][] get_prog_data() throws IOException {
		 
		String path = System.getProperty("user.dir") + "./data/Apilmstest.xlsx";
		String sheetname="Test004_delete";
		int rownum = ExcelUtils.getRowCount(path, sheetname);
		int colnum = ExcelUtils.getCellCount(path,sheetname, 1);
		String progdata[][] = new String[rownum][colnum];
		for (int i = 1; i <= rownum; i++) 
		{
		for (int j = 0; j < colnum; j++) 
		{
		progdata[i - 1][j] = ExcelUtils.getCelldata(path, sheetname, i, j);
		}
		}
		return progdata;
	 }
	 
		 
		 @Test(dataProvider = "deletedata")
			public void test_delete(String[] programId) {

				Reporter.log("Delete details for program id" + programId);

				Response response =  given()
						.auth().basic(ApiConfig.USERNAME, ApiConfig.PASSWORD)
													.delete(ApiConfig.BASE_URL  +ApiConfig.basePath + programId[0]);
				
				ResponseBody responsebody=response.getBody();
				Integer pgmId=responsebody.jsonPath().get("programId");
				System.out.println(pgmId);
				System.out.println(response.getStatusCode());
				
				//Json schema validation
				//String responseBody = response.getBody().asPrettyString(); 
				//assertThat("Json schema", responseBody.replaceAll("NaN","null"),JsonSchemaValidator.matchesJsonSchemaInClasspath("testpost.json"));
				//MatcherAssert.assertThat(responseBody,JsonSchemaValidator.matchesJsonSchemaInClasspath("testpost.json"));
			    //System.out.println("JSON Schema Validation is successful");
			    
			    
				
				
				
				int value=response.getStatusCode();
				if(value==200) {	
					Assert.assertEquals(value, 200,"Response received successfully");
					System.out.println("data is getting deleted");
				}
				
				else if(value==400) {
					Assert.assertEquals(value, 400,"Response received successfully");
					System.out.println("The programId is not valid");
				}
	else if(value==500) {
		Assert.assertEquals(value, 500,"Response received successfully");
					System.out.println("The data is already deleted");
		 }

	   }
	}