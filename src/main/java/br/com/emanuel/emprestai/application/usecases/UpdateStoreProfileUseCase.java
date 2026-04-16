package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateProfileRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;

public interface UpdateStoreProfileUseCase {
    StoreResponse updateProfile(Long storeId, UpdateProfileRequest request);
}

