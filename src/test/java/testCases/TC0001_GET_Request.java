package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC0001_GET_Request {

	@Test
	void getWeatherDetails() {

		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/Hyderabad");
		
		//print response in console window
		String responseBody= response.getBody().asString();
		System.out.println("Response is: "+ responseBody);
		
		//status code validation
		int statusCode= response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
		  //status line verification
		String statusLine= response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		//RestAssured.baseURI
		

	}

}
