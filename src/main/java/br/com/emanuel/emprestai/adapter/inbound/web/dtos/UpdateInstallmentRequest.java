package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateInstallmentRequest {
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal amount;

    private LocalDate dueDate;

    public UpdateInstallmentRequest() {
    }

    public UpdateInstallmentRequest(BigDecimal amount, LocalDate dueDate) {
        this.amount = amount;
        this.dueDate = dueDate;
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
}

