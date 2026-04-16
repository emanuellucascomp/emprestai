package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;

public interface CreateCustomerUseCase {
    CustomerResponse create(CustomerRequest request);
}
