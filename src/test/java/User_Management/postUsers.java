package User_Management;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

public class postUsers {
	//private static String requestBodyFileName;
	private static FileInputStream fileInputStreamMethod(String requestBodyFileName) {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream (new File(System.getProperty("user.dir")+"/src/test/resources/TestData/"+requestBodyFileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return fileInputStream;
	}
	
	@Test
	public void validatePostWithString() {

        // Authenticate and get an authorization token
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"id\":\"192\",\"createdAt\":\"2024-04-09T00:53:35.223Z\"}")
                .when()
                .post("https://reqres.in/api/users");
        assertEquals(response.getStatusCode(),statusCode.CREATED.code);
        System.out.println("validatePostWithString executed successfully");
        System.out.println(response.getBody().toString());
        System.out.println(response.getBody().asString());
	}
	
	@Test
	public void validatePutWithString() {

        // Authenticate and get an authorization token
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"id\":\"190\",\"createdAt\":\"2024-04-09T00:53:35.223Z\"}")
                .when()
                .put("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(),statusCode.SUCCESS.code);
        System.out.println("validatePutWithString executed successfully");
        System.out.println(response.getBody().toString());
        System.out.println(response.getBody().asString());
	}
	
	@Test
	public void validatePatchWithString() {

        // Authenticate and get an authorization token
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"morpheus\",\"job\":\"zionresident\"}")
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(),statusCode.SUCCESS.code);
        System.out.println("validatePatchWithString executed successfully");
        System.out.println(response.getBody().toString());
        System.out.println(response.getBody().asString());
	}
	
	@Test
	public void validatePostWithJsonFile() throws IOException {
		String requestBodyFileName = "postRequestBody.json";
        // Authenticate and get an authorization token
        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(fileInputStreamMethod(requestBodyFileName)))
                .when()
                .patch("https://reqres.in/api/users/6");
        assertEquals(response.getStatusCode(),statusCode.SUCCESS.code);
        System.out.println("validatePostWithJsonFile executed successfully");
        System.out.println(response.getBody().toString());
        System.out.println(response.getBody().asString());
	}
	
	@Test
	public void validatePutWithJsonFile() throws IOException {
		String requestBodyFileName = "putRequestBody.json";
        // Authenticate and get an authorization token
        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(fileInputStreamMethod(requestBodyFileName)))
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(),statusCode.SUCCESS.code);
        System.out.println("validatePutWithJsonFile executed successfully");
        System.out.println(response.getBody().toString());
        System.out.println(response.getBody().asString());
	}
	
	@Test
	public void validatePatchWithJsonFile() throws IOException {
		String requestBodyFileName = "patchRequestBody.json";
        // Authenticate and get an authorization token
        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(fileInputStreamMethod(requestBodyFileName)))
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(),statusCode.SUCCESS.code);
        System.out.println("validatePatchWithJsonFile executed successfully");
        System.out.println(response.getBody().toString());
        System.out.println(response.getBody().asString());
	}
	
	


}
