package jobsApi;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import utils.ApiConfig;
import utils.ExcelUtils;

public class jobsApi_delete {
	
	@DataProvider(name = "ProgramData")
	String[][] get_prog_data() throws IOException {
	String path = System.getProperty("user.dir") + "/data/jobstest.xlsx";
    String sheetname="jobsdelete";
	int rownum = ExcelUtils.getRowCount(path,sheetname);
	int colnum = ExcelUtils.getCellCount(path, sheetname, rownum);
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

	@Test(dataProvider = "ProgramData")

	  void test_delete(String[] JobId) {
		
		Reporter.log("Delete details " + JobId);
	
		Response response = RestAssured.given().queryParam("Job Id", JobId[0])
						  .when().delete(ApiConfig.baseurl)
				       .then().log().body().extract().response();
		
				 System.out.println(response.body().asPrettyString());
				 
				//Json schema validation
					String responseBody = response.getBody().asPrettyString(); 
					//assertThat("Json schema", responseBody.replaceAll("NaN","null"),JsonSchemaValidator.matchesJsonSchemaInClasspath("jobsApi_post.json"));
					MatcherAssert.assertThat(responseBody,JsonSchemaValidator.matchesJsonSchemaInClasspath("jobsApi_delete.json"));
				    System.out.println("JSON Schema Validation is successful");
				    
				    

		
           int statusCode=response.getStatusCode();
        
           System.out.println("The response code is" +statusCode);
      
	if(statusCode==200) {
		Assert.assertEquals(statusCode, 200,"Response received successfully"); 
		System.out.println("The data is valid");
	
	}
	
	else if(statusCode==404)
	{
		Assert.assertEquals(statusCode, 404,"Response received successfully"); 
		System.out.println("The Job not found");
		
	}
	else if(statusCode==500) {
		Assert.assertEquals(statusCode, 500,"Response received successfully"); 
		System.out.println("The data is invalid");
			
	}
  }
}

