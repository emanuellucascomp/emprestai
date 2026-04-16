package br.com.emanuel.emprestai.adapter.outbound.persistence.springdata;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataStoreRepository extends CrudRepository<StoreEntity, Long> {
    Optional<StoreEntity> findByCnpj(String cnpj);
    List<StoreEntity> findByAdministratorId(Long administratorId);
}
