package br.com.emanuel.emprestai.adapter.outbound.persistence;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.LoanEntity;
import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.InstallmentEntity;
import br.com.emanuel.emprestai.domain.model.Customer;
import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.CustomerEntity;
import br.com.emanuel.emprestai.domain.model.Loan;
import br.com.emanuel.emprestai.domain.model.Installment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class CustomerPersistenceMapper {

    public CustomerPersistenceMapper() {
    }
    public Customer toDomain(CustomerEntity entity) {
        if (entity == null) return null;

        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setName(entity.getName());
        customer.setCpf(entity.getCpf());
        customer.setAddress(entity.getAddress());
        customer.setPhone(entity.getPhone());
        customer.setEmail(entity.getEmail());
        customer.setPassword(entity.getPassword());
        customer.setRole(entity.getRole());
        customer.setAdministratorId(entity.getAdministrator().getId());
        customer.setActive(entity.getActive());

        // Mapear os empréstimos
        if (entity.getLoans() != null && !entity.getLoans().isEmpty()) {
            customer.setLoans(
                    entity.getLoans().stream()
                            .map(this::loanEntityToDomain)
                            .collect(Collectors.toList())
            );
        } else {
            customer.setLoans(new ArrayList<>());
        }

        return customer;
    }

    public CustomerEntity toEntity(Customer domain) {
        if (domain == null) return null;

        CustomerEntity entity = new CustomerEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCpf(domain.getCpf());
        entity.setAddress(domain.getAddress());
        entity.setPhone(domain.getPhone());

        // Mapear os empréstimos
        if (domain.getLoans() != null && !domain.getLoans().isEmpty()) {
            entity.setLoans(
                    domain.getLoans().stream()
                            .map(loan -> loanDomainToEntity(loan, entity))
                            .collect(Collectors.toList())
            );
        } else {
            entity.setLoans(new ArrayList<>());
        }

        return entity;
    }

    private Loan loanEntityToDomain(LoanEntity entity) {
        if (entity == null) return null;

        Loan loan = new Loan();
        loan.setId(entity.getId());
        loan.setCustomerId(entity.getCustomer().getId());
        loan.setAmount(entity.getAmount());
        loan.setStartDate(entity.getStartDate());
        loan.setLoanStatus(entity.getLoanStatus());
        loan.setInstallments(new ArrayList<>());

        // Mapear installments
        if (entity.getInstallments() != null && !entity.getInstallments().isEmpty()) {
            loan.setInstallments(
                entity.getInstallments().stream()
                    .map(this::installmentEntityToDomain)
                    .collect(Collectors.toList())
            );
        }

        return loan;
    }

    private LoanEntity loanDomainToEntity(Loan domain, CustomerEntity customer) {
        if (domain == null) return null;

        LoanEntity entity = new LoanEntity();
        entity.setId(domain.getId());
        entity.setCustomer(customer);
        entity.setAmount(domain.getAmount());
        entity.setStartDate(domain.getStartDate());
        entity.setLoanStatus(domain.getLoanStatus());

        // Mapear installments
        if (domain.getInstallments() != null && !domain.getInstallments().isEmpty()) {
            entity.setInstallments(
                domain.getInstallments().stream()
                    .map(installment -> installmentDomainToEntity(installment, entity))
                    .collect(Collectors.toList())
            );
        }

        return entity;
    }

    private Installment installmentEntityToDomain(InstallmentEntity entity) {
        if (entity == null) return null;

        Installment installment = new Installment();
        installment.setId(entity.getId());
        installment.setLoanId(entity.getLoan().getId());
        installment.setNumber(entity.getNumber());
        installment.setAmount(entity.getAmount());
        installment.setDueDate(entity.getDueDate());
        installment.setInstallmentStatus(entity.getInstallmentStatus());

        return installment;
    }

    private InstallmentEntity installmentDomainToEntity(Installment domain, LoanEntity loan) {
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
}
