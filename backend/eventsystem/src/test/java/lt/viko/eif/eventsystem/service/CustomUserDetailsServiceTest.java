package lt.viko.eif.eventsystem.service;

import lt.viko.eif.eventsystem.db.UserCredentialRepository;
import lt.viko.eif.eventsystem.model.UserCredential;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomUserDetailsServiceTest {

    @Mock
    private UserCredentialRepository userCredentialRepository;

    private CustomUserDetailsService customUserDetailsService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customUserDetailsService = new CustomUserDetailsService(userCredentialRepository);
    }

    @Test
    void shouldLoadUserByUsername() {
        UserCredential userCredential = new UserCredential();

        userCredential.setUsername("admin");
        userCredential.setPassword("password");

        when(userCredentialRepository.findByUsername("admin")).thenReturn(Optional.of(userCredential));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("admin");

        assertEquals("admin", userDetails.getUsername());
    }
}
