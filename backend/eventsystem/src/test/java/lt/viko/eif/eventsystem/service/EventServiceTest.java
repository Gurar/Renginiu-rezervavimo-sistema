package lt.viko.eif.eventsystem.service;

import lt.viko.eif.eventsystem.client.TicketmasterClient;
import lt.viko.eif.eventsystem.client.response.EmbeddedResponse;
import lt.viko.eif.eventsystem.client.response.TicketmasterEvent;
import lt.viko.eif.eventsystem.client.response.TicketmasterResponse;
import lt.viko.eif.eventsystem.dto.EventResponse;
import lt.viko.eif.eventsystem.mapper.EventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EventServiceTest {

    private EventService eventService;

    @Mock
    private TicketmasterClient ticketmasterClient;

    @Mock
    private EventMapper eventMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        eventService = new EventService(ticketmasterClient, eventMapper);

    }

    @Test
    void shouldFetchFromClientWhenCacheEmpty() {
        TicketmasterResponse response = buildTicketmasterResponse();

        when(ticketmasterClient.getEvents()).thenReturn(response);
        when(eventMapper.toEventResponse(any())).thenReturn(new EventResponse());

        List<EventResponse> result = eventService.getEvents();

        assertThat(result).hasSize(1);
        verify(ticketmasterClient, times(1)).getEvents();

    }

    @Test
    void shouldUseCachedDataWhenCacheValid() {
        TicketmasterResponse response = buildTicketmasterResponse();
        when(ticketmasterClient.getEvents()).thenReturn(response);
        when(eventMapper.toEventResponse(any())).thenReturn(new EventResponse());

        eventService.getEvents();
        eventService.getEvents();

        verify(ticketmasterClient, times(1)).getEvents();
    }

    @Test
    void shouldReturnMappedEvents() {
        TicketmasterResponse response = buildTicketmasterResponse();
        when(ticketmasterClient.getEvents()).thenReturn(response);

        EventResponse eventResponse = new EventResponse();
        eventResponse.setId("evt-001");
        when(eventMapper.toEventResponse(any())).thenReturn(eventResponse);

        List<EventResponse> result = eventService.getEvents();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("evt-001");
    }

    @Test
    void shouldReturnAllEventsWhenCacheEmpty() {
        TicketmasterResponse response = buildTicketmasterResponse();
        when(ticketmasterClient.getEvents()).thenReturn(response);

        TicketmasterResponse result = eventService.getEventsAll();

        assertThat(result).isEqualTo(response);
        verify(ticketmasterClient, times(1)).getEvents();
    }

    @Test
    void shouldUseAllCachedDataWhenCacheValid() {
        TicketmasterResponse response = buildTicketmasterResponse();
        when(ticketmasterClient.getEvents()).thenReturn(response);

        eventService.getEventsAll();
        eventService.getEventsAll();

        verify(ticketmasterClient, times(1)).getEvents();
    }

    @Test
    void shouldReturnCachedResponse() {
        TicketmasterResponse response = buildTicketmasterResponse();
        when(ticketmasterClient.getEvents()).thenReturn(response);

        TicketmasterResponse result = eventService.getEventsAll();

        assertThat(result).isEqualTo(response);
    }

    private TicketmasterResponse buildTicketmasterResponse() {
        TicketmasterEvent event = new TicketmasterEvent();
        event.setId("evt-001");
        event.setName("Rock Concert");

        EmbeddedResponse embedded = new EmbeddedResponse();
        embedded.setEvents( List.of(event) );

        TicketmasterResponse response = new TicketmasterResponse();
        response.setEmbedded(embedded);

        return response;
    }
}
