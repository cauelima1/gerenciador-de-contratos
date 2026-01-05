package contratos.controller;

import contratos.model.Users;
import contratos.model.dto.UserDTO;
import contratos.repository.UserRepository;
import contratos.security.token.TokenBlackList;
import contratos.security.token.TokenService;
import contratos.service.AuthService;
import contratos.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;



@Controller
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenBlackList tokenBlackList;

    @Autowired
    private AuthService authService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        loginService.logout(request, response);
        return "auth/login";
    }


    @GetMapping("/login")
    public String paginaLogin(){
        return "auth/login";
    }

    @GetMapping("/")
    public String paginaLogin2(){
        return "auth/login";
    }

    @GetMapping("/cadastro")
    public String showForm(Model model) {
        model.addAttribute("user", new Users());
        return "auth/cadastro";
    }


    @PostMapping("/cadastro")
    public String cadastroUser (@ModelAttribute("user") @Validated UserDTO userDTO, Model model){
        return authService.createUser(model, userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user, HttpServletResponse response) throws UnsupportedEncodingException {
        loginService.token(user, response);
        return "index";
        }


}
