package sys.passos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sys.passos.dao.SongRepository;
import sys.passos.model.SetList;
import sys.passos.model.Song;

import java.util.List;

@RestController
@RequestMapping
public class SongController {

    @Autowired
    private SongRepository songRepository;


    @GetMapping("/song")
    public List<Song> getSong() {
        return songRepository.findAll();
    }

    @GetMapping("/song/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return songRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/song")
    public Song create (@RequestBody Song song){
        return songRepository.save(song);
    }

    @PutMapping("/song/{id}")
    public ResponseEntity update (@PathVariable("id") long id, @RequestBody Song song){
        return songRepository.findById(id)
                .map(record -> {
                    record.setName(song.getName());
                    record.setType(song.getType());
                    record.setTone(song.getTone());
                    record.setLink(song.getLink());
                    Song updated = songRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/song/{id}")
    public ResponseEntity delete (@PathVariable long id){
        return songRepository.findById(id)
                .map(record -> {
                    songRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}