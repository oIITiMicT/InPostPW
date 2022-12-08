package getParcelStatusUS;

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

public class GetParcelStatusSteps {
    private Long id;
    private RestTemplate restTemplate;
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseEntity<String> resp;

    private final static String URL = "http://localhost:8080/api/{id}";
    private static final String JWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW1wbGVAZ21haWwuY29tIiwiZXhwIjoxNjczMTE5MzYzfQ.kHs1vMq7B_cM7dX-rsMy3G_Djnit-mU6iDCGzlBognA";

    @Given("a id of parcel")
    public void initDataForParcel(){
        id = 1L ;
    }

    @When("the user send id of parcel")
    public void sendRequest() throws JsonProcessingException {
        restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Token", JWT);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        resp = restTemplate.exchange(
                URL, HttpMethod.GET, entity, String.class);
    }

    @Then("the user gets 200 response")
    public void checkResponseStatus(){
        Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
    }
}
