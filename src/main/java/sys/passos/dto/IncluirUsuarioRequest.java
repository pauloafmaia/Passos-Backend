package sys.passos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncluirUsuarioRequest {

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
}
