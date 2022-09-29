package sys.passos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.passos.dto.AtualizarUsuarioRequest;
import sys.passos.dto.IncluirUsuarioRequest;
import sys.passos.dao.UsuarioRepository;
import sys.passos.dto.UserDTO;
import sys.passos.exception.UsuarioNaoEncontradoException;
import sys.passos.model.Usuario;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ObjectMapper mapper = new ObjectMapper();

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado " + id));
    }

//    public Usuario incluir(UserDTO UsuarioRequest) {
//        var data = Instant.now();
//
//        var usuario = new Usuario();
//        BeanUtils.copyProperties(usuarioRepository, usuario);
//        usuario.setDataCadastro(data);
//        usuario.setUltimaAtualizacao(data);
//        usuarioRepository.save(usuario);
//
//        return usuario;
//    }
//
    public Usuario atualizar(UserDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getId()).get();

        BeanUtils.copyProperties(dto, usuario);
        usuario.setUltimaAtualizacao(LocalDate.now());
        usuarioRepository.save(usuario);
        return usuario;
    }
//
//    public void deletar(Long id) {
//        usuarioRepository.deleteById(id);
//    }
}
