package lt.viko.eif.eventsystem.db;

import lt.viko.eif.eventsystem.model.User;
import lt.viko.eif.eventsystem.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email );
    boolean existsByEmail(String email);
}
