package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LoanResponse {
    private Long id;
    private BigDecimal amount;
    private LocalDate startDate;
    private String loanStatus;
    private List<InstallmentResponse> installments;

    public LoanResponse() {
    }

    public LoanResponse(Long id, BigDecimal amount, LocalDate startDate, String loanStatus) {
        this.id = id;
        this.amount = amount;
        this.startDate = startDate;
        this.loanStatus = loanStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public List<InstallmentResponse> getInstallments() {
        return installments;
    }

    public void setInstallments(List<InstallmentResponse> installments) {
        this.installments = installments;
    }
}

