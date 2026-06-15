package lt.viko.eif.eventsystem.steps;

import io.cucumber.java.en.Then;

import io.restassured.path.json.JsonPath;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EventSteps {
    private final SharedState state;

    public EventSteps(SharedState state) {
        this.state = state;
    }


    @Then("response should contain event title")
    public void responseShouldContainEventTitle() {
        JsonPath json = new JsonPath(state.responseBody);
        assertNotNull(json.getString("[0].title"));
    }

    @Then("response should contain event date")
    public void responseShouldContainEventDate() {
        JsonPath json = new JsonPath(state.responseBody);
        assertNotNull(json.getString("[0].eventDate"));
    }

    @Then("response should contain event address")
    public void responseShouldContainEventAddress() {
        JsonPath json = new JsonPath(state.responseBody);
        assertNotNull(json.getString("[0].address"));
    }

    @Then("response should contain event image")
    public void responseShouldContainEventImage() {
        JsonPath json = new JsonPath(state.responseBody);
        assertNotNull(json.getString("[0].image"));
    }

    @Then("response should contain event link")
    public void responseShouldContainEventLink() {
        JsonPath json = new JsonPath(state.responseBody);
        assertNotNull(json.getString("[0]._links.self.href"));
    }

    @Then("response should contain at least one event")
    public void responseShouldContainAtLeastOneEvent() {
        JsonPath json = new JsonPath(state.responseBody);
        assertTrue(json.getList("").size() > 0);
    }
}
