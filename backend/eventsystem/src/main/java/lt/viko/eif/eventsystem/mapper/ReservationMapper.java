package lt.viko.eif.eventsystem.mapper;

import lt.viko.eif.eventsystem.dto.ReservationResponse;
import lt.viko.eif.eventsystem.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ReservationMapper {
    public ReservationResponse toReservationResponse(Reservation reservation ) {
        ReservationResponse response = new ReservationResponse();

        response.setMessage( "Reservation created successfully" );

        response.setReservationId(reservation.getId());

        List<Map<String, String>> links = List.of(
                Map.of(
                        "rel", "self",
                        "href", "/api/reservations/" + reservation.getId(),
                        "method", "GET" ),

                Map.of( "rel", "cancel",
                        "href", "/api/reservations/" + reservation.getId(),
                        "method", "DELETE" )
        );

        response.setLinks(links); return response; }
}
