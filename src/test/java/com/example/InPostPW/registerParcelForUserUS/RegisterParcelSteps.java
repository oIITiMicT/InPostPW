package com.example.InPostPW.registerParcelForUserUS;

import com.example.InPostPW.dto.NewPackageFormDto;
import com.example.InPostPW.dto.RegistrationFormDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RegisterParcelSteps {
    private String shippingAddress;
    private String destinationAddress;
    private String recipient;
    private RestTemplate restTemplate;
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseEntity<JSONObject> resp;

    private final static String URL = "http://localhost:8080/api/package/create";
    private static final String JWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW1wbGVAZ21haWwuY29tIiwiZXhwIjoxNjczMTE5MzYzfQ.kHs1vMq7B_cM7dX-rsMy3G_Djnit-mU6iDCGzlBognA";

    @Given("a shippingAddress, destinationAddress and recipient")
    public void initDataForParcel(){
        shippingAddress = "someAddress";
        destinationAddress = "someDestAddress";
        recipient = "someRecipient";
    }

    @When("the user send request for creating parcel")
    public void sendRequest() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Token", JWT);
        String json = mapper.writeValueAsString(new NewPackageFormDto(shippingAddress, destinationAddress, recipient));
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        resp = restTemplate.postForEntity(URL, entity, JSONObject.class);
    }

    @Then("the user gets 201 response")
    public void checkResponseStatus(){
        Assertions.assertEquals(HttpStatus.CREATED, resp.getStatusCode());

    }
}
