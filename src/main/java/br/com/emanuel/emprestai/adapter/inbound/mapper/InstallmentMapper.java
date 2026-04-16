package br.com.emanuel.emprestai.adapter.inbound.mapper;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.InstallmentResponse;
import br.com.emanuel.emprestai.domain.model.Installment;
import org.springframework.stereotype.Component;

@Component
public class InstallmentMapper {

    public InstallmentResponse toResponse(Installment domain) {
        if (domain == null) return null;
        return new InstallmentResponse(
            domain.getId(),
            domain.getNumber(),
            domain.getAmount(),
            domain.getDueDate(),
            domain.getInstallmentStatus() != null ? domain.getInstallmentStatus().toString() : null
        );
    }
}

