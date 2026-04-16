package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

public class UpdateProfileRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String newPassword;
    private String currentPassword; // Obrigatório para validação

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

