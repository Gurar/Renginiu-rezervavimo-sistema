package lt.viko.eif.eventsystem.service;

import lt.viko.eif.eventsystem.client.TicketmasterClient;
import lt.viko.eif.eventsystem.client.response.TicketmasterResponse;
import lt.viko.eif.eventsystem.dto.EventResponse;
import lt.viko.eif.eventsystem.mapper.EventMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    private final TicketmasterClient ticketmasterClient;

    private final EventMapper eventMapper;

    private TicketmasterResponse cachedEvents;


    private LocalDateTime lastFetchTime;

    public EventService(TicketmasterClient ticketmasterClient, EventMapper eventMapper) {
        this.ticketmasterClient = ticketmasterClient;
        this.eventMapper = eventMapper;
    }

    public List<EventResponse> getEvents() {
        if ( cachedEvents != null && lastFetchTime != null && lastFetchTime.plusMinutes(5).isAfter(LocalDateTime.now()) )
            return cachedEvents.getEmbedded().getEvents().stream().map(eventMapper::toEventResponse).toList();

        cachedEvents = ticketmasterClient.getEvents();

        lastFetchTime = LocalDateTime.now();

        return cachedEvents.getEmbedded().getEvents().stream().map(eventMapper::toEventResponse).toList();
    }

    public TicketmasterResponse getEventsAll() {
        if ( cachedEvents != null && lastFetchTime != null && lastFetchTime.plusMinutes(5).isAfter(LocalDateTime.now()) )
            return cachedEvents;

        cachedEvents = ticketmasterClient.getEvents();

        lastFetchTime = LocalDateTime.now();

        return cachedEvents;
    }

}
