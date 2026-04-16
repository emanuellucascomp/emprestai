package br.com.emanuel.emprestai.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import br.com.emanuel.emprestai.domain.model.InstallmentStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "INSTALLMENT")
public class InstallmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private LoanEntity loan;

    private Integer number;
    private BigDecimal amount;
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private InstallmentStatus installmentStatus;

    public InstallmentEntity() {
    }

    public InstallmentEntity(Long id, LoanEntity loan, Integer number, BigDecimal amount,
                            LocalDate dueDate, InstallmentStatus installmentStatus) {
        this.id = id;
        this.loan = loan;
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

    public LoanEntity getLoan() {
        return loan;
    }

    public void setLoan(LoanEntity loan) {
        this.loan = loan;
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

