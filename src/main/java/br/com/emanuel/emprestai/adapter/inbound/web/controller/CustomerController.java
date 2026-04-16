package br.com.emanuel.emprestai.adapter.inbound.web.controller;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateProfileRequest;
import br.com.emanuel.emprestai.application.usecases.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomerUseCase createUseCase;
    private final GetCustomerUseCase getUseCase;
    private final ListCustomersUseCase listUseCase;
    private final UpdateCustomerUseCase updateUseCase;
    private final DeleteCustomerUseCase deleteUseCase;
    private final UpdateCustomerProfileUseCase updateProfileUseCase;

    public CustomerController(CreateCustomerUseCase createUseCase,
                            GetCustomerUseCase getUseCase,
                            ListCustomersUseCase listUseCase,
                            UpdateCustomerUseCase updateUseCase,
                            DeleteCustomerUseCase deleteUseCase,
                            UpdateCustomerProfileUseCase updateProfileUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.updateProfileUseCase = updateProfileUseCase;
    }

    @PostMapping
    public CustomerResponse create(@Valid @RequestBody CustomerRequest request) {
        return createUseCase.create(request);
    }

    @GetMapping("/{id}")
    public CustomerResponse get(@PathVariable Long id) {
        return getUseCase.getById(id);
    }

    @GetMapping
    public List<CustomerResponse> list() {
        return listUseCase.list();
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
        return updateUseCase.update(id, request);
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<CustomerResponse> updateProfile(@PathVariable Long id,
                                                          @Valid @RequestBody UpdateProfileRequest request) {
        CustomerResponse response = updateProfileUseCase.updateProfile(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteUseCase.delete(id);
    }
}
