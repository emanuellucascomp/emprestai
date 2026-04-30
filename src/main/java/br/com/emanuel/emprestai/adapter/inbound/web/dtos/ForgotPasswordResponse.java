package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

public class ForgotPasswordResponse {
    private String message;
    private String token;
    private Long expiresInSeconds;

    public ForgotPasswordResponse() {
    }

    public ForgotPasswordResponse(String message, String token, Long expiresInSeconds) {
        this.message = message;
        this.token = token;
        this.expiresInSeconds = expiresInSeconds;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiresInSeconds() {
        return expiresInSeconds;
    }

    public void setExpiresInSeconds(Long expiresInSeconds) {
        this.expiresInSeconds = expiresInSeconds;
    }
}

