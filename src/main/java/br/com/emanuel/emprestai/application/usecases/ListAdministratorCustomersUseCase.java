package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;
import java.util.List;

public interface ListAdministratorCustomersUseCase {
    List<CustomerResponse> listCustomers(Long administratorId);
}

