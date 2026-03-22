package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import static org.junit.Assert.*;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinitions extends Utils {

	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	TestDataBuild testData;
	Response response;

	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		testData = new TestDataBuild();
		requestSpec = given().spec(requestSpecification()).body(testData.addPlaceData(name, language, address));
		responseSpec = responseSpecification();

	}

	@When("User calls {string} with HTTP {string} request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources resourceObj = APIResources.valueOf(resource);
		String getResource = resourceObj.getResource();
		if (method.equalsIgnoreCase("POST")) {
			response = requestSpec.when().post(getResource).then().spec(responseSpec).extract().response();
		} else if (method.equalsIgnoreCase("GET")) {
			response = requestSpec.when().get(getResource).then().spec(responseSpec).extract().response();
		}

	}

	@Then("The API should return Status {int}")
	public void the_api_should_return_status(int statusCode) {
		assertEquals(response.getStatusCode(), statusCode);

	}

	@Then("Response body {string} should be {string}")
	public void response_body_should_be(String keyValue, String expectedValue) {

		assertEquals(response.jsonPath().getString(keyValue), expectedValue);

	}

}
