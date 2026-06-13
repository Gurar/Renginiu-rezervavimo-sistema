package lt.viko.eif.eventsystem.service;

import lt.viko.eif.eventsystem.db.UserCredentialRepository;
import lt.viko.eif.eventsystem.db.UserRepository;
import lt.viko.eif.eventsystem.dto.SigninRequest;
import lt.viko.eif.eventsystem.dto.SigninResponse;
import lt.viko.eif.eventsystem.dto.SignupRequest;
import lt.viko.eif.eventsystem.dto.SignupResponse;
import lt.viko.eif.eventsystem.exception.CustomException;
import lt.viko.eif.eventsystem.mapper.AuthMapper;
import org.springframework.security.core.userdetails.User;
import lt.viko.eif.eventsystem.model.UserCredential;
import lt.viko.eif.eventsystem.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class AuthenticationServicesTest {
    private AuthenticationServices authenticationServices;

    @Mock private AuthenticationManager authenticationManager;

    @Mock private UserCredentialRepository userCredentialRepository;

    @Mock private UserRepository userRepository;

    @Mock private PasswordEncoder encoder;

    @Mock private JwtUtil jwtUtil;

    @Mock
    private AuthMapper authMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authenticationServices = new AuthenticationServices(authenticationManager, userCredentialRepository, userRepository, encoder, jwtUtil, authMapper );
    }

    @Test
    void shouldSavesUserAndReturnResponse() {
        SignupRequest request = buildSignupRequest();

        when(
                userCredentialRepository .existsByUsername("admin")
        ).thenReturn(false);

        when(
                userRepository .existsByEmail("john@test.com")
        ).thenReturn(false);

        when(
                encoder.encode("1234")
        ).thenReturn("encoded-password");

        SignupResponse signupResponse = new SignupResponse();

        signupResponse.setUserId(1L);

        when(
                authMapper.toSignupResponse( any(UserCredential.class) )
        ).thenReturn(signupResponse);

        SignupResponse response = authenticationServices .registerUser(request);

        assertThat(response.getUserId()) .isEqualTo(1L);
        verify(userCredentialRepository) .save(any(UserCredential.class));
        verify(userRepository) .save(any());
    }

    @Test
    void shouldThrowsExceptionWhenUsernameExists() {
        SignupRequest request = buildSignupRequest();

        request.setUsername("admin");

        when(userCredentialRepository.existsByUsername("admin")).thenReturn(true);

        assertThatThrownBy(() -> authenticationServices.registerUser(request))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("User already exists");

        verify(userCredentialRepository, never()).save(any());
    }

    @Test
    void shouldThrowsExceptionWhenEmailExists() {
        SignupRequest request = buildSignupRequest();

        request.setUsername("admin");
        request.setEmail("john@test.com");

        when(userCredentialRepository.existsByUsername("admin")).thenReturn(false);
        when(userRepository.existsByEmail("john@test.com")).thenReturn(true);

        assertThatThrownBy(() -> authenticationServices.registerUser(request))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining("Email already exists");

        verify(userRepository, never()).save(any());

    }

    @Test
    void shouldReturnTokenWhenCredentialsAreValid() {
        SigninRequest signinRequest = new SigninRequest();

        signinRequest.setUsername("admin");
        signinRequest.setPassword("1234");

        UserDetails userDetails = new User("admin", "encoder", List.of());
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(jwtUtil.generateToken("admin")).thenReturn("jwt-token");

        UserCredential credential = new UserCredential();
        credential.setId(1L);
        credential.setUsername("admin");
        when(userCredentialRepository.findByUsername("admin")).thenReturn(Optional.of(credential));

        SigninResponse signinResponse = new SigninResponse();
        signinResponse.setUserId(1L);
        signinResponse.setToken("jwt-token");
        when(authMapper.toSigninResponse(credential, "jwt-token")).thenReturn(signinResponse);

        SigninResponse response = authenticationServices.authenticateUser(signinRequest);

        assertThat(response.getUserId()).isEqualTo(1L);
        assertThat(response.getToken()).isEqualTo("jwt-token");

    }
    private SignupRequest buildSignupRequest() {
        SignupRequest request = new SignupRequest();
        request.setUsername("admin");
        request.setPassword("1234");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john@test.com");
        return request;
    }
}
