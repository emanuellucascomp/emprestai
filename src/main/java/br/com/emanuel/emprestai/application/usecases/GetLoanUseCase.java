package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanResponse;

public interface GetLoanUseCase {
    LoanResponse getById(Long id);
}

