package br.com.emanuel.emprestai.application.service;

import br.com.emanuel.emprestai.adapter.inbound.mapper.CustomerMapper;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.UpdateProfileRequest;
import br.com.emanuel.emprestai.domain.model.Customer;
import br.com.emanuel.emprestai.domain.ports.outbound.CustomerRepository;
import br.com.emanuel.emprestai.application.usecases.CreateCustomerUseCase;
import br.com.emanuel.emprestai.application.usecases.GetCustomerUseCase;
import br.com.emanuel.emprestai.application.usecases.ListCustomersUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateCustomerUseCase;
import br.com.emanuel.emprestai.application.usecases.DeleteCustomerUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateCustomerProfileUseCase;
import br.com.emanuel.emprestai.common.exceptions.NotFoundException;
import br.com.emanuel.emprestai.common.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService implements
        CreateCustomerUseCase,
        GetCustomerUseCase,
        ListCustomersUseCase,
        UpdateCustomerUseCase,
        DeleteCustomerUseCase,
        UpdateCustomerProfileUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    private final PasswordEncoderService passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper mapper, PasswordEncoderService passwordEncoder) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        customerRepository.findByCpf(request.getCpf()).ifPresent(c -> {
            throw new ValidationException("CPF já cadastrado");
        });
        Customer c = mapper.toDomain(request);
        Customer saved = customerRepository.save(c);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado com id: " + id));
        return mapper.toResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> list() {
        List<Customer> customers = customerRepository.findAll();
        return mapper.toResponseList(customers);
    }

    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado com id: " + id));

        if (request.getCpf() != null && !request.getCpf().equals(existing.getCpf())) {
            customerRepository.findByCpf(request.getCpf()).ifPresent(c -> {
                throw new ValidationException("CPF já cadastrado para outro cliente");
            });
        }

        mapper.updateDomainFromRequest(existing, request);
        existing.setId(id);

        Customer saved = customerRepository.save(existing);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boolean exists = customerRepository.findById(id).isPresent();
        if (!exists) {
            throw new NotFoundException("Cliente não encontrado com id: " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CustomerResponse updateProfile(Long customerId, UpdateProfileRequest request) {
        Customer existing = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado com id: " + customerId));

        // Validar senha atual
        if (request.getCurrentPassword() == null || request.getCurrentPassword().isEmpty()) {
            throw new ValidationException("Senha atual é obrigatória para atualizar o perfil");
        }

        if (!passwordEncoder.matches(request.getCurrentPassword(), existing.getPassword())) {
            throw new ValidationException("Senha atual inválida");
        }

        // Atualizar informações do perfil
        if (request.getName() != null && !request.getName().isEmpty()) {
            existing.setName(request.getName());
        }
        if (request.getAddress() != null && !request.getAddress().isEmpty()) {
            existing.setAddress(request.getAddress());
        }
        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            existing.setPhone(request.getPhone());
        }
        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            existing.setEmail(request.getEmail());
        }

        // Atualizar senha se fornecida
        if (request.getNewPassword() != null && !request.getNewPassword().isEmpty()) {
            if (request.getNewPassword().length() < 6) {
                throw new ValidationException("Nova senha deve ter no mínimo 6 caracteres");
            }
            existing.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        Customer saved = customerRepository.save(existing);
        return mapper.toResponse(saved);
    }
}
