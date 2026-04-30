package br.com.emanuel.emprestai.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Installment {
    private Long id;
    private Long loanId;
    private Integer number;
    private BigDecimal amount;
    private LocalDate dueDate;
    private InstallmentStatus installmentStatus;

    public Installment() {
    }

    public Installment(Long id, Long loanId, Integer number, BigDecimal amount, LocalDate dueDate, InstallmentStatus installmentStatus) {
        this.id = id;
        this.loanId = loanId;
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

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
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

    public InstallmentStatus getInstallmentStatus() {
        return installmentStatus;
    }

    public void setInstallmentStatus(InstallmentStatus installmentStatus) {
        this.installmentStatus = installmentStatus;
    }
}
