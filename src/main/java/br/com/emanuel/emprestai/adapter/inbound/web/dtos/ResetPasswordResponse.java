package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

public class ResetPasswordResponse {
    private String message;
    private Long userId;
    private String userName;

    public ResetPasswordResponse() {
    }

    public ResetPasswordResponse(String message, Long userId, String userName) {
        this.message = message;
        this.userId = userId;
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

