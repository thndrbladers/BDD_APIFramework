package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinitions extends Utils {

	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	TestDataBuild testData;
	Response response;

	@Given("Add place Payload")
	public void add_place_payload() {
		testData = new TestDataBuild();
		requestSpec = given().spec(requestSpecification()).body(testData.addPlaceData());
		responseSpec = responseSpecification();

	}

	@When("User calls {string} with HTTP Post request")
	public void user_calls_with_http_post_request(String string) {
		response = requestSpec.when().post("maps/api/place/add/json").then().spec(responseSpec).extract().response();

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
