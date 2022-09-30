package sys.passos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sys.passos.dao.UserRepository;
import sys.passos.dto.UserDTO;
import sys.passos.exception.UserNotFoundException;
import sys.passos.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository rep;

    public List<UserDTO> getUsers() {
        List<UserDTO> list = rep.findAll().stream().map(UserDTO::create).collect(Collectors.toList());
        return list;
    }

    public UserDTO getUserById(Long id) {
        Optional<User> user = rep.findById(id);
        return user.map(UserDTO::create).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserDTO insert(User user) {
        Assert.isNull(user.getId(),"Cannot add User");
        return UserDTO.create(rep.save(user));
    }

    public UserDTO update(User user, Long id) {
        Assert.notNull(id,"Cannot update User");

        Optional<User> optional = rep.findById(id);
        if(optional.isPresent()) {
            User db = optional.get();
            db.setId(user.getId());
            System.out.println("User id " + db.getId());
            rep.save(db);
            return UserDTO.create(db);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}