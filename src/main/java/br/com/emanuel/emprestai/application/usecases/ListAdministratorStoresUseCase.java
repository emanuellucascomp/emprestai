package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;
import java.util.List;

public interface ListAdministratorStoresUseCase {
    List<StoreResponse> listStores(Long administratorId);
}

