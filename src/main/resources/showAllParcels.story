Scenario: when a user provides unique username, password and unique email, then 200 response code is returned

Given nothing
When the admin sends request for all parcels
Then the admin gets list of parcels
And response code is 200