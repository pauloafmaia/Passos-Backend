package sys.passos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sys.passos.dao.SetListRepository;
import sys.passos.model.SetList;
import java.util.List;

@RestController
@RequestMapping
public class SetListController {

    @Autowired
    private SetListRepository setListRepository;


    @GetMapping("/setlist")
    public List<SetList> getSetList() {
        return setListRepository.findAll();
    }

    @GetMapping("/setlist/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return setListRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/setlist")
    public SetList create (@RequestBody SetList setList){
        return setListRepository.save(setList);
    }

    @PutMapping("/setlist/{id}")
    public ResponseEntity update (@PathVariable("id") long id, @RequestBody SetList setList){
        return setListRepository.findById(id)
                .map(record -> {
                    record.setEvent(setList.getEvent());
                    record.setLocal(setList.getLocal());
                    record.setDate(setList.getDate());
                    record.setSetList(setList.getSetList());
                    SetList updated = setListRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/setlist/{id}")
    public ResponseEntity delete (@PathVariable long id){
        return setListRepository.findById(id)
                .map(record -> {
                    setListRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}