package br.com.emanuel.emprestai.application.service;

import br.com.emanuel.emprestai.adapter.inbound.mapper.StoreMapper;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;
import br.com.emanuel.emprestai.domain.model.Store;
import br.com.emanuel.emprestai.domain.ports.outbound.StoreRepository;
import br.com.emanuel.emprestai.application.usecases.CreateStoreUseCase;
import br.com.emanuel.emprestai.application.usecases.GetStoreUseCase;
import br.com.emanuel.emprestai.application.usecases.ListStoresUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateStoreUseCase;
import br.com.emanuel.emprestai.application.usecases.DeleteStoreUseCase;
import br.com.emanuel.emprestai.common.exceptions.NotFoundException;
import br.com.emanuel.emprestai.common.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreService implements
        CreateStoreUseCase,
        GetStoreUseCase,
        ListStoresUseCase,
        UpdateStoreUseCase,
        DeleteStoreUseCase {

    private final StoreRepository storeRepository;
    private final StoreMapper mapper;

    public StoreService(StoreRepository storeRepository, StoreMapper mapper) {
        this.storeRepository = storeRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public StoreResponse create(StoreRequest request) {
        storeRepository.findByCnpj(request.getCnpj()).ifPresent(s -> {
            throw new ValidationException("CNPJ já cadastrado");
        });
        Store s = mapper.toDomain(request);
        Store saved = storeRepository.save(s);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public StoreResponse getStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loja não encontrada com id: " + id));
        return mapper.toResponse(store);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreResponse> list() {
        List<Store> stores = storeRepository.findAll();
        return mapper.toResponseList(stores);
    }

    @Override
    @Transactional
    public StoreResponse update(Long id, StoreRequest request) {
        Store existing = storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loja não encontrada com id: " + id));

        if (request.getCnpj() != null && !request.getCnpj().equals(existing.getCnpj())) {
            storeRepository.findByCnpj(request.getCnpj()).ifPresent(s -> {
                throw new ValidationException("CNPJ já cadastrado para outra loja");
            });
        }

        mapper.updateDomainFromRequest(existing, request);
        existing.setId(id);

        Store saved = storeRepository.save(existing);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boolean exists = storeRepository.findById(id).isPresent();
        if (!exists) {
            throw new NotFoundException("Loja não encontrada com id: " + id);
        }
        storeRepository.deleteById(id);
    }
}
