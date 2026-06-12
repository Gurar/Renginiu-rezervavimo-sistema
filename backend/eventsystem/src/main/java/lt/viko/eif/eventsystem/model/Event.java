package lt.viko.eif.eventsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    private String externalEventId;
    private String title;
    private LocalDateTime eventDate;
    private Integer totalSeats;
    private Integer reservedSeats;

    public Event() {
    }

    public Event(String externalEventId, String title, LocalDateTime eventDate, Integer totalSeats, Integer reservedSeats) {
        this.externalEventId = externalEventId;
        this.title = title;
        this.eventDate = eventDate;
        this.totalSeats = totalSeats;
        this.reservedSeats = reservedSeats;
    }

    public String getExternalEventId() {
        return externalEventId;
    }

    public void setExternalEventId(String externalEventId) {
        this.externalEventId = externalEventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Integer reservedSeats) {
        this.reservedSeats = reservedSeats;
    }
}
