package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

import java.util.List;

public class CustomerResponse {
    private Long id;
    private String name;
    private String cpf;
    private String address;
    private String phone;
    private List<LoanResponse> loans;

    public CustomerResponse() { }

    public CustomerResponse(Long id, String name, String cpf, String address, String phone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.phone = phone;
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

    public List<LoanResponse> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanResponse> loans) {
        this.loans = loans;
    }
}
