package br.com.emanuel.emprestai.adapter.inbound.web.controller;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;
import br.com.emanuel.emprestai.application.usecases.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final CreateStoreUseCase createUseCase;
    private final GetStoreUseCase getUseCase;
    private final ListStoresUseCase listUseCase;
    private final UpdateStoreUseCase updateUseCase;
    private final DeleteStoreUseCase deleteUseCase;

    public StoreController(CreateStoreUseCase createUseCase,
                           GetStoreUseCase getUseCase,
                           ListStoresUseCase listUseCase,
                           UpdateStoreUseCase updateUseCase,
                           DeleteStoreUseCase deleteUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public StoreResponse create(@Valid @RequestBody StoreRequest request) {
        return createUseCase.create(request);
    }

    @GetMapping("/{id}")
    public StoreResponse get(@PathVariable Long id) {
        return getUseCase.getStore(id);
    }

    @GetMapping
    public List<StoreResponse> list() {
        return listUseCase.list();
    }

    @PutMapping("/{id}")
    public StoreResponse update(@PathVariable Long id, @Valid @RequestBody StoreRequest request) {
        return updateUseCase.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteUseCase.delete(id);
    }
}
