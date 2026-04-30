package br.com.emanuel.emprestai.adapter.inbound.web.controller;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateProfileRequest;
import br.com.emanuel.emprestai.application.usecases.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    private final UpdateStoreProfileUseCase updateProfileUseCase;

    public StoreController(CreateStoreUseCase createUseCase,
                           GetStoreUseCase getUseCase,
                           ListStoresUseCase listUseCase,
                           UpdateStoreUseCase updateUseCase,
                           DeleteStoreUseCase deleteUseCase,
                           UpdateStoreProfileUseCase updateProfileUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.updateProfileUseCase = updateProfileUseCase;
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

    @PutMapping("/{id}/profile")
    public ResponseEntity<StoreResponse> updateProfile(@PathVariable Long id,
                                                       @Valid @RequestBody UpdateProfileRequest request) {
        StoreResponse response = updateProfileUseCase.updateProfile(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteUseCase.delete(id);
    }
}
