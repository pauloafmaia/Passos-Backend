package sys.passos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sys.passos.dao.EventRepository;
import sys.passos.dto.EventDTO;
import sys.passos.exception.EventNotFoundException;
import sys.passos.model.Event;
import sys.passos.util.CopyProperties;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public EventDTO getEvent() {
        List<Event> event = eventRepository.findAll();
        return (EventDTO) event;
    }

    public EventDTO getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(u -> CopyProperties.copy(u, EventDTO.class)).orElseThrow(() -> new EventNotFoundException("Event Not Found"));
    }

    public EventDTO insert(Event event) {
        Assert.isNull(event.getId(),"Cannot add Event");
        return CopyProperties.copy(eventRepository.save(event), EventDTO.class);
    }

    public EventDTO update(Event event, Long id) {
        Assert.notNull(id,"Cannot update Event");

        Optional<Event> optional = eventRepository.findById(id);
        if(optional.isPresent()) {
            Event db = optional.get();
            db.setId(event.getId());
            System.out.println("Event id " + db.getId());
            eventRepository.save(db);
            return CopyProperties.copy(eventRepository.save(event), EventDTO.class);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
