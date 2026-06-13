package lt.viko.eif.eventsystem.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil("mysecretmysecretmysecretmysecret", 36000);
    }

    @Test
    void shouldGenerateToken() {
        String token = jwtUtil.generateToken("admin");

        assertNotNull(token);
    }

    @Test
    void shouldReturnUsernameFromToken() {
        String token = jwtUtil.generateToken("admin");
        String username = jwtUtil.getUserFromToken(token);
        assertEquals("admin", username);
    }

    @Test
    void shouldReturnTrueValidadJwtToken() {
        String token = jwtUtil.generateToken("admin");
        boolean isValid = jwtUtil.validateJwtToken(token);

        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseForInvalidToken() {
        boolean isValid = jwtUtil.validateJwtToken("invalid token");

        assertFalse(isValid);
    }


}
