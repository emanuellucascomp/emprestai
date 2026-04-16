package br.com.emanuel.emprestai.application.service;

import br.com.emanuel.emprestai.adapter.inbound.mapper.CustomerMapper;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.CustomerResponse;
import br.com.emanuel.emprestai.domain.model.Customer;
import br.com.emanuel.emprestai.domain.ports.outbound.CustomerRepository;
import br.com.emanuel.emprestai.application.usecases.CreateCustomerUseCase;
import br.com.emanuel.emprestai.application.usecases.GetCustomerUseCase;
import br.com.emanuel.emprestai.application.usecases.ListCustomersUseCase;
import br.com.emanuel.emprestai.application.usecases.UpdateCustomerUseCase;
import br.com.emanuel.emprestai.application.usecases.DeleteCustomerUseCase;
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
        DeleteCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
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
}
