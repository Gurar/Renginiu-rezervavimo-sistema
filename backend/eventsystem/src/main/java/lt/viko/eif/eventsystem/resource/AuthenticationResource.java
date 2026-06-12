package lt.viko.eif.eventsystem.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lt.viko.eif.eventsystem.dto.SignupRequest;
import lt.viko.eif.eventsystem.model.UserCredential;
import lt.viko.eif.eventsystem.dto.SignupResponse;
import lt.viko.eif.eventsystem.services.AuthenticationServices;
import org.springframework.stereotype.Component;

@Path("/api/auth")
@Component
public class AuthenticationResource {
    private final AuthenticationServices authenticationServices;

    public AuthenticationResource(AuthenticationServices authenticationServices) {
        this.authenticationServices = authenticationServices;
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(SignupRequest signupRequest) {
      SignupResponse response = authenticationServices.registerUser(signupRequest);
      return Response.ok(response).build();
    }
}
