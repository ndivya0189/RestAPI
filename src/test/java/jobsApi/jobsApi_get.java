package jobsApi;
import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import utils.ApiConfig;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;

public class jobsApi_get {
	
	@Test    
    void test_getAll() {

	  		Response response= RestAssured.given().when().get(ApiConfig.baseurl).
            		  then().log().all().extract().response();
              int statuscode=response.getStatusCode();
              Assert.assertEquals(statuscode, 200,"Response received successfully"); 
           System.out.println("The response code is" +statuscode);
           Reporter.log("Response recieved successfully");
           
           
          
           
	}

	@Test    
	void test_get1() {

  		Response response= RestAssured.given().when().get(ApiConfig.baseurl+null).
        		  then().log().all().extract().response();
          int statuscode=response.getStatusCode();
          Assert.assertEquals(statuscode, 404,"Response received successfully"); 
       System.out.println("The response code is" +statuscode);
   
	}
}



	
