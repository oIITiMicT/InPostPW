Scenario: when a user provides correct username and password then 200 response code and tokens are returned

Given a username, password
When the user send login request
Then the user get 200 response
And the user get access and refresh tokens