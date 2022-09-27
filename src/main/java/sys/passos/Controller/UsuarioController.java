package sys.passos.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sys.passos.Controller.dto.AtualizarUsuarioRequest;
import sys.passos.Controller.dto.IncluirUsuarioRequest;
import sys.passos.Controller.dto.IncluirUsuarioResponse;
import sys.passos.model.Usuario;
import sys.passos.service.UsuarioService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ObjectMapper mapper = new ObjectMapper();

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> listar() {
        return new ResponseEntity<>(usuarioService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> ler(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usuarioService.getUsuario(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<IncluirUsuarioResponse> incluir(@RequestParam String usuarioData, @RequestParam("file") final MultipartFile file) throws IOException {

        final var incluirUsuarioRequest = mapper.readValue(usuarioData, IncluirUsuarioRequest.class);

        var usuario = usuarioService.incluir(new IncluirUsuarioRequest());

        var usuarioResponse = new IncluirUsuarioResponse();
        BeanUtils.copyProperties(usuario, usuarioResponse);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Usuario> atualizar(@RequestParam String usuarioData, @RequestParam(value = "file", required = false) final MultipartFile file ) throws IOException {
        final var atualizarUsuarioRequest = mapper.readValue(usuarioData, AtualizarUsuarioRequest.class);

        var usuario = usuarioService.atualizar(atualizarUsuarioRequest);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
