package lt.viko.eif.eventsystem.steps;

import io.restassured.response.Response;
import lt.viko.eif.eventsystem.dto.SignupRequest;

public class SharedState {
    public SignupRequest request = new SignupRequest();
    public Response response;
    public int statusCode;
    public String responseBody;
    public String token;
    public String eventId;
}
