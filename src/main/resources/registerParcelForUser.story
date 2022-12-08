Scenario: when a user provides shippingAddress, destinationAddress, recipient, then 201 response code is returned

Given a shippingAddress, destinationAddress and recipient
When the user send request for creating parcel
Then the user gets 201 response
