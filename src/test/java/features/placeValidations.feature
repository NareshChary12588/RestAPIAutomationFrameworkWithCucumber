Feature: Validating Place APIs
@Addplace  @Regression
Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response is "OK"
	And "scope" in response is "APP"
	And Verify if place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:

	|name	|language	|address				|	
	|AAhouse|English		|World cross center	|
	|BBhouse|Spanish		|Sea cross center	|
	
	
@DeletePlace @Regression
Scenario: Verify if Delete place functionality is working
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response is "OK"