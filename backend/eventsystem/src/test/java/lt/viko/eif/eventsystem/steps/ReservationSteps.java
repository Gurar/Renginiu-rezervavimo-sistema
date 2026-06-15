package lt.viko.eif.eventsystem.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.notNullValue;

public class ReservationSteps {
    private final SharedState state;

    public ReservationSteps(SharedState state) {
        this.state = state;
    }

    @Given("user provides event id {string}")
    public void userProvidesEventId(String eventId) {
        state.eventId = eventId;
    }

    @When("user sends POST request to {string} without token")
    public void userSendsPostRequestWithoutToken(String endpoint) {
        state.response = RestAssured.given()
                .contentType("application/json")
                .body("{\"eventId\": \"" + state.eventId + "\"}")
                .post(endpoint);
    }

    @Then("response should contain reservationId")
    public void responseShouldContainReservationId() {
        state.response.then().body("reservationId", notNullValue());
    }

    @Then("response should contain reservation self link")
    public void responseShouldContainReservationSelfLink() {
        state.response.then().body("links.find { it.rel == 'self' }.href", notNullValue());
    }

    @Then("response should contain reservation cancel link")
    public void responseShouldContainReservationCancelLink() {
        state.response.then().body("links.find { it.rel == 'cancel' }.href", notNullValue());
    }
}
