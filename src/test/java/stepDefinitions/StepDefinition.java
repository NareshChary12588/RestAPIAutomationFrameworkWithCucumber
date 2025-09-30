package stepDefinitions;

import io.cucumber.java.en.Given;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	ResponseSpecification	resspec;
	TestDataBuild data=new TestDataBuild();
	RequestSpecification res;
	Response response;
static	String place_id;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    res=given().spec(requestSpecification())
	    		.body(data.addPlacePayLoad(name, language, address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI= APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec=	new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST")) {
			response=res.when().post(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("GET")) {
			response=res.when().get(resourceAPI.getResource());
		}
		
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	   assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response is {string}")
	public void in_response_is(String keyValue, String expectedValue) {
	    assertEquals(getJsonPath(response, keyValue), expectedValue);
	}
	
	@Then("Verify if place_Id created maps to {string} using {string}")
	public void verify_if_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id=	getJsonPath(response, "place_id");
		res=	given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"Get");
		String actualName=getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
		
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		res =given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id));
	}





}
