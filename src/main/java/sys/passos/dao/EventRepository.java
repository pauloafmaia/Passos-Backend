package sys.passos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.passos.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
