package sys.passos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sys.passos.dao.ScheduleRepository;
import sys.passos.model.Schedule;

import java.util.List;

public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @GetMapping("/schedule")
    public List<Schedule> getSchedule() {
        return scheduleRepository.findAll();
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return scheduleRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/schedule")
    public Schedule create (@RequestBody Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    @PutMapping("/schedule/{id}")
    public ResponseEntity update (@PathVariable("id") long id, @RequestBody Schedule schedule){
        return scheduleRepository.findById(id)
                .map(record -> {
                    record.setEvent(schedule.getEvent());
                    record.setLocal(schedule.getLocal());
                    record.setDate(schedule.getDate());
                    record.setTime(schedule.getTime());
                    Schedule updated = scheduleRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity delete (@PathVariable long id){
        return scheduleRepository.findById(id)
                .map(record -> {
                    scheduleRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
