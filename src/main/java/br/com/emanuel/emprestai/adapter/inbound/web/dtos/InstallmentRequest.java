package br.com.emanuel.emprestai.adapter.inbound.web.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentRequest {
    @NotNull(message = "Número da parcela é obrigatório")
    private Integer number;

    @NotNull(message = "Valor da parcela é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal amount;

    @NotNull(message = "Data de vencimento é obrigatória")
    private LocalDate dueDate;

    public InstallmentRequest() {
    }

    public InstallmentRequest(Integer number, BigDecimal amount, LocalDate dueDate) {
        this.number = number;
        this.amount = amount;
        this.dueDate = dueDate;
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
}

