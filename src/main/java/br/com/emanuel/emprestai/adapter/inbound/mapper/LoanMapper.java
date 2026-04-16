package br.com.emanuel.emprestai.adapter.inbound.mapper;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.InstallmentRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.InstallmentResponse;
import br.com.emanuel.emprestai.domain.model.Loan;
import br.com.emanuel.emprestai.domain.model.Installment;
import br.com.emanuel.emprestai.domain.model.InstallmentStatus;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class LoanMapper {

    public Loan toDomain(LoanRequest request) {
        if (request == null) return null;

        Loan loan = new Loan();
        loan.setCustomerId(request.getCustomerId());
        loan.setAmount(request.getAmount());
        loan.setStartDate(request.getStartDate());

        if (request.getInstallments() != null && !request.getInstallments().isEmpty()) {
            loan.setInstallments(
                request.getInstallments().stream()
                    .map(this::installmentRequestToDomain)
                    .collect(Collectors.toList())
            );
        } else {
            loan.setInstallments(new ArrayList<>());
        }

        return loan;
    }

    public LoanResponse toResponse(Loan domain) {
        if (domain == null) return null;

        LoanResponse response = new LoanResponse();
        response.setId(domain.getId());
        response.setAmount(domain.getAmount());
        response.setStartDate(domain.getStartDate());
        response.setLoanStatus(domain.getLoanStatus() != null ? domain.getLoanStatus().toString() : null);

        if (domain.getInstallments() != null && !domain.getInstallments().isEmpty()) {
            response.setInstallments(
                domain.getInstallments().stream()
                    .map(this::installmentToResponse)
                    .collect(Collectors.toList())
            );
        } else {
            response.setInstallments(new ArrayList<>());
        }

        return response;
    }

    private Installment installmentRequestToDomain(InstallmentRequest request) {
        if (request == null) return null;
        return new Installment(
            null,
            request.getNumber(),
            request.getAmount(),
            request.getDueDate(),
            InstallmentStatus.NOT_PAYED
        );
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

