package br.com.emanuel.emprestai.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private Long id;
    private String name;
    private String cpf;
    private String address;
    private String phone;
    private String email;
    private String password; // Criptografada
    private UserRole role;
    private List<Loan> loans;
    private Long administratorId; // ID do administrador responsável
    private Boolean active;
    private String resetToken; // Token para reset de senha
    private Long resetTokenExpiration; // Data de expiração do token (timestamp)

    public Customer() {
        this.loans = new ArrayList<>();
        this.role = UserRole.CUSTOMER;
        this.active = true;
    }

    public Customer(Long id, String name, String cpf, String address, String phone, List<Loan> loans) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.phone = phone;
        this.loans = loans;
        this.role = UserRole.CUSTOMER;
        this.active = true;
    }

    // ...existing code...
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Long getResetTokenExpiration() {
        return resetTokenExpiration;
    }

    public void setResetTokenExpiration(Long resetTokenExpiration) {
        this.resetTokenExpiration = resetTokenExpiration;
    }
}
