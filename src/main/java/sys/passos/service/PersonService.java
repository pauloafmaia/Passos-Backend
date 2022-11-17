package sys.passos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import sys.passos.dao.PersonRepository;
import sys.passos.dto.PersonDTO;
import sys.passos.exception.PersonNotFoundException;
import sys.passos.model.Person;
import sys.passos.util.CopyProperties;

import java.util.List;
import java.util.Optional;

public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public PersonDTO getPerson() {
        List<Person> person = personRepository.findAll();
        return (PersonDTO) person;
    }

    public PersonDTO getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(u -> CopyProperties.copy(u, PersonDTO.class)).orElseThrow(() -> new PersonNotFoundException("Person Not Found"));
    }

    public PersonDTO insert(Person person) {
        Assert.isNull(person.getId(),"Cannot add Person");
        return CopyProperties.copy(personRepository.save(person), PersonDTO.class);
    }

    public PersonDTO update(Person person, Long id) {
        Assert.notNull(id,"Cannot update Person");

        Optional<Person> optional = personRepository.findById(id);
        if(optional.isPresent()) {
            Person db = optional.get();
            db.setId(person.getId());
            System.out.println("Person id " + db.getId());
            personRepository.save(db);
            return CopyProperties.copy(personRepository.save(person), PersonDTO.class);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
