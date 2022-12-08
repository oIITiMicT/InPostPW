Scenario: when a user as a admin provides correct id, then 200 response code is returned

Given a id
And id is correct
When the user as a admin send registration request
Then the user as a admin gets 200 response
And get user data


