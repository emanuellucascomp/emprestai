package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

public class StoreResponse {
    private Long id;
    private String name;
    private String cnpj;
    private String address;
    private String phone;

    public StoreResponse() {
    }

    public StoreResponse(Long id, String name, String cnpj, String address, String phone) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
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
