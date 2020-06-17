package dataDrivenTestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC0001_POST_DataDrivenTest {

	@Test
	void postCustomerDetails() {

		// Specify base URI
		RestAssured.baseURI = "http://dummy.restapiexamples.com/api/v1";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Rahul0015");
		requestParams.put("salary", 123456);
		requestParams.put("age", 32);
		// requestParams.put("Password","Sahu0013@123");
		// requestParams.put("Email","dummy0013@gmail.com");

		// System.out.println(requestParams);

		httpRequest.body(requestParams.toJSONString());
		httpRequest.header("Content-Type", "application/json");

		// Response object
		Response response = httpRequest.request(Method.POST, "/create");

		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response is: " + responseBody);

		// status code validation
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

		// status message validation
		String statusMessage = response.jsonPath().getString("SuccessCode");
		Assert.assertEquals(statusMessage, "OPERATION_SUCCESS");

		// verify body
		Assert.assertEquals(responseBody.contains("Rahul0015"), true);

	}

}
