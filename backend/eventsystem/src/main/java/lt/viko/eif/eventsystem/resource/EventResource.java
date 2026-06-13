package lt.viko.eif.eventsystem.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lt.viko.eif.eventsystem.dto.EventResponse;
import lt.viko.eif.eventsystem.service.EventService;
import org.springframework.stereotype.Component;

import java.util.List;

@Path("/api/events")
@Component
public class EventResource {
    private final EventService eventService;

    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @GET
    public List<EventResponse> getEvents() {
        return eventService.getEvents();
    }

}
