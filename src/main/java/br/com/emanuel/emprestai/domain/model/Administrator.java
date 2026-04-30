package br.com.emanuel.emprestai.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Administrator {
    private Long id;
    private String name;
    private String cpf;
    private String address;
    private String phone;
    private String email;
    private String password; // Criptografada
    private UserRole role;
    private List<Customer> customers;
    private List<Store> stores;
    private Boolean active;
    private String resetToken; // Token para reset de senha
    private Long resetTokenExpiration; // Data de expiração do token (timestamp)

    public Administrator() {
        this.customers = new ArrayList<>();
        this.stores = new ArrayList<>();
        this.role = UserRole.ADMINISTRATOR;
        this.active = true;
    }

    public Administrator(Long id, String name, String cpf, String address, String phone,
                        String email, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = UserRole.ADMINISTRATOR;
        this.customers = new ArrayList<>();
        this.stores = new ArrayList<>();
        this.active = true;
    }

    // Getters and Setters
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
