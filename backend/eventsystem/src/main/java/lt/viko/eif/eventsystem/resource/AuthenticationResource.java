package lt.viko.eif.eventsystem.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lt.viko.eif.eventsystem.dto.*;
import lt.viko.eif.eventsystem.service.AuthenticationServices;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.util.Map;

@Path("/auth")
@Component
public class AuthenticationResource {
    private final AuthenticationServices authenticationServices;

    public AuthenticationResource(AuthenticationServices authenticationServices) {
        this.authenticationServices = authenticationServices;
    }

    @GET
    public Response auth() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        AuthResponse response = new AuthResponse();

        response.setUsername(username);

        return Response.ok(response).build();
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(@Valid SignupRequest signupRequest) {
      SignupResponse response = authenticationServices.registerUser(signupRequest);
      return Response.status(Response.Status.CREATED).
              entity(response).build();
    }

    @POST
    @Path("/signin")
    public Response authenticateUser(SigninRequest signinRequest) {
        try {
            SigninResponse response = authenticationServices.authenticateUser(signinRequest);
            return Response.ok().entity(response).build();
        } catch (AuthenticationException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(
                            Map.of( "message", "Invalid username or password" )
                    )
                    .build();

        }

    }
}
