package sys.passos.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;

}
