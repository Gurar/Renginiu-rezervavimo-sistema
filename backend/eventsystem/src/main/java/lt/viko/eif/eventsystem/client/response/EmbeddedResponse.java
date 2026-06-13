package lt.viko.eif.eventsystem.client.response;

import java.util.List;

public class EmbeddedResponse {
    private List<TicketmasterEvent> events;

    public List<TicketmasterEvent> getEvents() {
        return events;
    }

    public void setEvents(List<TicketmasterEvent> events) {
        this.events = events;
    }
}
