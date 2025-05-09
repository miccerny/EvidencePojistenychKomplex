package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;

public interface UserService extends UserDetailsService {

    void create(UserDTO userDTO, boolean isAdmin, BindingResult bindingResult);

}
