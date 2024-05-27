package User_Management;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.JsonReader;

public class getPostmanEcho {
	
	@Test(groups= {"SmokeSuite","RegressionSuite"})
    public void validateResponseBodyGetDigestAuth() {
    	
    	Response resp = given()
    			.auth()
    			.digest("postman", "password")
    			.when()
    			.get("https://postman-echo.com/basic-auth");
    	int actualStatusCode = resp.statusCode();
    	assertEquals(actualStatusCode, 200);
    	System.out.println(resp.body().asString());
    	
    }
	
	@Test(groups="RegressionSuite")
    public void validateWithTestDataFromJson() throws Throwable {
    	String username = JsonReader.getTestData("username");
    	String password = JsonReader.getTestData("password");
    	System.out.println(username +" : "+password);
    	Response resp = given()
    			.auth()
    			.basic(username, password)
    			.when()
    			.get("https://postman-echo.com/basic-auth");
    	int actualStatusCode = resp.statusCode();
    	assertEquals(actualStatusCode, 200);
    	System.out.println(resp.body().asString());
    	
    }
	
	 @Test
	    public void validateResponseBodyGetBasicAuth() {
	    	
	    	Response resp = given()
	    			.auth()
	    			.basic("postman", "password")
	    			.when()
	    			.get("https://postman-echo.com/basic-auth");
	    	int actualStatusCode = resp.statusCode();
	    	assertEquals(actualStatusCode, 200);
	    	System.out.println(resp.body().asString());
	    	
	    }

}
