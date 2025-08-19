package contratos.service;

import contratos.model.Users;
import contratos.repository.UserRepository;
import contratos.security.token.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String token (Users user, HttpServletResponse response){
        UserDetails newUser = userRepository.findByLogin(user.getLogin());
        if (newUser != null){
            try {
                var usernamePassword = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
                var auth = authenticationManager.authenticate(usernamePassword);
                var token = tokenService.generatetoken((Users) auth.getPrincipal());

                tokenService.newCookie(token, response);

                return token;
            } catch (Exception e) {
                throw new RuntimeException("Invalid password, try again");
            }
        } else {
            throw new RuntimeException("Login not Found");
        }
    }



}
