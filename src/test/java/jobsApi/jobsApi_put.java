package jobsApi;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import utils.ApiConfig;
import utils.ExcelUtils;


public class jobsApi_put {
	@DataProvider(name = "ProgramData")
	String[][] get_prog_data() throws IOException {
	String path = System.getProperty("user.dir") + "./data/jobstest.xlsx";
    String sheetname="jobsput";
	int rownum = ExcelUtils.getRowCount(path, sheetname);
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
	public void test_put(String JobId,String JobTitle,String JobLocation,String JobCompanyName,String JobType,String JobPostedtime,String JobDescription){
	{
		
	JSONObject request = new JSONObject();
	
	request.put("Job Id",JobId);
	request.put("Job Title",JobTitle);
	request.put("Job Location",JobLocation);
	request.put("Job Company Name",JobCompanyName);
	request.put("Job Type",JobType);
	request.put("Job Posted time",JobPostedtime);
	request.put("Job Description",JobDescription);
	
	System.out.println("request="+request);
	
	Response response = RestAssured.given().header("Content-Type","application/json").
			body(request.toJSONString())
			.when().put(ApiConfig.baseurl).
  		  then().log().all().extract().response();
	request = new JSONObject();
	
	request.put("Job Id",JobId);
	request.put("Job Title",JobTitle);
	
	response = given()
				.header("Content-type", "application/json").and().body(request.toJSONString()).when()
				.put(ApiConfig.baseurl).then().extract().response();
	ResponseBody responsebody=response.getBody();
	 JsonPath jsonPathEvaluator = responsebody.jsonPath();	
	 
	////Json schema validation
		String responseBody = response.getBody().asPrettyString(); 
		//assertThat("Json schema", responseBody.replaceAll("NaN","null"),JsonSchemaValidator.matchesJsonSchemaInClasspath("jobsApi_post.json"));
		MatcherAssert.assertThat(responseBody,JsonSchemaValidator.matchesJsonSchemaInClasspath("jobsApi_post.json"));
	  System.out.println("JSON Schema Validation is successful");
	  	
				  			
				  	}
				  	}
				  
				  	
				}
	 
	 
	


		

	 
	 
	 
	 
	 

	