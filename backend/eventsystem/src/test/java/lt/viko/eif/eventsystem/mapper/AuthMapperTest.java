package lt.viko.eif.eventsystem.mapper;

import lt.viko.eif.eventsystem.dto.SigninResponse;
import lt.viko.eif.eventsystem.dto.SignupResponse;
import lt.viko.eif.eventsystem.model.UserCredential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthMapperTest {
    private AuthMapper authMapper;
    private UserCredential userCredential;

    @BeforeEach
    void setUp() {
        authMapper = new AuthMapper();

        userCredential = new UserCredential();
        userCredential.setId(1l);
        userCredential.setUsername("admin");
    }

    @Test
    void shouldSetUserIdOnSignupResponse() {
        SignupResponse response = authMapper.toSignupResponse(userCredential);

        assertThat(response.getUserId()).isEqualTo(1L);
    }

    @Test
    void shouldContainTwoLinksOnSignupResponse() {
        SignupResponse response = authMapper.toSignupResponse(userCredential);

        assertThat(response.getLinks()).hasSize(2);
    }

    @Test
    void  shouldContainSelfLinkOnSignupResponse() {
        SignupResponse response = authMapper.toSignupResponse(userCredential);
        Map<String, String> selfLink = response.getLinks().get(0);

        assertThat(selfLink)
                .containsEntry("rel", "self")
                .containsEntry("href", "/api/auth/signup")
                .containsEntry("method", "POST");
    }

    @Test
    void shouldContainSigninLinkOnSignupResponse() {
        SignupResponse response = authMapper.toSignupResponse(userCredential);
        Map<String, String> signinLink = response.getLinks().get(1);

        assertThat(signinLink)
                .containsEntry("rel", "signin")
                .containsEntry("href", "/api/auth/signin")
                .containsEntry("method", "POST");
    }


    @Test
    void shouldSetAllFieldsOnSigninResponse() {
        SigninResponse response = authMapper.toSigninResponse(userCredential, "jwt-token-123");

        assertThat(response.getUserId()).isEqualTo(1L);
        assertThat(response.getUsername()).isEqualTo("admin");
        assertThat(response.getToken()).isEqualTo("jwt-token-123");
    }

    @Test
    void shouldContainTwoLinksOnSigninResponseto() {
        SigninResponse response = authMapper.toSigninResponse(userCredential, "token");

        assertThat(response.getLinks()).hasSize(2);
    }

    @Test
    void shouldContainUserIdInSelfLinkOnSigninResponse() {
        SigninResponse response = authMapper.toSigninResponse(userCredential, "token");
        Map<String, String> selfLink = response.getLinks().get(0);

        assertThat(selfLink)
                .containsEntry("rel", "self")
                .containsEntry("href", "/api/users/1")
                .containsEntry("method", "Get");
    }

    @Test
    void shouldContainReservationsLinkOnSigninResponse() {
        SigninResponse response = authMapper.toSigninResponse(userCredential, "token");
        Map<String, String> reservationsLink = response.getLinks().get(1);

        assertThat(reservationsLink)
                .containsEntry("rel", "reservations")
                .containsEntry("href", "/api/reservations")
                .containsEntry("method", "Get");
    }
}
