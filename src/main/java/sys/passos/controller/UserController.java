package sys.passos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sys.passos.dao.UserRepository;
import sys.passos.model.User;
import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return userRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/user")
    public User create (@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity update (@PathVariable("id") long id, @RequestBody User user){
        return userRepository.findById(id)
                .map(record -> {
                    record.setEmail(user.getEmail());
                    record.setPassword(user.getPassword());
                    User updated = userRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                  }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity delete (@PathVariable long id){
        return userRepository.findById(id)
                .map(record -> {
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
