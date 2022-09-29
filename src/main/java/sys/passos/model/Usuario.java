package sys.passos.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
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
