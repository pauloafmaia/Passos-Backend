package sys.passos.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.Instant;
import java.time.LocalDate;

@Data
public class UserDTO {
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
    private LocalDate dataCadastro;
    private LocalDate ultimaAtualizacao;
}
