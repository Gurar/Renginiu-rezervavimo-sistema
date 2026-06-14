package lt.viko.eif.eventsystem.dto;

import java.util.List;
import java.util.Map;

public class ReservationResponse {
    private Long reservationId;
    private String message;
    private List<Map<String, String>> links;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Map<String, String>> getLinks() {
        return links;
    }

    public void setLinks(List<Map<String, String>> links) {
        this.links = links;
    }
}
