package br.com.emanuel.emprestai.adapter.inbound.web.controller;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoanResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.InstallmentResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateLoanStatusRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateInstallmentStatusRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateLoanRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateInstallmentRequest;
import br.com.emanuel.emprestai.application.usecases.CreateLoanUseCase;
import br.com.emanuel.emprestai.application.usecases.GetLoanUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateLoanStatusUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateInstallmentStatusUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateLoanUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateInstallmentDataUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final CreateLoanUseCase createUseCase;
    private final GetLoanUseCase getUseCase;
    private final UpdateLoanStatusUseCase updateLoanStatusUseCase;
    private final UpdateInstallmentStatusUseCase updateInstallmentStatusUseCase;
    private final UpdateLoanUseCase updateLoanUseCase;
    private final UpdateInstallmentDataUseCase updateInstallmentDataUseCase;

    public LoanController(CreateLoanUseCase createUseCase, GetLoanUseCase getUseCase,
                         UpdateLoanStatusUseCase updateLoanStatusUseCase,
                         UpdateInstallmentStatusUseCase updateInstallmentStatusUseCase,
                         UpdateLoanUseCase updateLoanUseCase,
                         UpdateInstallmentDataUseCase updateInstallmentDataUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.updateLoanStatusUseCase = updateLoanStatusUseCase;
        this.updateInstallmentStatusUseCase = updateInstallmentStatusUseCase;
        this.updateLoanUseCase = updateLoanUseCase;
        this.updateInstallmentDataUseCase = updateInstallmentDataUseCase;
    }

    @PostMapping
    public ResponseEntity<LoanResponse> create(@Valid @RequestBody LoanRequest request) {
        LoanResponse response = createUseCase.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getById(@PathVariable Long id) {
        LoanResponse response = getUseCase.getById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<LoanResponse> updateLoanStatus(@PathVariable Long id,
                                                         @Valid @RequestBody UpdateLoanStatusRequest request) {
        LoanResponse response = updateLoanStatusUseCase.updateStatus(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{loanId}/installments/{installmentId}/status")
    public ResponseEntity<InstallmentResponse> updateInstallmentStatus(
            @PathVariable Long loanId,
            @PathVariable Long installmentId,
            @Valid @RequestBody UpdateInstallmentStatusRequest request) {
        InstallmentResponse response = updateInstallmentStatusUseCase.updateStatus(loanId, installmentId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanResponse> updateLoan(@PathVariable Long id,
                                                    @Valid @RequestBody UpdateLoanRequest request) {
        LoanResponse response = updateLoanUseCase.update(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{loanId}/installments/{installmentId}")
    public ResponseEntity<InstallmentResponse> updateInstallment(
            @PathVariable Long loanId,
            @PathVariable Long installmentId,
            @Valid @RequestBody UpdateInstallmentRequest request) {
        InstallmentResponse response = updateInstallmentDataUseCase.update(loanId, installmentId, request);
        return ResponseEntity.ok(response);
    }
}

