package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;

public interface GetCustomerUseCase {
    CustomerResponse getById(Long id);
}