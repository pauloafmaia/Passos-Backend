package sys.passos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sys.passos.dao.SongRepository;
import sys.passos.dto.SetListDTO;
import sys.passos.dto.SongDTO;
import sys.passos.exception.SetListNotFoundException;
import sys.passos.exception.SongNotFoundException;
import sys.passos.model.SetList;
import sys.passos.model.Song;
import sys.passos.util.CopyProperties;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public SongDTO getSong() {
        List<Song> song = songRepository.findAll();
        return (SongDTO) song;
    }

    public SongDTO getSongById(Long id) {
        Optional<Song> song = songRepository.findById(id);
        return song.map(u -> CopyProperties.copy(u, SongDTO.class)).orElseThrow(() -> new SongNotFoundException("Song Not Found"));
    }

    public SongDTO insert(Song song) {
        Assert.isNull(song.getId(),"Cannot add Song");
        return CopyProperties.copy(songRepository.save(song), SongDTO.class);
    }

    public SongDTO update(Song song, Long id) {
        Assert.notNull(id,"Cannot update Song");

        Optional<Song> optional = songRepository.findById(id);
        if(optional.isPresent()) {
            Song db = optional.get();
            db.setId(song.getId());
            System.out.println("Song id " + db.getId());
            songRepository.save(db);
            return CopyProperties.copy(songRepository.save(song), SongDTO.class);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        songRepository.deleteById(id);
    }
}
