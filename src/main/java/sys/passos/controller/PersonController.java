package sys.passos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sys.passos.dao.PersonRepository;
import sys.passos.model.Person;

import java.util.List;

@Controller
@RequestMapping
public class PersonController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping("/person")
    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return personRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/event")
    public Person create (@RequestBody Person person){
        return personRepository.save(person);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity update (@PathVariable("id") long id, @RequestBody Person person){
        return personRepository.findById(id)
                .map(record -> {
                    record.setName(person.getName());
                    record.setBirthDate(person.getBirthDate());
                    record.setGender(person.getGender());
                    record.setEmail(person.getEmail());
                    record.setAddress(person.getAddress());
                    record.setCity(person.getCity());
                    record.setState(person.getState());
                    record.setCountry(person.getCountry());
                    Person updated = personRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity delete (@PathVariable long id){
        return personRepository.findById(id)
                .map(record -> {
                    personRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
