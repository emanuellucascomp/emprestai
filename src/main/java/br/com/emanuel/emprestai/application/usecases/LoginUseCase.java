package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest request);
}

