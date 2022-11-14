package sys.passos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sys.passos.dao.EventRepository;
import sys.passos.model.Event;
import java.util.List;

@RestController
@RequestMapping
public class EventController {

    @Autowired
    private EventRepository eventRepository;


    @GetMapping("/event")
    public List<Event> getEvent() {
        return eventRepository.findAll();
    }

    @GetMapping("/event/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return eventRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/event")
    public Event create (@RequestBody Event event){
        return eventRepository.save(event);
    }

    @PutMapping("/event/{id}")
    public ResponseEntity update (@PathVariable("id") long id, @RequestBody Event event){
        return eventRepository.findById(id)
                .map(record -> {
                    record.setName(event.getName());
                    record.setLocal(event.getLocal());
                    record.setDate(event.getDate());
                    record.setSetList(event.getSetList());
                    Event updated = eventRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity delete (@PathVariable long id){
        return eventRepository.findById(id)
                .map(record -> {
                    eventRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}