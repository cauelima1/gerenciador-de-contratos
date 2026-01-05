package contratos.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import contratos.model.Users;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.security.core.token.Token;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.time.*;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generatetoken(Users user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("jwt")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro while generating Token", exception);
        }
    }


    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("jwt")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error on Token validation.");
        }
    }

    private Instant genExpirationDate() {
        Instant instant = ZonedDateTime.now()
                .plusHours(1)
                .toInstant();

        System.out.println("Expiração do token: " + instant);
        return instant;
    }


    public void newCookie(String token, HttpServletResponse response) {
    Cookie cookie = new Cookie("jwt", token);
                cookie.setHttpOnly(true); // Protege contra acesso via JavaScript
                cookie.setSecure(true); // Só envia via HTTPS
                cookie.setPath("/"); // Disponível para todas as rotas do domínio
                cookie.setMaxAge(3600); // Tempo de vida do cookie em segundos (1 hora)
                response.addCookie(cookie); // Adiciona o cookie à resposta HTTP
    }

}

