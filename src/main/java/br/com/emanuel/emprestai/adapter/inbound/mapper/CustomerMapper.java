package br.com.emanuel.emprestai.adapter.inbound.mapper;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.InstallmentResponse;
import br.com.emanuel.emprestai.domain.model.Customer;
import br.com.emanuel.emprestai.domain.model.Loan;
import br.com.emanuel.emprestai.domain.model.Installment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public Customer toDomain(CustomerRequest request) {
        if (request == null) return null;
        Customer c = new Customer();
        c.setName(request.getName());
        c.setCpf(request.getCpf());
        c.setAddress(request.getAddress());
        c.setPhone(request.getPhone());
        c.setLoans(new ArrayList<>());
        return c;
    }

    public CustomerResponse toResponse(Customer domain) {
        if (domain == null) return null;
        CustomerResponse resp = new CustomerResponse();
        resp.setId(domain.getId());
        resp.setName(domain.getName());
        resp.setCpf(domain.getCpf());
        resp.setAddress(domain.getAddress());
        resp.setPhone(domain.getPhone());

        // Mapear loans
        if (domain.getLoans() != null) {
            resp.setLoans(
                domain.getLoans().stream()
                    .map(this::loanToResponse)
                    .collect(Collectors.toList())
            );
        } else {
            resp.setLoans(new ArrayList<>());
        }

        return resp;
    }

    public List<CustomerResponse> toResponseList(List<Customer> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void updateDomainFromRequest(Customer domain, CustomerRequest request) {
        if (domain == null || request == null) return;
        domain.setName(request.getName());
        domain.setCpf(request.getCpf());
        domain.setAddress(request.getAddress());
        domain.setPhone(request.getPhone());
    }

    private LoanResponse loanToResponse(Loan loan) {
        if (loan == null) return null;
        LoanResponse response = new LoanResponse();
        response.setId(loan.getId());
        response.setAmount(loan.getAmount());
        response.setStartDate(loan.getStartDate());
        response.setLoanStatus(loan.getLoanStatus() != null ? loan.getLoanStatus().toString() : null);

        if (loan.getInstallments() != null) {
            response.setInstallments(
                loan.getInstallments().stream()
                    .map(this::installmentToResponse)
                    .collect(Collectors.toList())
            );
        }

        return response;
    }

    private InstallmentResponse installmentToResponse(Installment installment) {
        if (installment == null) return null;
        return new InstallmentResponse(
            installment.getId(),
            installment.getNumber(),
            installment.getAmount(),
            installment.getDueDate(),
            installment.getInstallmentStatus() != null ? installment.getInstallmentStatus().toString() : null
        );
    }
}
