package br.com.emanuel.emprestai.adapter.outbound.persistence;

import org.springframework.stereotype.Component;
import br.com.emanuel.emprestai.domain.ports.outbound.StoreRepository;
import br.com.emanuel.emprestai.adapter.outbound.persistence.springdata.SpringDataStoreRepository;
import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.StoreEntity;
import br.com.emanuel.emprestai.domain.model.Store;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StoreRepositoryImpl implements StoreRepository {

    private final SpringDataStoreRepository springRepo;
    private final StorePersistenceMapper mapper;

    public StoreRepositoryImpl(SpringDataStoreRepository springRepo, StorePersistenceMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Store save(Store store) {
        StoreEntity entity = mapper.toEntity(store);
        StoreEntity saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Store> findById(Long id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Store> findAll() {
        return ((List<StoreEntity>) springRepo.findAll()).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springRepo.deleteById(id);
    }

    @Override
    public Optional<Store> findByCnpj(String cnpj) {
        return springRepo.findByCnpj(cnpj).map(mapper::toDomain);
    }

    @Override
    public List<Store> findByAdministratorId(Long administratorId) {
        return springRepo.findByAdministratorId(administratorId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
