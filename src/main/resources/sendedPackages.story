Scenario: when a user send a request to get sended packages then 200 response code and sended packages id are returned

Given a user id and token
When the user send get sended packages request
Then the user get 200 response and list of sended packages