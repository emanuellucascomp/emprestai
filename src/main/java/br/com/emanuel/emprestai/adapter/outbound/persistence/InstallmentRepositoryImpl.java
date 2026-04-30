package br.com.emanuel.emprestai.adapter.outbound.persistence;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.InstallmentEntity;
import br.com.emanuel.emprestai.adapter.outbound.persistence.springdata.SpringDataInstallmentRepository;
import br.com.emanuel.emprestai.domain.model.Installment;
import br.com.emanuel.emprestai.domain.ports.outbound.InstallmentRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InstallmentRepositoryImpl implements InstallmentRepository {
    private final SpringDataInstallmentRepository springRepo;
    private final InstallmentPersistenceMapper mapper;

    public InstallmentRepositoryImpl(SpringDataInstallmentRepository springRepo, InstallmentPersistenceMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Installment save(Installment installment) {
        InstallmentEntity entity = mapper.toEntity(installment);
        InstallmentEntity saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Installment> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Installment> findByLoanId(Long loanId) {
        return springRepo.findByLoanId(loanId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }
}

