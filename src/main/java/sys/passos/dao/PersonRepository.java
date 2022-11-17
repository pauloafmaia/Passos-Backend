package sys.passos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.passos.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
