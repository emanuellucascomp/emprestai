package br.com.emanuel.emprestai.adapter.outbound.persistence.springdata;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpringDataLoanRepository extends CrudRepository<LoanEntity, Long> {
    List<LoanEntity> findByCustomerId(Long customerId);
}

