package sys.passos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sys.passos.dao.UserRepository;
import sys.passos.model.User;
import sys.passos.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return userRepository.findById(id)
                .map(record -> ResponseEntity.status(200).body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(400).body("{\"message\": \"This email is already in use\"}");
        } else {
            userRepository.save(user);
            return ResponseEntity.status(201).body("{\"message\": \"User created successfully\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable long id, @RequestBody User user){
        return userRepository.findById(id)
                .map(record -> {
                    record.setLogin(user.getLogin());
                    record.setPassword(user.getPassword());
                    record.setEmail(user.getEmail());
                    User updated = userRepository.save(record);
                    return ResponseEntity.ok().body("User was updated successfully!");
                  }).orElse(ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable long id){
        return userRepository.findById(id)
                .map(record -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.status(200).body("User was removed!");
                }).orElse(ResponseEntity.status(404).build());
    }

}
