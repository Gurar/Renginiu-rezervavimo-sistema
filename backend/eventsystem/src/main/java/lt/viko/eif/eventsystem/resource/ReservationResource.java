package lt.viko.eif.eventsystem.resource;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lt.viko.eif.eventsystem.dto.ReservationResponse;
import lt.viko.eif.eventsystem.mapper.ReservationMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import lt.viko.eif.eventsystem.db.UserCredentialRepository;
import lt.viko.eif.eventsystem.dto.ReservationRequest;
import lt.viko.eif.eventsystem.model.Reservation;
import lt.viko.eif.eventsystem.model.UserCredential;
import lt.viko.eif.eventsystem.service.ReservationService;
import org.springframework.stereotype.Component;

@Path("/api/reservation")
@Component
public class ReservationResource {

    private final ReservationService reservationService;

    private final ReservationMapper reservationMapper;

    private final UserCredentialRepository userCredentialRepository;

    public ReservationResource(ReservationService reservationService, ReservationMapper reservationMapper, UserCredentialRepository userCredentialRepository) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
        this.userCredentialRepository = userCredentialRepository;
    }

    @POST
    public Response eventReservation(ReservationRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserCredential userCredential = userCredentialRepository.findByUsername(username).orElseThrow();

       Reservation reservation = reservationService.eventReservation(request.getEventId(), userCredential.getUser());

        ReservationResponse response = reservationMapper.toReservationResponse(reservation);

        return Response.status(Response.Status.CREATED).entity(response).build();
    }

}
