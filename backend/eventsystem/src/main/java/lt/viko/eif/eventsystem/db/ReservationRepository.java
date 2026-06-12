package lt.viko.eif.eventsystem.db;

import lt.viko.eif.eventsystem.model.Reservation;
import lt.viko.eif.eventsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user );
}
