package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateLoanStatusRequest;

public interface UpdateLoanStatusUseCase {
    LoanResponse updateStatus(Long id, UpdateLoanStatusRequest request);
}

