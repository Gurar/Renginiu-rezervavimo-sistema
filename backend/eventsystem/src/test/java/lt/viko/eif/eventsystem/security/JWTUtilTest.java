package lt.viko.eif.eventsystem.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JWTUtilTest {
    private JWTUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JWTUtil();
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
}
