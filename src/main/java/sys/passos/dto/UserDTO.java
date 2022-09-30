package sys.passos.dto;

import lombok.Data;
import sys.passos.model.User;

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

    public static UserDTO create(User user) {
        ModelMapper modelMapper = new ModelMapper() {
            @Override
            public UserDTO map(User user, Class<UserDTO> userDTOClass) {
                return null;
            }
        };
        return modelMapper.map(user, UserDTO.class);
    }
}
