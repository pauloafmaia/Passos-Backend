package sys.passos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.passos.model.Song;

public interface SongRepository extends JpaRepository <Song, Long> {
}
