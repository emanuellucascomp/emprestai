package br.com.emanuel.emprestai.adapter.outbound.persistence;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.LoanEntity;
import br.com.emanuel.emprestai.domain.model.Loan;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class LoanPersistenceMapper {
    private final InstallmentPersistenceMapper installmentMapper;

    public LoanPersistenceMapper(InstallmentPersistenceMapper installmentMapper) {
        this.installmentMapper = installmentMapper;
    }

    public Loan toDomain(LoanEntity entity) {
        if (entity == null) return null;

        Loan loan = new Loan();
        loan.setId(entity.getId());
        loan.setCustomerId(entity.getCustomer().getId());
        loan.setAmount(entity.getAmount());
        loan.setStartDate(entity.getStartDate());
        loan.setLoanStatus(entity.getLoanStatus());

        if (entity.getInstallments() != null && !entity.getInstallments().isEmpty()) {
            loan.setInstallments(
                entity.getInstallments().stream()
                    .map(installmentMapper::toDomain)
                    .collect(Collectors.toList())
            );
        } else {
            loan.setInstallments(new ArrayList<>());
        }

        return loan;
    }

    public LoanEntity toEntity(Loan domain) {
        if (domain == null) return null;

        LoanEntity entity = new LoanEntity();
        entity.setId(domain.getId());
        entity.setAmount(domain.getAmount());
        entity.setStartDate(domain.getStartDate());
        entity.setLoanStatus(domain.getLoanStatus());

        if (domain.getInstallments() != null && !domain.getInstallments().isEmpty()) {
            entity.setInstallments(
                domain.getInstallments().stream()
                    .map(installment -> installmentMapper.toEntity(installment, entity))
                    .collect(Collectors.toList())
            );
        } else {
            entity.setInstallments(new ArrayList<>());
        }

        return entity;
    }
}

