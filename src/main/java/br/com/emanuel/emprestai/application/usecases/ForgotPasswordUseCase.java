package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ForgotPasswordRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ForgotPasswordResponse;

public interface ForgotPasswordUseCase {
    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);
}

