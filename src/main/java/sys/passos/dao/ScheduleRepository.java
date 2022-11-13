package sys.passos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.passos.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
