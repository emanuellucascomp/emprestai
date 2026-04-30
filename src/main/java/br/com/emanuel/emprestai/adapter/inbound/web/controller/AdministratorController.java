package br.com.emanuel.emprestai.adapter.inbound.web.controller;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.AdministratorResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateProfileRequest;
import br.com.emanuel.emprestai.application.usecases.ListAdministratorCustomersUseCase;
import br.com.emanuel.emprestai.application.usecases.ListAdministratorStoresUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateAdministratorProfileUseCase;
import br.com.emanuel.emprestai.application.service.AdministratorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {
    private final ListAdministratorCustomersUseCase listCustomersUseCase;
    private final ListAdministratorStoresUseCase listStoresUseCase;
    private final UpdateAdministratorProfileUseCase updateProfileUseCase;
    private final AdministratorService administratorService;

    public AdministratorController(ListAdministratorCustomersUseCase listCustomersUseCase,
                                   ListAdministratorStoresUseCase listStoresUseCase,
                                   UpdateAdministratorProfileUseCase updateProfileUseCase,
                                   AdministratorService administratorService) {
        this.listCustomersUseCase = listCustomersUseCase;
        this.listStoresUseCase = listStoresUseCase;
        this.updateProfileUseCase = updateProfileUseCase;
        this.administratorService = administratorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorResponse> getById(@PathVariable Long id) {
        AdministratorResponse response = administratorService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{administratorId}/customers")
    public ResponseEntity<List<CustomerResponse>> listCustomers(@PathVariable Long administratorId) {
        List<CustomerResponse> customers = listCustomersUseCase.listCustomers(administratorId);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{administratorId}/stores")
    public ResponseEntity<List<StoreResponse>> listStores(@PathVariable Long administratorId) {
        List<StoreResponse> stores = listStoresUseCase.listStores(administratorId);
        return ResponseEntity.ok(stores);
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<AdministratorResponse> updateProfile(@PathVariable Long id,
                                                              @Valid @RequestBody UpdateProfileRequest request) {
        AdministratorResponse response = updateProfileUseCase.updateProfile(id, request);
        return ResponseEntity.ok(response);
    }
}

