package sys.passos.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String birth;
    private String cep;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String gender;
}
