package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC0006_GET_Request_NodeValidateBody {

	@Test
	void getWeatherDetails() {

		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response object
		Response response = httpRequest.request(Method.GET, "/Hyderabad");
		
		//capture all nodes value and compare it
		
		JsonPath jsonpath= response.jsonPath();
		
		//System.out.println(jsonpath.get("City"));
		Assert.assertEquals(jsonpath.get("City"), "Hyderabad");
		Assert.assertEquals(jsonpath.get("Temperature"), "38.01 Degree celsius");
		Assert.assertEquals(jsonpath.get("Humidity"), "31 Percent");
		Assert.assertEquals(jsonpath.get("WeatherDescription"), "scattered clouds");
		Assert.assertEquals(jsonpath.get("WindSpeed"), "0.5 Km per hour");
		Assert.assertEquals(jsonpath.get("WindDirectionDegree"), "10 Degree");
		//Assert.assertEquals(jsonpath.get("City"), "Hyderabad");
		
		

	}

}
