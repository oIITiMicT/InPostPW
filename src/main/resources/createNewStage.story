Scenario: when a user send a request to create new stage then 201 response code and new stage are returned

Given a description, packageId and time
When the user send create new stage request
Then the user get 201 response and new stage