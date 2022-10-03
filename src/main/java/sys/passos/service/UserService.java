package sys.passos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sys.passos.dao.UserRepository;
import sys.passos.dto.UserDTO;
import sys.passos.exception.UserNotFoundException;
import sys.passos.model.User;
import sys.passos.util.CopyProperties;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository rep;

    public UserDTO getUser() {
        List<User> user = rep.findAll();
        return (UserDTO) user;
    }

    public UserDTO getUserById(Long id) {
        Optional<User> user = rep.findById(id);
        return user.map(u -> CopyProperties.copy(u, UserDTO.class)).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserDTO insert(User user) {
        Assert.isNull(user.getId(),"Cannot add User");
        return CopyProperties.copy(rep.save(user), UserDTO.class);
    }

    public UserDTO update(User user, Long id) {
        Assert.notNull(id,"Cannot update User");

        Optional<User> optional = rep.findById(id);
        if(optional.isPresent()) {
            User db = optional.get();
            db.setId(user.getId());
            System.out.println("User id " + db.getId());
            rep.save(db);
            return CopyProperties.copy(rep.save(user), UserDTO.class);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}