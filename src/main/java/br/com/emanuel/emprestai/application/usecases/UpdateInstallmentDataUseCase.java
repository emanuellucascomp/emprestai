package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateInstallmentRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.InstallmentResponse;

public interface UpdateInstallmentDataUseCase {
    InstallmentResponse update(Long loanId, Long installmentId, UpdateInstallmentRequest request);
}

