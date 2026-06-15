package lt.viko.eif.eventsystem.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import lt.viko.eif.eventsystem.dto.SignupRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class CommonSteps {
    private final SharedState state;

    public CommonSteps(SharedState state) {
        this.state = state;
    }

    @Before
    public void setUp() {
        RestAssured.reset();
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        state.token = null;
        state.eventId = null;
        state.response = null;
        state.responseBody = null;
        state.request = new SignupRequest();
    }

    @Given("registered user exists in the system")
    public void registeredUserExistsInTheSystem() {
        SignupRequest signup = new SignupRequest();
        signup.setUsername("admin");
        signup.setPassword("1234");
        signup.setEmail("jonas@test.com");
        signup.setFirstName("Jonas");
        signup.setLastName("Jonaitis");

        RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json")
                .body(signup)
                .post("/api/auth/signup");
    }

    @Given("user provides username {string}")
    public void userProvidesUsername(String username) {
        state.request.setUsername(username);
    }

    @Given("user provides password {string}")
    public void userProvidesPassword(String password) {
        state.request.setPassword(password);
    }

    @When("user sends POST request to {string}")
    public void userSendsRequest(String endpoint) {
        var requestSpec = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType("application/json");

        if (state.token != null)
            requestSpec = requestSpec.header("Authorization", "Bearer " + state.token);

        if (state.eventId != null)
            requestSpec = requestSpec.body("{\"eventId\": \"" + state.eventId + "\"}");
        else
            requestSpec = requestSpec.body(state.request);

        state.response = requestSpec.post(endpoint);
    }

    @When("user sends GET request to {string}")
    public void userSendsGetRequest(String endpoint) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080" + endpoint))
                .GET();

        if (state.token != null)
            requestBuilder.header("Authorization", "Bearer " + state.token);

        HttpResponse<String> httpResponse = client.send(requestBuilder.build(),
                HttpResponse.BodyHandlers.ofString());

        state.statusCode = httpResponse.statusCode();
        state.responseBody = httpResponse.body();
    }

    @Then("response status should be {int}")
    public void responseStatusShouldBe(int status) {
        if (state.response != null)
            state.response.then().statusCode(status);
        else
            assertEquals(status, state.statusCode);
    }

    @Then("response should contain message {string}")
    public void responseShouldContainMessage(String message) {
        state.response.then().body("message", equalTo(message));
    }

    @Then("response should contain validation errors")
    public void responseShouldContainValidationErrors() {
        state.response.then().body("errors", notNullValue());
    }
}
