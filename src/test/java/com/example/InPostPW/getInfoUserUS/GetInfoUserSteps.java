package com.example.InPostPW.getInfoUserUS;
import com.example.InPostPW.dto.NewPackageFormDto;
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
public class GetInfoUserSteps {
    private Long id;
    private RestTemplate restTemplate;

    private ObjectMapper mapper = new ObjectMapper();
    private ResponseEntity<?> resp;
    private String URL = "http://localhost:8080/api/user/";

    private final static String JWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJyb2xlIjpbImdldCB1c2VyIGluZm8iXSwiZXhwIjoxNzYwNTQyMjE4fQ.SonKSHvGe0YAaJ2Q4-3aOginepNwJ_lgbVfPEb7dsc4";
    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsInJvbGUiOlsiZ2V0IHVzZXIgaW5mbyJdLCJleHAiOjE3NjA1Mzg5NzR9.YmK8wlNtIfdaIZ1u1UbHAv3zfWS7IAKr9ovsO12hN1Y";
    private UserService userService;
    @Given("a id")
    public void initUserData() {
        id = 1000L;
    }


    @When("the user as a admin send id of user")
    public void userSendsRequest() throws JsonProcessingException {
        restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Token", JWT);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        URL ="http://localhost:8080/api/user/" + id.toString();
        resp = restTemplate.exchange(
                URL, HttpMethod.GET, entity, JSONObject.class);
    }

    @Then("the user as a admin gets 200 response")
    public void userGets200Response() {

        Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
    }


}
