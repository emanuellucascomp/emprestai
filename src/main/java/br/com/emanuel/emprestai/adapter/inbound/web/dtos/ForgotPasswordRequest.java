package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
    @NotBlank(message = "Documento é obrigatório")
    private String document; // CPF ou CNPJ

    public ForgotPasswordRequest() {
    }

    public ForgotPasswordRequest(String document) {
        this.document = document;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}

