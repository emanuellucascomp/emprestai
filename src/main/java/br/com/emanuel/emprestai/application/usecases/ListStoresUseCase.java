package br.com.emanuel.emprestai.application.usecases;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;

import java.util.List;

public interface ListStoresUseCase {
    List<StoreResponse> list();
}
