package com.example.InPostPW.expectedPackagesUS;

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
public class ExpectedPackagesSteps {
    private String token;
    private Long id;
    private RestTemplate restTemplate;
    private ResponseEntity<?> resp;
    private ObjectMapper mapper = new ObjectMapper();

    @Given("a user id and token")
    public void initUserData() {
        id = 1000L;
        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsInJvbGUiOlsiZ2V0IHVzZXIgaW5mbyJdLCJleHAiOjE3NjA1Mzg5NzR9.YmK8wlNtIfdaIZ1u1UbHAv3zfWS7IAKr9ovsO12hN1Y";
    }

    @When("the user send get expected packages request")
    public void userSendedRequest() throws JsonProcessingException {
        String URL = "http://localhost:8080/api/user/" + id.toString() + "/packages/expected";
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        resp = restTemplate.exchange(
                URL, HttpMethod.GET, entity, JSONObject[].class);
    }

    @Then("the user get 200 response and list of expected packages")
    public void checkUserResponse() throws JsonProcessingException {
        Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
        Assertions.assertNotNull(resp.getBody());
    }
}
