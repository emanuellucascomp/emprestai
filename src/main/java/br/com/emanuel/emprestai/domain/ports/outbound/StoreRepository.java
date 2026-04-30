package br.com.emanuel.emprestai.domain.ports.outbound;

import br.com.emanuel.emprestai.domain.model.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    Store save(Store store);
    Optional<Store> findById(Long id);
    List<Store> findAll();
    void deleteById(Long id);
    Optional<Store> findByCnpj(String cnpj);
    List<Store> findByAdministratorId(Long administratorId);
}
