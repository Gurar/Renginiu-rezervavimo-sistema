package lt.viko.eif.eventsystem.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.RestAssured;


import static org.hamcrest.Matchers.notNullValue;

public class SignupSteps {

    private final SharedState state;


    public SignupSteps(SharedState state) {
        this.state = state;
    }


    @Given( "user provides email {string}" )
    public void userProvidesEmail( String email ) {
        state.request.setEmail(email);
    }

    @Given("user provides first name {string}")
    public void userProvidesFirstName(String firstName) {
        state.request.setFirstName(firstName);
    }

    @Given ("user provides last name {string}")
    public void userProvidesLastName(String lastName) {
        state.request.setLastName(lastName);
    }


    @Given("user already exists in the system")
    public void userAlreadyExistsInSystem() {
        RestAssured.given()
                .contentType("application/json")
                .body(state.request)
                .post("/api/auth/signup");
    }


    @Then("response should contain userId")
    public void responseShouldContainUserId(){
        state.response.then().body("userId", notNullValue());
    }

    @Then("response should contain signup link")
    public void responseShouldContainSignupLink() {
        state.response.then().body("links.find { it.rel == 'self' }.href", notNullValue());
    }

    @Then("response should contain signin link")
    public void responseShouldContainSigninLink() {
        state.response.then().body("links.find { it.rel == 'signin' }.href", notNullValue());
    }

    @Then("response content type should be {string}")
    public void responseContentTypeShouldBe(String contentType) {
        state.response.then().contentType(contentType);
    }
}
