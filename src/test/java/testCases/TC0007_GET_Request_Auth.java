package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC0007_GET_Request_Auth {

	@Test
	void getWeatherDetails() {

		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

		// Baisc Authentication

		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();

		authScheme.setUserName("ToolsQA");
		authScheme.setPassword("TestPassword");

		RestAssured.authentication = authScheme;

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/");

		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response is: " + responseBody);

		// status code validation
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		// status line verification
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

}
