package lt.viko.eif.eventsystem.db;

import lt.viko.eif.eventsystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByExternalEventId(String externalEventId );
}
