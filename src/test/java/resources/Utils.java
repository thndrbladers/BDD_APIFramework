package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public RequestSpecification request;
	public ResponseSpecification response;

	public RequestSpecification requestSpecification() {

		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();

		return request;

	}

	public ResponseSpecification responseSpecification() {
		ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return response;

	}

}
