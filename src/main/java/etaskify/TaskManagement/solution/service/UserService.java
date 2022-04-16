package etaskify.TaskManagement.solution.service;

import etaskify.TaskManagement.solution.model.User;
import etaskify.TaskManagement.solution.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
