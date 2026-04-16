package br.com.emanuel.emprestai.domain.model;

public class Store {
    private Long id;
    private String name;
    private String cnpj;
    private String address;
    private String phone;
    private String email;
    private String password; // Criptografada
    private UserRole role;
    private Long administratorId;
    private Boolean active;

    public Store() {
        this.role = UserRole.STORE;
        this.active = true;
    }

    public Store(Long id, String name, String cnpj, String address, String phone) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phone = phone;
        this.role = UserRole.STORE;
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
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
}
