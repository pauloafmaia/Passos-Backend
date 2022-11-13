package sys.passos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sys.passos.dao.ScheduleRepository;
import sys.passos.dto.ScheduleDTO;
import sys.passos.exception.ScheduleNotFoundException;
import sys.passos.model.Schedule;
import sys.passos.util.CopyProperties;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ScheduleDTO getSchedule() {
        List<Schedule> schedule = scheduleRepository.findAll();
        return (ScheduleDTO) schedule;
    }

    public ScheduleDTO getScheduleById(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.map(u -> CopyProperties.copy(u, ScheduleDTO.class)).orElseThrow(() -> new ScheduleNotFoundException("Schedule Not Found"));
    }

    public ScheduleDTO insert(Schedule schedule) {
        Assert.isNull(schedule.getId(),"Cannot add Schedule");
        return CopyProperties.copy(scheduleRepository.save(schedule), ScheduleDTO.class);
    }

    public ScheduleDTO update(Schedule schedule, Long id) {
        Assert.notNull(id,"Cannot update Schedule");

        Optional<Schedule> optional = scheduleRepository.findById(id);
        if(optional.isPresent()) {
            Schedule db = optional.get();
            db.setId(schedule.getId());
            System.out.println("Schedule id " + db.getId());
            scheduleRepository.save(db);
            return CopyProperties.copy(scheduleRepository.save(schedule), ScheduleDTO.class);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
