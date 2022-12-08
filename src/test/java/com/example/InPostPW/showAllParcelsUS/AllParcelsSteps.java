package com.example.InPostPW.showAllParcelsUS;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class AllParcelsSteps {
    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> result;
    private final static String URL = "http://localhost:8080/api/package/list";
    private static final String JWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJyb2xlIjpbImdldCB1c2VyIGluZm8iXSwiZXhwIjoxNzYwNTQyMjE4fQ.SonKSHvGe0YAaJ2Q4-3aOginepNwJ_lgbVfPEb7dsc4";


    @Given("nothing")
    public void givenNothing(){}

    @When("the admin sends request for all parcels")
    public void adminSendsReq(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Token", JWT);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        result = restTemplate.exchange(
                URL, HttpMethod.GET, requestEntity, String.class);
    }

    @Then("the admin gets list of parcels")
    public void adminGetsList(){
        Assertions.assertTrue(result.hasBody());
    }

    @Then("response code is 200")
    public void respCodeIs200(){
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
