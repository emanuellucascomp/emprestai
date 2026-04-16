package br.com.emanuel.emprestai.adapter.outbound.persistence;

import org.springframework.stereotype.Component;
import br.com.emanuel.emprestai.domain.ports.outbound.CustomerRepository;
import br.com.emanuel.emprestai.adapter.outbound.persistence.springdata.SpringDataCustomerRepository;
import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.CustomerEntity;
import br.com.emanuel.emprestai.domain.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final SpringDataCustomerRepository springRepo;
    private final CustomerPersistenceMapper mapper;

    public CustomerRepositoryImpl(SpringDataCustomerRepository springRepo, CustomerPersistenceMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Customer save(Customer client) {
        CustomerEntity entity = mapper.toEntity(client);
        CustomerEntity saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return ((List<CustomerEntity>) springRepo.findAll()).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    @Override
    public Optional<Customer> findByCpf(String cpf) {
        return springRepo.findByCpf(cpf).map(mapper::toDomain);
    }

    @Override
    public List<Customer> findByAdministratorId(Long administratorId) {
        return springRepo.findByAdministratorId(administratorId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
