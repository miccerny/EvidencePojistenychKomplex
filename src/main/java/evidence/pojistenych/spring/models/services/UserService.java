package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void create(UserDTO userDTO, boolean isAdmin);

}
