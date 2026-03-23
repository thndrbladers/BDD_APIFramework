package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws IOException {
		StepDefinitions sd = new StepDefinitions();

		System.out.println("Hook called");

		if (StepDefinitions.placeID == null) {
			sd.add_place_payload_with("Rahul", "Hindi", "Heaven");
			sd.user_calls_with_http_request("addPlaceAPI", "POST");
			sd.verify_place_got_added_using_using("Rahul", "getPlaceAPI");
		}

	}

}
