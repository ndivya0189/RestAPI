package lmsApiTesting;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import utils.ApiConfig;
public class lmsApi_get {
	@Test
	public void testgetAll()
	
	 {
	
		
	Response response=	RestAssured.given().auth().basic(ApiConfig.USERNAME, ApiConfig.PASSWORD)
		.when().get(ApiConfig.BASE_URL+ApiConfig.basePath);
	@SuppressWarnings({ "rawtypes", "unused" })
	ResponseBody responsebody=response.getBody();
	@SuppressWarnings("unused")
	String ResponseBody=response.getBody().asPrettyString();
	MatcherAssert.assertThat(ResponseBody,JsonSchemaValidator.matchesJsonSchemaInClasspath("getschema.json"));
	
	int statuscode=response.getStatusCode();
	Assert.assertEquals(statuscode ,200,"response received successfully");
	}
	@Test
   public void test_get2() {
    	Response response=RestAssured.given().get(ApiConfig.BASE_URL);
    	int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 401,"Response received successfully");
		
		
 }
}