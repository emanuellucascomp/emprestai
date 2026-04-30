package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanResponse;

public interface CreateLoanUseCase {
    LoanResponse create(LoanRequest request);
}

