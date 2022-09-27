package sys.passos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.passos.model.Usuario;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAll();

}
