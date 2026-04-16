package br.com.emanuel.emprestai.domain.ports.outbound;

import br.com.emanuel.emprestai.domain.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer client);
    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    void deleteById(Long id);
    Optional<Customer> findByCpf(String cpf);
    List<Customer> findByAdministratorId(Long administratorId);
}
