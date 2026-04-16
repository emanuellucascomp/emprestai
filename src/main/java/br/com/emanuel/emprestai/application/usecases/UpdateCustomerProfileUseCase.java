package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateProfileRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;

public interface UpdateCustomerProfileUseCase {
    CustomerResponse updateProfile(Long customerId, UpdateProfileRequest request);
}

