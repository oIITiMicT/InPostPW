package com.example.InPostPW.loginUS;

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

@UsingSteps
public class LoginSteps {
    private String email;
    private String password;
    private RestTemplate restTemplate;

    private ObjectMapper mapper = new ObjectMapper();
    private ResponseEntity<JSONObject> resp;
    private final static String URL = "http://localhost:8080/api/login";

    @Given("a email, password")
    public void initUserData() {
        email = "test@gmail.com";
        password = "passwordA1!";
    }

    @When("the user send login request")
    public void userSendsRequest() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = mapper.writeValueAsString(new UserLogin(email, password));
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        resp = restTemplate.postForEntity(URL, entity, JSONObject.class);
    }

    @Then("the user get 200 response")
    public void userGets200Response() {
        Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Then("the user get access and refresh tokens")
    public void userGetsTokens() {
        Assertions.assertNotNull(resp.getHeaders().get("token"));
    }
}
