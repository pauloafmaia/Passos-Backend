package sys.passos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.passos.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
