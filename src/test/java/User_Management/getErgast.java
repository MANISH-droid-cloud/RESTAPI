package User_Management;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import utils.JsonReader;
import utils.PropertyReader;
import utils.SoftAssertionUtil;

import static io.restassured.RestAssured.given;
import static io.restassured.http.Cookie.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.hamcrest.Matcher;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class getErgast {

    @Test(groups="SmokeSuite")
    public void testUserCookies() {
//        Cookie myCookie = cookie("my_cookie_name","my_cookie_value");
//        myCookie = myCookie.setDomain("example.com")
//        		.setPath("/api")
//        		.setMaxAge(60*60*24*365);
        given()
        .cookie("","")
        //.cookie("test","testing")
        //.cookie("test2","testing2")
        .when()
        .get("https://reqres.in/api/users?page=2")
        .then()
        .statusCode(200);
        //.body("response",equalTo("expected_value"));
        System.out.println("testUserCookies Executed Successfully");
        
    }
    
    @Test
    public void testUseCookies() {
    	Cookie cookies = new Cookie.Builder("cookieKey1", "cookieValue1")
    			.setComment("using cookie key")
    			.build();
    	given()
    	      .cookie(cookies)
    	      .when()
    	      .get("https://reqres.in/api/users?page=2")
    	      .then()
    	      .statusCode(200);
    	System.out.println("testUseCookies Executed Successfully");
    	
    }
    
    @Test
    public void testFetchCookies() {
    	Response response = given()
    			.when()
    			.get("https://reqres.in/api/users?page=2")
    			.then()
    			.extract().response();
    	
    	Map<String, String> cookies = response.getCookies();
    	Cookies cookies1 = response.getDetailedCookies();
    	cookies1.getValue("server");
    	assertEquals(cookies1.getValue("cloudflare"));
    	//assertThat(cookies, hasKey(""))
    	
//    	assertThat(cookies, hasKey("JSESSIONID"));
//    	assertThat(cookies, hasValue("ABCDEF123456"));
    	
    }
    
    @Test
    public void verifyStatusCodeDelete() {
    	Response resp = given()
    			.delete("https://reqres.in/api/users?page=2");
    	assertEquals(resp.getStatusCode(),statusCode.SUCCESS.code);
    	System.out.println("verifyStatusCodeDelete is Successful");
    	
    }

	private void assertEquals(int actualStatusCode, int i) {
		// TODO Auto-generated method stub
		
	}

	private void assertEquals(String value) {
		// TODO Auto-generated method stub
		
	}

	private Matcher<? super Map<String, String>> hasValue(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private Matcher<? super Map<String, String>> hasKey(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private ResponseAwareMatcher<Response> equalTo(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Test
    public void validateWithDataFromPropertiesFile() {
		String serverAddress = PropertyReader.propertyReader("D:\\QEdge\\selenium_java\\RESTAPI\\target\\configuration.properties", "serverAddress");  
    	System.out.println("Server Address :"+serverAddress);
		Response resp = given()
    			.queryParam("page", 2)
    			.when()
    			.get(serverAddress);
        int actualStatusCode = resp.getStatusCode();
        assertEquals(actualStatusCode,200);
    	//assertEquals(resp.getStatusCode(),statusCode.SUCCESS.code);
    	System.out.println("validateWithDataFromPropertiesFile verified Successful");
    	
    }
	
	@Test
    public void validateWithDataFromPropertiesFile_TestData() throws Throwable {
		String serverAddress = PropertyReader.propertyReader("D:\\QEdge\\selenium_java\\RESTAPI\\target\\configuration.properties", "server");
    	String endpoint = JsonReader.getTestData("endpoint");
		String URL = serverAddress+endpoint;
		System.out.println("URL Address is :"+URL);
		Response resp = given()
    			.queryParam("page", 2)
    			.when()
    			.get(URL);
        int actualStatusCode = resp.getStatusCode();
        assertEquals(actualStatusCode,200);
    	//assertEquals(resp.getStatusCode(),statusCode.SUCCESS.code);
    	System.out.println("validateWithDataFromPropertiesFile_TestData verified Successful");
    	
    }
	
	@Test
    public void softAssertion(){
		SoftAssertionUtil softAssertion = new SoftAssertionUtil();
		System.out.println("softAssert");
		softAssertion.assertTrue(true,"Successful");
		//softAssertion.assertTrue(true);
		softAssertion.assertAll();
		
	}
//	@Test
//    public void validateWithSoftAssertUtil() {
//		RestAssured.baseURI = "https://reqres.in/api";
//		Response response = given()
//    			.queryParam("page", 2)
//    			.when()
//    			.get("/users")
//    			.then()
//    			statusCode(200)
//    			.extract()
//    			.response();
//        
//    }

	@DataProvider(name = "testdata")
	public Object[][] testData() {
	    return new Object[][] {
	        {"1", "John"},
	        {"2", "Jane"},
	        {"3", "Bob"}
	    };
	}
	
	@Test(dataProvider = "testdata")
	@Parameters({"id", "name"})
	public void testEndpoint(String id, String name) {
	    given()
	        .queryParam("id", id)
	        .queryParam("name", name)
	    .when()
	        .get("https://reqres.in/api/users")
	    .then()
	        .statusCode(200);
	}

}


