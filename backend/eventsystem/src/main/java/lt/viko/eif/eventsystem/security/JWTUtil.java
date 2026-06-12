package lt.viko.eif.eventsystem.security;

import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
    public String generateToken(String username) {
        return "Token";
    }

    public String getUserFromToken(String token) {
        return "admin";
    }
}
