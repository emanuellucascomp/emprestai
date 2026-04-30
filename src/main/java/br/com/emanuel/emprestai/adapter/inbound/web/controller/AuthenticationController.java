package br.com.emanuel.emprestai.adapter.inbound.web.controller;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.RegisterAdministratorRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.AdministratorResponse;
import br.com.emanuel.emprestai.application.usecases.LoginUseCase;
import br.com.emanuel.emprestai.application.usecases.RegisterAdministratorUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final LoginUseCase loginUseCase;
    private final RegisterAdministratorUseCase registerAdministratorUseCase;

    public AuthenticationController(LoginUseCase loginUseCase, RegisterAdministratorUseCase registerAdministratorUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerAdministratorUseCase = registerAdministratorUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginUseCase.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register-admin")
    public ResponseEntity<AdministratorResponse> registerAdministrator(
            @Valid @RequestBody RegisterAdministratorRequest request) {
        AdministratorResponse response = registerAdministratorUseCase.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

