package br.com.emanuel.emprestai.application.service;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.LoginResponse;
import br.com.emanuel.emprestai.application.usecases.LoginUseCase;
import br.com.emanuel.emprestai.common.exceptions.ValidationException;
import br.com.emanuel.emprestai.domain.model.Customer;
import br.com.emanuel.emprestai.domain.model.Store;
import br.com.emanuel.emprestai.domain.model.Administrator;
import br.com.emanuel.emprestai.domain.ports.outbound.CustomerRepository;
import br.com.emanuel.emprestai.domain.ports.outbound.StoreRepository;
import br.com.emanuel.emprestai.domain.ports.outbound.AdministratorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements LoginUseCase {
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AdministratorRepository administratorRepository;
    private final PasswordEncoderService passwordEncoder;

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
}

