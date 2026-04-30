package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.RegisterAdministratorRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.AdministratorResponse;

public interface RegisterAdministratorUseCase {
    AdministratorResponse register(RegisterAdministratorRequest request);
}

