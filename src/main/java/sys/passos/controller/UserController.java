package sys.passos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sys.passos.dao.UserRepository;
import sys.passos.model.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public void createUser (@RequestBody User user) {
        userRepository.save(user);
    }
//
////    @PutMapping("/user/{id}")
////    public void updateUser(@PathVariable ("id") Long id, @RequestBody User user) {
////        userRepository.
////    }
//
    @DeleteMapping("/user/{id}")
    public void deleteUser (@PathVariable ("id") Long id) {
        userRepository.deleteById(id);
    }

}
