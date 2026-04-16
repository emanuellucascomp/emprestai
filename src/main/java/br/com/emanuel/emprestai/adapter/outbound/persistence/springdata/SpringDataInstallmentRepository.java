package br.com.emanuel.emprestai.adapter.outbound.persistence.springdata;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.InstallmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpringDataInstallmentRepository extends CrudRepository<InstallmentEntity, Long> {
    List<InstallmentEntity> findByLoanId(Long loanId);
}

