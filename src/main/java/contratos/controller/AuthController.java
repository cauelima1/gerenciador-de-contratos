package contratos.controller;

import contratos.model.Users;
import contratos.model.dto.UserDTO;
import contratos.repository.UserRepository;
import contratos.security.TokenBlackList;
import contratos.security.TokenService;
import contratos.service.AuthService;
import contratos.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/login")
    public String paginaLogin(){
        return "/auth/login";
    }

    @GetMapping("/cadastro")
    public String showForm(Model model) {
        model.addAttribute("user", new Users());
        return "/auth/cadastro";
    }


    @PostMapping("/cadastro")
    public String cadastroUser (@ModelAttribute("user") @Validated UserDTO userDTO, Model model){
        if (userRepository.existsByLogin(userDTO.login().trim().toLowerCase())) {
            model.addAttribute("error", "Nome de usuário já cadastrado!");
            return "cadastro";
        }
        authService.createUser(userDTO);
        return "/auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user, HttpServletResponse response) throws UnsupportedEncodingException {
        var token = loginService.token(user);
        Cookie cookie = new Cookie("jwt",token);
        cookie.setHttpOnly(true); // Protege contra acesso via JavaScript
        cookie.setSecure(true); // Só envia via HTTPS
        cookie.setPath("/"); // Disponível para todas as rotas do domínio
        cookie.setMaxAge(3600); // Tempo de vida do cookie em segundos (1 hora)
        response.addCookie(cookie); // Adiciona o cookie à resposta HTTP
        System.out.println(token);
        return "index";
    }


}
