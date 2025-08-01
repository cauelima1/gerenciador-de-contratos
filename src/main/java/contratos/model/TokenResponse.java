package contratos.model;

public class TokenResponse {


    private String Token;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public TokenResponse(String token) {
        Token = token;
    }
}


