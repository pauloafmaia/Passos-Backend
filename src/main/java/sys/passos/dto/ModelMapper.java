package sys.passos.dto;

import sys.passos.model.User;

public interface ModelMapper {
    UserDTO map(User user, Class<UserDTO> userDTOClass);
}
