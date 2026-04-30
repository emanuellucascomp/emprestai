package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LoanRequest {
    @NotNull(message = "Customer ID é obrigatório")
    private Long customerId;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal amount;

    @NotNull(message = "Data de início é obrigatória")
    private LocalDate startDate;

    @NotEmpty(message = "Deve ter pelo menos uma parcela")
    @Valid
    private List<InstallmentRequest> installments;

    public LoanRequest() {
    }

    public LoanRequest(Long customerId, BigDecimal amount, LocalDate startDate, List<InstallmentRequest> installments) {
        this.customerId = customerId;
        this.amount = amount;
        this.startDate = startDate;
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

    public List<InstallmentRequest> getInstallments() {
        return installments;
    }

    public void setInstallments(List<InstallmentRequest> installments) {
        this.installments = installments;
    }
}

