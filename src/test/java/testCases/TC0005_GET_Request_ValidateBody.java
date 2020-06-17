package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC0005_GET_Request_ValidateBody {

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
		
		Assert.assertEquals(responseBody.contains("Hyderabad"),true);
		

	}

}
