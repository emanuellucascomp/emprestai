package br.com.emanuel.emprestai.adapter.outbound.persistence;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.AdministratorEntity;
import br.com.emanuel.emprestai.adapter.outbound.persistence.springdata.SpringDataAdministratorRepository;
import br.com.emanuel.emprestai.domain.model.Administrator;
import br.com.emanuel.emprestai.domain.ports.outbound.AdministratorRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AdministratorRepositoryImpl implements AdministratorRepository {
    private final SpringDataAdministratorRepository springRepo;
    private final AdministratorPersistenceMapper mapper;

    public AdministratorRepositoryImpl(SpringDataAdministratorRepository springRepo,
                                     AdministratorPersistenceMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Administrator save(Administrator administrator) {
        AdministratorEntity entity = mapper.toEntity(administrator);
        AdministratorEntity saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Administrator> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Administrator> findAll() {
        return ((List<AdministratorEntity>) springRepo.findAll()).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    @Override
    public Optional<Administrator> findByCpf(String cpf) {
        return springRepo.findByCpf(cpf).map(mapper::toDomain);
    }
}

