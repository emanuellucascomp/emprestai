package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.InstallmentResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateInstallmentStatusRequest;

public interface UpdateInstallmentStatusUseCase {
    InstallmentResponse updateStatus(Long loanId, Long installmentId, UpdateInstallmentStatusRequest request);
}

