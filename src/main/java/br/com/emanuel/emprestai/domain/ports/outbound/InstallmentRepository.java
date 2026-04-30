package br.com.emanuel.emprestai.domain.ports.outbound;

import br.com.emanuel.emprestai.domain.model.Installment;
import java.util.List;
import java.util.Optional;

public interface InstallmentRepository {
    Installment save(Installment installment);
    Optional<Installment> findById(Long id);
    List<Installment> findByLoanId(Long loanId);
    void deleteById(Long id);
}

