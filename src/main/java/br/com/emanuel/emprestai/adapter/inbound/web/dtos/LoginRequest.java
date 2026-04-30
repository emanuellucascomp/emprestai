package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Documento (CPF/CNPJ) é obrigatório")
    private String document; // CPF ou CNPJ

    @NotBlank(message = "Senha é obrigatória")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String document, String password) {
        this.document = document;
        this.password = password;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

