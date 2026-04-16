package br.com.emanuel.emprestai.adapter.outbound.persistence.springdata;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.AdministratorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SpringDataAdministratorRepository extends CrudRepository<AdministratorEntity, Long> {
    Optional<AdministratorEntity> findByCpf(String cpf);
}

