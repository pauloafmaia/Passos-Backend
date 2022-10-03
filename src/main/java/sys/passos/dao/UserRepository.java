package sys.passos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.passos.dto.UserDTO;
import sys.passos.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

}
