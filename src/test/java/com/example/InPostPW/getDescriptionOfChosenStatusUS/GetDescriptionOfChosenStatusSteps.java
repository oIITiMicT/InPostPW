package com.example.InPostPW.getDescriptionOfChosenStatusUS;

import com.example.InPostPW.dto.NewPackageFormDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class GetDescriptionOfChosenStatusSteps {
    private Long id;
    private RestTemplate restTemplate;
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseEntity<?> resp;
    private String token;
    private static final String JWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW1wbGVAZ21haWwuY29tIiwiZXhwIjoxNjczMTE5MzYzfQ.kHs1vMq7B_cM7dX-rsMy3G_Djnit-mU6iDCGzlBognA";

    @Given("a id of chosen status and token")
    public void initDataForParcel(){
        id = 1000L;
        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsInJvbGUiOlsiZ2V0IHVzZXIgaW5mbyJdLCJleHAiOjE3NjA1Mzg5NzR9.YmK8wlNtIfdaIZ1u1UbHAv3zfWS7IAKr9ovsO12hN1Y";
    }

    @When("the user send request to get a description of parcel status")
    public void sendRequest() throws JsonProcessingException {
        String URL = "http://localhost:8080/api/stage/" + id.toString();
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        resp = restTemplate.exchange(
                URL, HttpMethod.GET, entity, JSONObject.class);
    }

    @Then("the user gets 200 response and description of status")
    public void checkResponseStatus() {
        Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
        Assertions.assertNotNull(resp.getBody());
    }
}
