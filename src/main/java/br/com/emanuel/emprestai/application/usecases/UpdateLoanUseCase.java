package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateLoanRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanResponse;

public interface UpdateLoanUseCase {
    LoanResponse update(Long id, UpdateLoanRequest request);
}

