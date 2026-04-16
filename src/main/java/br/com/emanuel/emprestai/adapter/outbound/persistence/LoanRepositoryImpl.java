package br.com.emanuel.emprestai.adapter.outbound.persistence;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.LoanEntity;
import br.com.emanuel.emprestai.adapter.outbound.persistence.springdata.SpringDataLoanRepository;
import br.com.emanuel.emprestai.domain.model.Loan;
import br.com.emanuel.emprestai.domain.ports.outbound.LoanRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LoanRepositoryImpl implements LoanRepository {
    private final SpringDataLoanRepository springRepo;
    private final LoanPersistenceMapper mapper;

    public LoanRepositoryImpl(SpringDataLoanRepository springRepo, LoanPersistenceMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Loan save(Loan loan) {
        LoanEntity entity = mapper.toEntity(loan);
        LoanEntity saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Loan> findAll() {
        return ((List<LoanEntity>) springRepo.findAll()).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findByCustomerId(Long customerId) {
        return springRepo.findByCustomerId(customerId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }
}

