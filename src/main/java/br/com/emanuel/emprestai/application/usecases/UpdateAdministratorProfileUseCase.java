package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateProfileRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.AdministratorResponse;

public interface UpdateAdministratorProfileUseCase {
    AdministratorResponse updateProfile(Long administratorId, UpdateProfileRequest request);
}

