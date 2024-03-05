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
        if (user.isPresent()) {
            UserDTO userDTO = new UserDTO();
            User u = user.get();
            userDTO.setId(u.getId());
            userDTO.setLogin(u.getLogin());
            userDTO.setPassword(u.getPassword());
            userDTO.setEmail(u.getEmail());

            return userDTO;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public UserDTO insert(User user) {
        Assert.isNull(user.getId(), "Cannot add User");

        User savedUser = rep.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setLogin(savedUser.getLogin());
        userDTO.setPassword(savedUser.getPassword());
        userDTO.setEmail(savedUser.getEmail());

        return userDTO;
    }


    public UserDTO update(User user, Long id) {
        Assert.notNull(id, "Cannot update User");

        Optional<User> optional = rep.findById(id);
        if (optional.isPresent()) {
            User db = optional.get();
            db.setId(user.getId());
            db.setLogin(user.getLogin());
            db.setPassword(user.getPassword());
            db.setEmail(user.getEmail());
            rep.save(db);

            UserDTO updatedUserDTO = new UserDTO();
            updatedUserDTO.setId(db.getId());
            db.setLogin(user.getLogin());
            db.setPassword(user.getPassword());
            db.setEmail(user.getEmail());

            return updatedUserDTO;
        } else {
            return null;
        }
    }


    public void delete(Long id) {
        rep.deleteById(id);
    }
}