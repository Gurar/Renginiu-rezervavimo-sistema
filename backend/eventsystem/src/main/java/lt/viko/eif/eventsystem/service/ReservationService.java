package lt.viko.eif.eventsystem.service;

import lt.viko.eif.eventsystem.client.TicketmasterClient;
import lt.viko.eif.eventsystem.client.response.TicketmasterEvent;
import lt.viko.eif.eventsystem.db.EventRepository;
import lt.viko.eif.eventsystem.db.ReservationRepository;
import lt.viko.eif.eventsystem.dto.ReservationResponse;
import lt.viko.eif.eventsystem.model.Event;
import lt.viko.eif.eventsystem.model.Reservation;
import lt.viko.eif.eventsystem.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;
    private final TicketmasterClient ticketmasterClient;

    public ReservationService(ReservationRepository reservationRepository, EventRepository eventRepository, TicketmasterClient ticketmasterClient) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
        this.ticketmasterClient = ticketmasterClient;
    }

    public Reservation eventReservation(String eventId, User user ) {

        TicketmasterEvent ticketmasterEvent = ticketmasterClient.getEventById(eventId);

        LocalDateTime eventDate = LocalDateTime.parse(ticketmasterEvent.getDates().getStart().getLocalDate() + "T20:00:00");

//        if (eventDate.isBefore(LocalDateTime.now()))
//            throw new RuntimeException("Event already started");

        Event event = eventRepository.findByExternalEventId(eventId).orElse(null);

        if (event == null) {
            event = new Event();
            event.setExternalEventId(ticketmasterEvent.getId());
            event.setTitle(ticketmasterEvent.getName());
            event.setEventDate(eventDate);
            event.setTotalSeats(100);
            event.setReservedSeats(0);
            event = eventRepository.save( event );
        }

        if ( event.getReservedSeats() >= event.getTotalSeats() )
            throw new RuntimeException( "No available seats" );

        event.setReservedSeats( event.getReservedSeats() + 1 );

        eventRepository.save(event);

        Reservation reservation = new Reservation();

        reservation.setUser(user);

        reservation.setEvent(event);

        reservation.setExternalEventId(event.getExternalEventId());

        reservation.setEventName( event.getTitle());

        reservation.setReservationDate( LocalDateTime.now() );


        return reservationRepository.save( reservation );
    }


}
