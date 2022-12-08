package com.example.InPostPW.sendedPackagesUS;

import com.example.InPostPW.dto.UserLogin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.annotations.When;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@UsingSteps
public class SendedPackagesSteps {
    private String token;
    private Long id;
    private RestTemplate restTemplate;
    private ResponseEntity<JSONObject> resp;
    private ObjectMapper mapper = new ObjectMapper();

    @Given("a user id and token")
    public void initUserData() {
        id = 1000L;
        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsInJvbGUiOltdLCJleHAiOjE3NjA1Mzc0OTB9.BKZQ9BGNOWeW57ngPHkm1rarYgXTGIlFdE-U-wlk-6E";
    }

    @When("the user send get sended packages request")
    public void userSEndedREquest() throws JsonProcessingException {
        String URL = "http://localhost:8080/api/user/" + id.toString() + "/packages/sent";
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        resp = restTemplate.exchange(
                URL, HttpMethod.GET, entity, JSONObject.class);
    }

    @Then("the user get 200 response and list of sended packages")
    public void checkUserResponse() {
        Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
    }
}
