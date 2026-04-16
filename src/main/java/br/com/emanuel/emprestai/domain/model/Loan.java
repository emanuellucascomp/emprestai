package br.com.emanuel.emprestai.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Loan {
    private Long id;
    private Long customerId;
    private BigDecimal amount;
    private LocalDate startDate;
    private LoanStatus loanStatus;
    private List<Installment> installments;

    public Loan() {
        this.installments = new ArrayList<>();
    }

    public Loan(Long id, Long customerId, BigDecimal amount, LocalDate startDate,
                LoanStatus loanStatus, List<Installment> installments) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.startDate = startDate;
        this.loanStatus = loanStatus;
        this.installments = installments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}
