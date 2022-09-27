package sys.passos.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import sys.passos.Controller.dto.AtualizarUsuarioRequest;
import sys.passos.Controller.dto.IncluirUsuarioRequest;
import sys.passos.dao.UsuarioRepository;
import sys.passos.exception.UsuarioNaoEncontradoException;
import sys.passos.model.Usuario;

import java.time.Instant;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository UsuarioRepository) {
        this.usuarioRepository = UsuarioRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado " + id));
    }

    public Usuario incluir (IncluirUsuarioRequest UsuarioRequest) {
        var data = Instant.now();

        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRepository, usuario);
        usuario.setDataCadastro(data);
        usuario.setUltimaAtualizacao(data);
        usuarioRepository.save(usuario);

        return usuario;
    }

    public Usuario atualizar(AtualizarUsuarioRequest atualizarUsuarioRequest) {
        var usuario = usuarioRepository.findById(atualizarUsuarioRequest.getId()).get();

        BeanUtils.copyProperties(atualizarUsuarioRequest, usuario);
        usuario.setUltimaAtualizacao(Instant.now());
        usuarioRepository.save(usuario);
        return usuario;
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
