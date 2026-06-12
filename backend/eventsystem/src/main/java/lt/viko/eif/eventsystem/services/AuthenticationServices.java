package lt.viko.eif.eventsystem.services;

import lt.viko.eif.eventsystem.db.UserCredentialRepository;
import lt.viko.eif.eventsystem.db.UserRepository;
import lt.viko.eif.eventsystem.dto.SignupRequest;
import lt.viko.eif.eventsystem.exception.CustomException;
import lt.viko.eif.eventsystem.mapper.AuthMapper;
import lt.viko.eif.eventsystem.model.User;
import lt.viko.eif.eventsystem.model.UserCredential;
import lt.viko.eif.eventsystem.dto.SignupResponse;
import lt.viko.eif.eventsystem.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {
    private final AuthenticationManager authenticationManager;

    private final UserCredentialRepository userCredentialRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtUtil jwtUtil;

    private final AuthMapper authMapper;

    public AuthenticationServices(AuthenticationManager authenticationManager, UserCredentialRepository userCredentialRepository, UserRepository userRepository, PasswordEncoder encoder, JwtUtil jwtUtil, AuthMapper authMapper) {
        this.authenticationManager = authenticationManager;
        this.userCredentialRepository = userCredentialRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.authMapper = authMapper;
    }

    public String auth(Authentication authentication) {
        return authentication.getName();
    }

    public String authenticateUser(UserCredential userCredential, User user) {
        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        userCredential.getUsername(),
                        userCredential.getPassword()
                )
        );

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtUtil.generateToken(userDetails.getUsername());
    }

    public SignupResponse registerUser(SignupRequest signupRequest) {
        if (userCredentialRepository.existsByUsername(signupRequest.getUsername()))
            throw new CustomException("User already exists");

        if (userRepository.existsByEmail(signupRequest.getEmail()))
            throw new CustomException("Email already exists");

        UserCredential newUserCredential = new UserCredential(
                null, signupRequest.getUsername(),
                encoder.encode(signupRequest.getPassword())
        );

        userCredentialRepository.save(newUserCredential);

        User user = new User();

        user.setFirst_name(signupRequest.getFirstName());
        user.setLast_name(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());

        userRepository.save(user);

        return authMapper.toSignupResponse(newUserCredential);
    }
}
