package com.git_hub.step_defenition;

import com.git_hub.utilities.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;


public class Step {
	


	private static RequestSpecification requestSpecification;
	private static String jsonString;
	private static Response response;
	private static ValidatableResponse validatableResponse;


	@Given("The base uri is initialized")
	public void the_base_uri_is_initialized() {
		RestAssured.baseURI = ConfigReader.getConfiguration("git_hub_base_uri");
	}

	@Given("Header {string} is {string}")
	public void header_is(String headerName, String headerValue) {
		requestSpecification = given().header(headerName, headerValue);
	}

	@Given("User sends GET request to the endpoint for user {string}")
	public void user_sends_GET_request_to_the_endpoint_for_user(String userName) {
		response = requestSpecification.when().log().all().get("/users/" + userName + "/repos");

	}
	@Given("User saves response for Get request")
	public void user_saves_response_for_Get_request() {
		jsonString = response.asString();
		validatableResponse = response.then().log().all();
	}


	@Given("User gets the list of repositories for user {string}")
	public void user_gets_the_list_of_repositories_for_user(String string) {
		validatableResponse = response.then().log().all();

		jsonString = response.asString();
		List<Map<String, String>> repos = JsonPath.from(jsonString).get("name");
		Assert.assertTrue(repos.size() > 0);

		List<String> repo_names = new ArrayList<String>();

		System.out.println("Repository name: " + repos.toString());

	}

	@Given("User validates status code {int}")
	public void user_validates_status_code(Integer expectedStatusCode) {
		validatableResponse.assertThat().statusCode(expectedStatusCode);
	}
	@Given("User validates message {string}")
	public void user_validates_message(String expectedMessage) {
		
		Assert.assertEquals(JsonPath.from(jsonString).get("message"), expectedMessage);
		
	}


	
}
