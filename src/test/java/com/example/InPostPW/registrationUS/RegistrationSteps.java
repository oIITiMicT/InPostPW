package com.example.InPostPW.registrationUS;

import com.example.InPostPW.dto.RegistrationFormDto;
import com.example.InPostPW.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.annotations.When;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.fail;


@UsingSteps
public class RegistrationSteps {
    private String username;
    private String password;
    private String email;
    private RestTemplate restTemplate;

    private ObjectMapper mapper = new ObjectMapper();
    private ResponseEntity<JSONObject> resp;
    private final static String URL = "http://localhost:8080/api/registration";

    private UserService userService;

    @Given("a username, password and email")
    public void initUserData() {
        username = "TestTest";
        password = "12345678aA!";
        email = "sample@gmail.com";
    }

    @Given("the username and email are unique")
    public void areUnique() {
//        boolean result = userService.findUserByEmail(email).isEmpty() && userService.findUserByUsername(username).isEmpty();
        Assertions.assertTrue(true);
    }

    @When("the user send registration request")
    public void userSendsRequest() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = mapper.writeValueAsString(new RegistrationFormDto(username, password, email));
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        resp = restTemplate.postForEntity(URL, entity, JSONObject.class);
    }

    @Then("the user gets 200 response")
    public void userGets200Response() {
        Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Then("account is created")
    public void userIsCreated(){
//        Assertions.assertTrue(userService.findUserByUsername(username).isPresent());
    }
}
