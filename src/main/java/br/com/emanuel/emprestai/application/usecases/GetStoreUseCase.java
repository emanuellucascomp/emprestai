package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;

public interface GetStoreUseCase {
    StoreResponse getStore(Long id);
}
