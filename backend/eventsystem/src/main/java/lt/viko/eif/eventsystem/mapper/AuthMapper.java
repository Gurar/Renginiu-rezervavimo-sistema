package lt.viko.eif.eventsystem.mapper;

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
                        "href", "/api/v1/auth/signup",
                        "method", "POST"
                ),
                Map.of(
                        "rel", "signin",
                        "href", "/api/v1/auth/signin",
                        "method", "POST"
                )
        );

        response.setLinks(links);

        return response;
    }
}
