Scenario: when a user send a request to get expected packages then 200 response code and expected packages id are returned

Given a user id and token
When the user send get expected packages request
Then the user get 200 response and list of expected packages