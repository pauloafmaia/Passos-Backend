package sys.passos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sys.passos.dto.AtualizarUsuarioRequest;
import sys.passos.dto.IncluirUsuarioRequest;
import sys.passos.dto.IncluirUsuarioResponse;
import sys.passos.dto.UserDTO;
import sys.passos.model.Usuario;
import sys.passos.service.UsuarioService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> ler(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }

//    @PostMapping()
//    public ResponseEntity<IncluirUsuarioResponse> incluir(@RequestParam UserDTO usuarioData) throws IOException {
//
//        var usuario = usuarioService.incluir(new IncluirUsuarioRequest());
//
//        var usuarioResponse = new IncluirUsuarioResponse();
//        BeanUtils.copyProperties(usuario, usuarioResponse);
//        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
//    }
//
//    @PutMapping()
//    public ResponseEntity<Usuario> atualizar(@RequestParam UserDTO dto) throws IOException {
//        return ResponseEntity.ok(usuarioService.atualizar(dto));
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
//        usuarioService.deletar(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
