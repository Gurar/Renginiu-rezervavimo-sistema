package lt.viko.eif.eventsystem.dto;

import lt.viko.eif.eventsystem.model.User;

import java.time.LocalDateTime;

public class ReservationRequest {
    private String eventId;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
