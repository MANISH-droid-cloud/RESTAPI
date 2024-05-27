package User_Management;


import static io.restassured.RestAssured.*;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.text.IsEmptyString;
import org.json.simple.JSONArray;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import utils.JsonReader;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;  // Updated import statement
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class getUsers {
	
	@Test
	public void getUserData() {
		
		given().
		when().get("https://reqres.in/api/users?page=2").
		then().
		assertThat().
		statusCode(200);
		
	}
	
	 @Test
	    public void validateGetResponseBody() {
	        // Set base URI for the API
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

	        // Send a GET request and validate the response body using 'then'
	        given()
	                .when()
	                .get("/todos/1")
	                .then()
	                .assertThat()
	                .statusCode(200)
	                .body(not(isEmptyString()))
	                .body("title", equalTo("delectus aut autem"))
	                .body("userId", equalTo(1));
	    }
	 
//	 @Test
//	    public void validateResponseHasItems() {
//	        // Set base URI for the API
//	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//
//	        // Send a GET request and store the response in a variable
//	        Response response = given()
//	                .when()
//	                .get("/posts")
//	                .then()
//	                .extract()
//	                .response();
//
//	        // Use Hamcrest to check that the response body contains specific items
//	        assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat", "qui est esse"));
//	    }

	    @Test
	    public void validateResponseHasSize() {
	        // Set base URI for the API
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

	        // Send a GET request and store the response in a variable
	        Response response = given()
	                .when()
	                .get("/comments")
	                .then()
	                .extract()
	                .response();

	        // Use Hamcrest to check that the response body has a specific size
	        assertThat(response.jsonPath().getList(""), hasSize(500));
	    }
	    
	    @Test(groups={"SmokeSuite","RegressionSuite"})
	    public void validateResponseBodyGetPathParam() {
	    	String raceSeasonValue = "2017";
	    	Response resp = given().pathParam("raceSeason", raceSeasonValue)
	    			      .when()
	    			      .get("http://ergast.com/api/f1/{raceSeason}/circuits.json"); //RestAssured
	    	int actualStatusCode = resp.statusCode();
	    	assertEquals(actualStatusCode, 200);
	    	System.out.println(resp.body().asString());
	    }
	    
	    @Test
	    public void testCreateUserWithFormParam()
	    {
	    	Response response = given() //Request Specification
	    			.contentType("application/x-www-form-urlencoded")
	    			.formParam("name", "John Doe")
	    			.formParam("job", "Developer")
	    			.when()
	    			.post("/users")
	    			.then()
	    			.statusCode(200)
	    			.extract()
	    			.response();
	    }
	    
	    @Test
	    public void testGetUserWithHeader()
	    {
	    	given()
	    	       .header("Content-Type","application/json")
	    	       .when()
	    	       .get("https://reqres.in/api/users?page=2")
	    	       .then()
	    	       .statusCode(200);
//	    	       .body("page", equalTo(2))
//	    	       .body("data[0].first_name", equalTo("Eve"))
//	    	       .body("data[0].last_name", equalTo("Holt"));
	    	System.out.println("testGetUserWithHeader Executed Successfully");	       
	    }
	    
	    @Test
	    public void testWithTwoHeaders()
	    {
	    	given()
	    	       .header("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx")
	    	       .header("Content-Type","application/json")
	    	       .when()
	    	       .get("https://reqres.in/api/users?page=2")
	    	       .then()
	    	       .statusCode(200);
	    	System.out.println("testWithTwoHeaders Executed Successfully");
	    	       
	    }
	    
	    @Test
	    public void testTwoHeadersWithMap()
	    {
	    	
	    	Map<String, String> headers = new HashMap();
	    	
	    	headers.put("Content-Type","application/json");
	    	headers.put("Authorization", "bearer ywtefdu13tx4fdub1t3ygdxuy3gnx1iuwdheni1u3y4gfuy1t3bx");
	    	given()
	    	       .headers(headers)
	    	       .when()
	    	       .get("https://reqres.in/api/users?page=2")
	    	       .then()
	    	       .statusCode(200);
	    	System.out.println("testTwoHeadersWithMap Executed Successfully");
	    	       
	    }
	    
	    @Test
	    public void testFetchHeaders()
	    {
	    	Response response = given()
	    	       .when()
	    	       .get("https://reqres.in/api/users?page=2")
	    	       .then()
	    	       
	    	       .extract().response();
	    	
	    	Headers headers = response.getHeaders();
	    	for(Header h : headers) {
	    		if (h.getName().contains("Server")) 
	    		{
	    		//System.out.println(h.getName()+" : "+h.getValue());
	    		assertEquals(h.getValue(), "cloudflare");
	    		System.out.println("testFetchHeaders Executed Successfully");
	    		
	    	}
	    	}
	    }
	    
	    @Test
	    public void Test() throws IOException, ParseException, Throwable
	    {
	    	JsonReader.getJsonArrayData("languages",0);
	    	JSONArray jsonArray = getJsonArray("contact");
	    	Iterator<String> iterator = jsonArray.iterator();
	         while(iterator.hasNext()) {
	            System.out.println(iterator.next());
	         }
	      

	    }

		private JSONArray getJsonArray(String string) {
			// TODO Auto-generated method stub
			return null;
		}

//		private JSONArray getJsonArray(String string) {
//			// TODO Auto-generated method stub
//			return null;
//		}
	     	
	    	       
	    }

