package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ResetPasswordRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ResetPasswordResponse;

public interface ResetPasswordUseCase {
    ResetPasswordResponse resetPassword(ResetPasswordRequest request);
}

