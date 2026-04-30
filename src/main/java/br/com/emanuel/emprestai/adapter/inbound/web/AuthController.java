package br.com.emanuel.emprestai.adapter.inbound.web;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ForgotPasswordRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ForgotPasswordResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ResetPasswordRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ResetPasswordResponse;
import br.com.emanuel.emprestai.application.usecases.LoginUseCase;
import br.com.emanuel.emprestai.application.usecases.ForgotPasswordUseCase;
import br.com.emanuel.emprestai.application.usecases.ResetPasswordUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final ForgotPasswordUseCase forgotPasswordUseCase;
    private final ResetPasswordUseCase resetPasswordUseCase;

    public AuthController(LoginUseCase loginUseCase,
                         ForgotPasswordUseCase forgotPasswordUseCase,
                         ResetPasswordUseCase resetPasswordUseCase) {
        this.loginUseCase = loginUseCase;
        this.forgotPasswordUseCase = forgotPasswordUseCase;
        this.resetPasswordUseCase = resetPasswordUseCase;
    }

    /**
     * Realizar login no sistema (CPF/CNPJ + Senha)
     * @param request Contém document (CPF/CNPJ) e password
     * @return LoginResponse com dados do usuário autenticado
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginUseCase.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Solicitar reset de senha (Forgot Password)
     * Envia um token para o email do usuário
     * @param request Contém o document (CPF/CNPJ) do usuário
     * @return ForgotPasswordResponse com o token de reset
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        ForgotPasswordResponse response = forgotPasswordUseCase.forgotPassword(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Resetar a senha usando o token recebido
     * @param request Contém token, newPassword e confirmPassword
     * @return ResetPasswordResponse confirmando o reset
     */
    @PostMapping("/reset-password")
    public ResponseEntity<ResetPasswordResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        ResetPasswordResponse response = resetPasswordUseCase.resetPassword(request);
        return ResponseEntity.ok(response);
    }
}

