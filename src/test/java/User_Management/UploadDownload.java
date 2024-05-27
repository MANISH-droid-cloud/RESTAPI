package User_Management;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class UploadDownload {
	
	@Test
	public void FileUploadExample() {
		
			File file = new File("path/to/file.txt");
			
			Response response = given()
					.multiPart("file", file)
					.when()
					.post("https://example.com/upload");
			System.out.println(response.getStatusCode());
			
		
	}

}
