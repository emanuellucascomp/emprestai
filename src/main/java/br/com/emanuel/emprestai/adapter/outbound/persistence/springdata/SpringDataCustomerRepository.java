package br.com.emanuel.emprestai.adapter.outbound.persistence.springdata;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataCustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByCpf(String cpf);
    List<CustomerEntity> findByAdministratorId(Long administratorId);
}
