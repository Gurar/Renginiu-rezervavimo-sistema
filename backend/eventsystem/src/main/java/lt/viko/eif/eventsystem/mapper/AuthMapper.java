package lt.viko.eif.eventsystem.mapper;

import lt.viko.eif.eventsystem.dto.SigninResponse;
import lt.viko.eif.eventsystem.model.UserCredential;
import lt.viko.eif.eventsystem.dto.SignupResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AuthMapper {
    public SignupResponse toSignupResponse(UserCredential userCredential) {
        SignupResponse response = new SignupResponse();

        response.setUserId(userCredential.getId());

        List<Map<String, String>> links = List.of(
                Map.of(
                        "rel", "self",
                        "href", "/api/auth/signup",
                        "method", "POST"


                ),
                Map.of(
                        "rel", "signin",
                        "href", "/api/auth/signin",
                        "method", "POST"

                )
        );

        response.setLinks(links);

        return response;
    }

    public SigninResponse toSigninResponse(UserCredential userCredential, String token)  {
        SigninResponse response = new SigninResponse();

        response.setUserId(userCredential.getId());

        response.setUsername(userCredential.getUsername());

        response.setToken(token);

        List<Map<String, String>> links = List.of(
                Map.of(
                        "rel", "self",
                        "href", "/api/users/" + userCredential.getId(),
                        "method", "Get"
                ),
                Map.of(
                        "rel", "reservations",
                        "href", "/api/reservations",
                        "method", "Get"
                )
        );

        response.setLinks(links);

        return response;

    }
}
