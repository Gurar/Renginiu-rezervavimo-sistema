package lt.viko.eif.eventsystem.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lt.viko.eif.eventsystem.dto.SigninRequest;



import static org.hamcrest.Matchers.notNullValue;

public class SigninSteps {

    private final SharedState state;

    public SigninSteps(SharedState state) {
        this.state = state;
    }



    @Given("user is authenticated")
    public void userIsAuthenticated() {
        SigninRequest signin = new SigninRequest();
        signin.setUsername("admin");
        signin.setPassword("1234");

        Response signinResponse = RestAssured.given()
                .contentType("application/json")
                .body(signin)
                .post("/api/auth/signin");

        state.token = signinResponse.jsonPath().getString("token");
    }


    @Then("response should contain token")
    public void responseShouldContainToken() {
        state.response.then().body("token", notNullValue());
    }

    @Then("response should contain username")
    public void responseShouldContainUsername() {
        state.response.then().body("username", notNullValue());
    }

    @Then("response should contain self link")
    public void responseShouldContainSelfLink() {
        state.response.then().body("links.find { it.rel == 'self' }.href", notNullValue());
    }

    @Then("response should contain reservations link")
    public void responseShouldContainReservationsLink() {
        state.response.then().body("links.find { it.rel == 'reservations' }.href", notNullValue());
    }

}

