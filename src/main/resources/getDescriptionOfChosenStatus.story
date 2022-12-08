Scenario: when a user provides correct id of parcel status , then 200 response code is returne

Given a id of chosen status and token
When the user send request to get a description of parcel status
Then the user gets 200 response and description of status


