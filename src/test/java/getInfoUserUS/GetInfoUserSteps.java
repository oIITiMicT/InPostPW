package getInfoUserUS;
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
    private ResponseEntity<JSONObject> resp;
    private final static String URL = "http://localhost:8080/api/user/{id}";

    private final static String JWT = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW1wbGVAZ21haWwuY29tIiwicm9sZSI6W10sImV4cCI6MTY3MDUyODI2M30.jTCM52MO2rXC1WzhVkTrN7KQzCmvXYaUv1TtisSHLXI";
    private UserService userService;
    @Given("a id")
    public void initUserData() {
        id = 1L ;
    }

    @Given("id is correct")
    public void isCorrect() {
    }

    @When("the user as a admin send registration request")
    public void userSendsRequest() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Token", JWT);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        resp = restTemplate.postForEntity(URL, entity, JSONObject.class);
    }

    @Then("the user as a admin gets 200 response")
    public void userGets200Response() {
        Assertions.assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Then("get user data")
    public void getUserData(){

        Assertions.assertTrue(resp.hasBody());
    }
}
