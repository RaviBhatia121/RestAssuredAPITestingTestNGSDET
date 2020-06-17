package dataDrivenTestCases;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC0002_POST_DataDrivenTest {

	@Test(dataProvider="empDataProvider")
	void postCustomerDetails(String efirstname, String elastname, String eusername, String epassword, String eemail) {

		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", efirstname);
		requestParams.put("LastName", elastname);
		requestParams.put("UserName", eusername);
		requestParams.put("Password", epassword);
		requestParams.put("Email", eemail);

		// System.out.println(requestParams);

		httpRequest.body(requestParams.toJSONString());
		httpRequest.header("Content-Type", "application/json");

		// Response object
		Response response = httpRequest.request(Method.POST, "/register");

		// print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response is: " + responseBody);

		// status code validation
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

		// status message validation
		String statusMessage = response.jsonPath().getString("SuccessCode");
		Assert.assertEquals(statusMessage, "OPERATION_SUCCESS");
		
		/*Assert.assertEquals(responseBody.contains(efirstname), true);
		Assert.assertEquals(responseBody.contains(elastname), true);
		Assert.assertEquals(responseBody.contains(eusername), true);
		Assert.assertEquals(responseBody.contains(epassword), true);
		Assert.assertEquals(responseBody.contains(eemail), true);
		*/

	}

	@DataProvider(name = "empDataProvider")
	String[][] getEmpData() throws IOException {
		
		String path = System.getProperty("user.dir")+"/src/test/java/dataDrivenTestCases/empdata.xlsx";
		int row = XLUtils.getRowCount(path, "Sheet1");
		int countclm = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String emp[][]= new String[row][countclm];
		
		for(int i=1; i<=row;i++) {
			for(int j=0; j<countclm; j++) {
				emp[i-1][j]= XLUtils.getCellData(path, "Sheet1", i, j);
				//System.out.println(emp[i-1][j]);
			}
		}
				
		//String emp[][] = { { "Rahul0018", "Sharma0018", "RSharma0018", "Sahu0018@123", "dummy0018@gmail.com" }, { "Rahul0019", "Sharma0019", "RSharma0019", "Sahu0019@123", "dummy0019@gmail.com" }, { "Rahul0020", "Sharma0020", "RSharma0020", "Sahu0020@123", "dummy0020@gmail.com" }};
		return (emp);
	}

}
