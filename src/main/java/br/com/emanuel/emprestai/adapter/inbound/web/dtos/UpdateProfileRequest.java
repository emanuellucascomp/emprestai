package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateProfileRequest {
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String name;

    @Size(max = 255, message = "Endereço não pode exceder 255 caracteres")
    private String address;

    @Size(min = 10, max = 15, message = "Telefone deve ter entre 10 e 15 caracteres")
    private String phone;

    @Email(message = "Email deve ser válido")
    private String email;

    @Size(min = 6, max = 50, message = "Nova senha deve ter entre 6 e 50 caracteres")
    private String newPassword;

    @NotBlank(message = "Senha atual é obrigatória para validação")
    private String currentPassword;

    public UpdateProfileRequest() {
    }

    public UpdateProfileRequest(String name, String address, String phone, String email,
                               String newPassword, String currentPassword) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.newPassword = newPassword;
        this.currentPassword = currentPassword;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}

