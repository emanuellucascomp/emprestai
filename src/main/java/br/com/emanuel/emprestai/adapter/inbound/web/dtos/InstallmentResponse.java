package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentResponse {
    private Long id;
    private Integer number;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String installmentStatus;

    public InstallmentResponse() {
    }

    public InstallmentResponse(Long id, Integer number, BigDecimal amount, LocalDate dueDate, String installmentStatus) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.dueDate = dueDate;
        this.installmentStatus = installmentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getInstallmentStatus() {
        return installmentStatus;
    }

    public void setInstallmentStatus(String installmentStatus) {
        this.installmentStatus = installmentStatus;
    }
}

