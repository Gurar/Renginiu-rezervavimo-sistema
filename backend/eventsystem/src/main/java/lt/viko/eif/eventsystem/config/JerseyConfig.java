package lt.viko.eif.eventsystem.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api*")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages(
                "lt.viko.eif.eventsystem.resource",
                "lt.viko.eif.eventsystem.mapper"
        );


    }
}
