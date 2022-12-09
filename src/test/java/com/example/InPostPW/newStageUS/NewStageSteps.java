package com.example.InPostPW.newStageUS;

import com.example.InPostPW.dto.NewStageFormDto;
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

import java.time.LocalDateTime;
import java.util.Date;

@UsingSteps
public class NewStageSteps {

    private String description;
    private Long packageId;
    private String token;
    private Date date;
    private RestTemplate restTemplate;

    private ObjectMapper mapper = new ObjectMapper();
    private ResponseEntity<JSONObject> resp;
    private final static String URL = "http://localhost:8080/api/stage/create";

    @Given("a description, packageId and time")
    public void initUserData() {
        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsInJvbGUiOlsiZ2V0IHVzZXIgaW5mbyJdLCJleHAiOjE3NjA1Mzg5NzR9.YmK8wlNtIfdaIZ1u1UbHAv3zfWS7IAKr9ovsO12hN1Y";
        description = "description";
        packageId = 1000L;
        date = new Date();
    }

    @When("the user send create new stage request")
    public void userSendsRequest() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token", token);
        String json = mapper.writeValueAsString(new NewStageFormDto(description, packageId, date));
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        resp = restTemplate.postForEntity(URL, entity, JSONObject.class);
    }

    @Then("the user get 201 response and new stage")
    public void userGets200Response() {
        Assertions.assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        Assertions.assertNotNull(resp.getBody());
    }
}
