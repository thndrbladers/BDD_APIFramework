package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

import org.junit.Assert;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {

	public StepDefinitions() {
		counter = counter + 1;
		System.out.println("No. of time class was initialized :" + counter);
	}

	public static int counter = 0;

	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	TestDataBuild testData = new TestDataBuild();
	Response response;
	static String placeID;

	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		requestSpec = given().spec(requestSpecification()).body(testData.addPlaceData(name, language, address));
		responseSpec = responseSpecification();

	}

	@When("User calls {string} with HTTP {string} request")
	public void user_calls_with_http_request(String resource, String method) {

		APIResources resourceObj = APIResources.valueOf(resource);
		String getResource = resourceObj.getResource();
		if (method.equalsIgnoreCase("POST")) {
			// response = requestSpec.when().post(getResource).then().extract().response();
			response = requestSpec.when().post(getResource).then().spec(responseSpec).extract().response();
		} else if (method.equalsIgnoreCase("GET")) {
			// response = requestSpec.when().get(getResource).then().extract().response();
			response = requestSpec.when().get(getResource).then().spec(responseSpec).extract().response();
		} else if (method.equalsIgnoreCase("DELETE")) {
			response = requestSpec.when().delete(getResource).then().spec(responseSpec).extract().response();
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

	@Then("Verify place got added using {string} using {string}")
	public void verify_place_got_added_using_using(String expName, String resource) throws IOException {
		// this is to extract the place id from post response
		placeID = getJsonPath(response, "place_id");

		// updated the request specification for GET request as it doesn't accept the
		// same body as POST. (Overriding)
		requestSpec = given().spec(requestSpecification()).queryParam("place_id", placeID);

		// Calling already available "reusable method" to fetch the response.
		user_calls_with_http_request(resource, "GET");
		Assert.assertEquals(getJsonPath(response, "name"), expName);

	}

	@Given("Delete Place API Payload")
	public void delete_place_api_payload() throws IOException {
		requestSpec = given().spec(requestSpecification()).body(testData.deletePlaceData(placeID));
		responseSpec = responseSpecification();

	}

}
