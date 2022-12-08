Scenario: when a user provides unique username, password and unique email, then 200 response code is returned

Given a username, password and email
And  the username and email are unique
When the user send registration request
Then the user gets 200 response
And  account is created
