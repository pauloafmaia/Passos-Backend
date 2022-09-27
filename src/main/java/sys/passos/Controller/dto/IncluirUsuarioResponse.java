package sys.passos.Controller.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class IncluirUsuarioResponse {

    private Long id;
    private String email;
    private String senha;
    private String nome;
    private String dataNascimento;
    private String cep;
    private String endereco;
    private String cidade;
    private String estado;
    private String celular;
    private String genero;
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;
}
