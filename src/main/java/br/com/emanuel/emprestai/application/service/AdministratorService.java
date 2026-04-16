package br.com.emanuel.emprestai.application.service;

import br.com.emanuel.emprestai.adapter.inbound.mapper.AdministratorMapper;
import br.com.emanuel.emprestai.adapter.inbound.mapper.CustomerMapper;
import br.com.emanuel.emprestai.adapter.inbound.mapper.StoreMapper;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.RegisterAdministratorRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.AdministratorResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;
import br.com.emanuel.emprestai.application.usecases.RegisterAdministratorUseCase;
import br.com.emanuel.emprestai.application.usecases.ListAdministratorCustomersUseCase;
import br.com.emanuel.emprestai.application.usecases.ListAdministratorStoresUseCase;
import br.com.emanuel.emprestai.common.exceptions.ValidationException;
import br.com.emanuel.emprestai.domain.model.Administrator;
import br.com.emanuel.emprestai.domain.ports.outbound.AdministratorRepository;
import br.com.emanuel.emprestai.domain.ports.outbound.CustomerRepository;
import br.com.emanuel.emprestai.domain.ports.outbound.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministratorService implements RegisterAdministratorUseCase, ListAdministratorCustomersUseCase, ListAdministratorStoresUseCase {
    private final AdministratorRepository administratorRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AdministratorMapper administratorMapper;
    private final CustomerMapper customerMapper;
    private final StoreMapper storeMapper;
    private final PasswordEncoderService passwordEncoder;

    public AdministratorService(AdministratorRepository administratorRepository,
                               CustomerRepository customerRepository,
                               StoreRepository storeRepository,
                               AdministratorMapper administratorMapper,
                               CustomerMapper customerMapper,
                               StoreMapper storeMapper,
                               PasswordEncoderService passwordEncoder) {
        this.administratorRepository = administratorRepository;
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.administratorMapper = administratorMapper;
        this.customerMapper = customerMapper;
        this.storeMapper = storeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public AdministratorResponse register(RegisterAdministratorRequest request) {
        // Validar se CPF já existe
        var existingAdmin = administratorRepository.findByCpf(request.getCpf());
        if (existingAdmin.isPresent()) {
            throw new ValidationException("CPF já cadastrado como administrador");
        }

        // Converter para domain
        Administrator admin = administratorMapper.toDomain(request);

        // Criptografar senha
        admin.setPassword(passwordEncoder.encode(request.getPassword()));

        // Salvar
        Administrator saved = administratorRepository.save(admin);

        return administratorMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public AdministratorResponse getById(Long id) {
        Administrator admin = administratorRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Administrador não encontrado com id: " + id));
        return administratorMapper.toResponse(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> listCustomers(Long administratorId) {
        // Validar se administrador existe
        administratorRepository.findById(administratorId)
                .orElseThrow(() -> new ValidationException("Administrador não encontrado com id: " + administratorId));

        // Buscar clientes do administrador
        return customerRepository.findByAdministratorId(administratorId).stream()
                .map(customerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreResponse> listStores(Long administratorId) {
        // Validar se administrador existe
        administratorRepository.findById(administratorId)
                .orElseThrow(() -> new ValidationException("Administrador não encontrado com id: " + administratorId));

        // Buscar lojas do administrador
        return storeRepository.findByAdministratorId(administratorId).stream()
                .map(storeMapper::toResponse)
                .collect(Collectors.toList());
    }
}

