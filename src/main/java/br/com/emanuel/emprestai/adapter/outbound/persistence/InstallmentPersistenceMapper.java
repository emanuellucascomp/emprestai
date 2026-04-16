package br.com.emanuel.emprestai.adapter.outbound.persistence;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.InstallmentEntity;
import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.LoanEntity;
import br.com.emanuel.emprestai.domain.model.Installment;
import org.springframework.stereotype.Component;

@Component
public class InstallmentPersistenceMapper {

    public Installment toDomain(InstallmentEntity entity) {
        if (entity == null) return null;

        return new Installment(
            entity.getId(),
            entity.getNumber(),
            entity.getAmount(),
            entity.getDueDate(),
            entity.getInstallmentStatus()
        );
    }

    public InstallmentEntity toEntity(Installment domain, LoanEntity loan) {
        if (domain == null) return null;

        InstallmentEntity entity = new InstallmentEntity();
        entity.setId(domain.getId());
        entity.setLoan(loan);
        entity.setNumber(domain.getNumber());
        entity.setAmount(domain.getAmount());
        entity.setDueDate(domain.getDueDate());
        entity.setInstallmentStatus(domain.getInstallmentStatus());

        return entity;
    }

    public InstallmentEntity toEntity(Installment domain) {
        if (domain == null) return null;

        InstallmentEntity entity = new InstallmentEntity();
        entity.setId(domain.getId());
        entity.setNumber(domain.getNumber());
        entity.setAmount(domain.getAmount());
        entity.setDueDate(domain.getDueDate());
        entity.setInstallmentStatus(domain.getInstallmentStatus());

        return entity;
    }
}

