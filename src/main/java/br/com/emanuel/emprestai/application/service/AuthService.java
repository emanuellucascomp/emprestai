package br.com.emanuel.emprestai.application.service;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ForgotPasswordRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ForgotPasswordResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ResetPasswordRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.ResetPasswordResponse;
import br.com.emanuel.emprestai.application.usecases.LoginUseCase;
import br.com.emanuel.emprestai.application.usecases.ForgotPasswordUseCase;
import br.com.emanuel.emprestai.application.usecases.ResetPasswordUseCase;
import br.com.emanuel.emprestai.common.exceptions.ValidationException;
import br.com.emanuel.emprestai.common.exceptions.NotFoundException;
import br.com.emanuel.emprestai.domain.model.Customer;
import br.com.emanuel.emprestai.domain.model.Store;
import br.com.emanuel.emprestai.domain.model.Administrator;
import br.com.emanuel.emprestai.domain.ports.outbound.CustomerRepository;
import br.com.emanuel.emprestai.domain.ports.outbound.StoreRepository;
import br.com.emanuel.emprestai.domain.ports.outbound.AdministratorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AuthService implements LoginUseCase, ForgotPasswordUseCase, ResetPasswordUseCase {
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AdministratorRepository administratorRepository;
    private final PasswordEncoderService passwordEncoder;
    private static final long TOKEN_EXPIRATION_SECONDS = 3600; // 1 hora

    public AuthService(CustomerRepository customerRepository, StoreRepository storeRepository,
                      AdministratorRepository administratorRepository, PasswordEncoderService passwordEncoder) {
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        String document = request.getDocument().replaceAll("[^0-9]", ""); // Remove formatação
        String password = request.getPassword();

        // Tentar login como Customer
        var customer = customerRepository.findByCpf(document);
        if (customer.isPresent() && customer.get().getPassword() != null) {
            Customer c = customer.get();
            if (passwordEncoder.matches(password, c.getPassword()) && c.getActive()) {
                return new LoginResponse(c.getId(), c.getName(), c.getEmail(), c.getRole());
            }
        }

        // Tentar login como Store
        var store = storeRepository.findByCnpj(document);
        if (store.isPresent() && store.get().getPassword() != null) {
            Store s = store.get();
            if (passwordEncoder.matches(password, s.getPassword()) && s.getActive()) {
                return new LoginResponse(s.getId(), s.getName(), s.getEmail(), s.getRole());
            }
        }

        // Tentar login como Administrator
        var admin = administratorRepository.findByCpf(document);
        if (admin.isPresent() && admin.get().getPassword() != null) {
            Administrator a = admin.get();
            if (passwordEncoder.matches(password, a.getPassword()) && a.getActive()) {
                return new LoginResponse(a.getId(), a.getName(), a.getEmail(), a.getRole());
            }
        }

        throw new ValidationException("Documento ou senha inválidos");
    }

    @Override
    @Transactional
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) {
        String document = request.getDocument().replaceAll("[^0-9]", "");

        // Gerar token único
        String token = generateToken();
        long expirationTime = System.currentTimeMillis() + (TOKEN_EXPIRATION_SECONDS * 1000);

        // Tentar encontrar o usuário (Customer)
        var customer = customerRepository.findByCpf(document);
        if (customer.isPresent()) {
            Customer c = customer.get();
            if (c.getEmail() == null || c.getEmail().isEmpty()) {
                throw new ValidationException("Usuário não possui email cadastrado");
            }
            c.setResetToken(token);
            c.setResetTokenExpiration(expirationTime);
            customerRepository.save(c);
            return new ForgotPasswordResponse(
                "Email de reset de senha enviado com sucesso",
                token,
                TOKEN_EXPIRATION_SECONDS
            );
        }

        // Tentar encontrar o usuário (Store)
        var store = storeRepository.findByCnpj(document);
        if (store.isPresent()) {
            Store s = store.get();
            if (s.getEmail() == null || s.getEmail().isEmpty()) {
                throw new ValidationException("Usuário não possui email cadastrado");
            }
            s.setResetToken(token);
            s.setResetTokenExpiration(expirationTime);
            storeRepository.save(s);
            return new ForgotPasswordResponse(
                "Email de reset de senha enviado com sucesso",
                token,
                TOKEN_EXPIRATION_SECONDS
            );
        }

        // Tentar encontrar o usuário (Administrator)
        var admin = administratorRepository.findByCpf(document);
        if (admin.isPresent()) {
            Administrator a = admin.get();
            a.setResetToken(token);
            a.setResetTokenExpiration(expirationTime);
            administratorRepository.save(a);
            return new ForgotPasswordResponse(
                "Email de reset de senha enviado com sucesso",
                token,
                TOKEN_EXPIRATION_SECONDS
            );
        }

        throw new NotFoundException("Usuário não encontrado com o documento informado");
    }

    @Override
    @Transactional
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {
        String token = request.getToken();
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();

        // Validar senhas
        if (!newPassword.equals(confirmPassword)) {
            throw new ValidationException("As senhas não conferem");
        }

        if (newPassword.length() < 6) {
            throw new ValidationException("Nova senha deve ter no mínimo 6 caracteres");
        }

        // Procurar token em Customer
        var customers = customerRepository.findAll();
        for (Customer c : customers) {
            if (c.getResetToken() != null && c.getResetToken().equals(token)) {
                // Verificar expiração
                if (System.currentTimeMillis() > c.getResetTokenExpiration()) {
                    throw new ValidationException("Token de reset expirou");
                }
                c.setPassword(passwordEncoder.encode(newPassword));
                c.setResetToken(null);
                c.setResetTokenExpiration(null);
                customerRepository.save(c);
                return new ResetPasswordResponse(
                    "Senha resetada com sucesso",
                    c.getId(),
                    c.getName()
                );
            }
        }

        // Procurar token em Store
        var stores = storeRepository.findAll();
        for (Store s : stores) {
            if (s.getResetToken() != null && s.getResetToken().equals(token)) {
                // Verificar expiração
                if (System.currentTimeMillis() > s.getResetTokenExpiration()) {
                    throw new ValidationException("Token de reset expirou");
                }
                s.setPassword(passwordEncoder.encode(newPassword));
                s.setResetToken(null);
                s.setResetTokenExpiration(null);
                storeRepository.save(s);
                return new ResetPasswordResponse(
                    "Senha resetada com sucesso",
                    s.getId(),
                    s.getName()
                );
            }
        }

        // Procurar token em Administrator
        var admins = administratorRepository.findAll();
        for (Administrator a : admins) {
            if (a.getResetToken() != null && a.getResetToken().equals(token)) {
                // Verificar expiração
                if (System.currentTimeMillis() > a.getResetTokenExpiration()) {
                    throw new ValidationException("Token de reset expirou");
                }
                a.setPassword(passwordEncoder.encode(newPassword));
                a.setResetToken(null);
                a.setResetTokenExpiration(null);
                administratorRepository.save(a);
                return new ResetPasswordResponse(
                    "Senha resetada com sucesso",
                    a.getId(),
                    a.getName()
                );
            }
        }

        throw new NotFoundException("Token de reset inválido");
    }

    // Método auxiliar para gerar token seguro
    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] values = new byte[32];
        random.nextBytes(values);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(values);
    }
}

