package br.com.emanuel.emprestai.domain.ports.outbound;

import br.com.emanuel.emprestai.domain.model.Loan;
import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    Loan save(Loan loan);
    Optional<Loan> findById(Long id);
    List<Loan> findAll();
    List<Loan> findByCustomerId(Long customerId);
    void deleteById(Long id);
}

