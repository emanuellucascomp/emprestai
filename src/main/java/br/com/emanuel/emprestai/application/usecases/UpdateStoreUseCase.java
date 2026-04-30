package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;

public interface UpdateStoreUseCase {
    StoreResponse update(Long id, StoreRequest request);
}
