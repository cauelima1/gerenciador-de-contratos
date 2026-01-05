package contratos.service;


import contratos.model.Users;
import contratos.model.dto.UserDTO;
import contratos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AuthService {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserRepository userRepository;

    public String createUser(Model model, UserDTO userDTO){
        if (userRepository.existsByLogin(userDTO.login().trim().toLowerCase())) {
            model.addAttribute("error", "Nome de usuário já cadastrado!");
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
            Users newUser = new Users(userDTO.login().toLowerCase(), encryptedPassword, "user");
            userRepository.save(newUser);
        }
        return "auth/login";

    }



}
