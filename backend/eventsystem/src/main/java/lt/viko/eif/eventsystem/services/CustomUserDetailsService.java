package lt.viko.eif.eventsystem.services;

import lt.viko.eif.eventsystem.db.UserCredentialRepository;
import lt.viko.eif.eventsystem.model.UserCredential;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserCredentialRepository userCredentialRepository;

    public CustomUserDetailsService(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByUsername(username).orElseThrow(
                () ->new UsernameNotFoundException("User Not Found witch username: " + username));

        return new org.springframework.security.core.userdetails.User(
                userCredential.getUsername(),
                userCredential.getPassword(),
                Collections.emptyList()
        );
    }
}
