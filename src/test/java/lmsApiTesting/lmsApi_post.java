package lmsApiTesting;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import utils.ApiConfig;
import utils.ExcelUtils;

public class lmsApi_post {
@DataProvider(name = "ProgramData")
		String[][] get_prog_data() throws IOException {
		String path = System.getProperty("user.dir") + "./data/lmspost.xlsx";
		String sheetname="Sheet1";
		int rownum = ExcelUtils.getRowCount(path,sheetname);
		int colnum = ExcelUtils.getCellCount(path, sheetname, 1);
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
		public void postprogram(String programId,String programName, String programDescription, String online) {
			
			JSONObject request = new JSONObject();
			request.put("programId", programId);
			request.put("programName", programName);
			request.put("programDescription", programDescription);
			request.put("online", online);

			System.out.println("request="+request);
			Response response=RestAssured.given().auth().basic(ApiConfig.USERNAME, ApiConfig.PASSWORD).header("Content-Type","application/json")
					.body(request.toJSONString()).when().post(ApiConfig.BASE_URL+ApiConfig.basePath).then()	.log().all().extract().response();
			
			
			//JSON schema validation
			ResponseBody responsebody=response.getBody();
			String responseBody = response.getBody().asPrettyString();
	        MatcherAssert.assertThat(responseBody,JsonSchemaValidator.matchesJsonSchemaInClasspath("testpost.json"));
	        System.out.println("JSON Schema Validation is successful");
			
	        //status code validation
	        
	        int statusCode = response.getStatusCode();			
			System.out.println("The response code is "+statusCode);
			if(statusCode==200) {
				Assert.assertEquals(statusCode, 200,"Response received successfully"); 
				System.out.println("The data is valid");
			}
			
			else if(statusCode==400)
			{
				Assert.assertEquals(statusCode, 400,"Response received successfully"); 
				System.out.println("The data is invalid");
				
			}
			else if(statusCode==500) {
				Assert.assertEquals(statusCode, 500,"Response received successfully"); 
				System.out.println("The data is invalid");
					



			
		

			}
}
}
