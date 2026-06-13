package lt.viko.eif.eventsystem.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lt.viko.eif.eventsystem.dto.SigninRequest;
import lt.viko.eif.eventsystem.dto.SigninResponse;
import lt.viko.eif.eventsystem.dto.SignupRequest;
import lt.viko.eif.eventsystem.dto.SignupResponse;
import lt.viko.eif.eventsystem.service.AuthenticationServices;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Map;

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
      return Response.status(Response.Status.CREATED).
              entity(response).build();
    }

    @POST
    @Path("/signin")
    public Response authenticateUser(SigninRequest signinRequest) {
        try {
            SigninResponse response = authenticationServices.authenticateUser(signinRequest);
            return Response.status(Response.Status.CREATED).
                    entity(response).build();
        } catch (AuthenticationException e) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(
                            Map.of( "message", "Invalid username or password" )
                    )
                    .build();

        }

    }
}
