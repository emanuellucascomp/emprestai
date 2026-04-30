package br.com.emanuel.emprestai.adapter.outbound.persistence;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.AdministratorEntity;
import br.com.emanuel.emprestai.domain.model.Administrator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class AdministratorPersistenceMapper {
    private final CustomerPersistenceMapper customerMapper;
    private final StorePersistenceMapper storeMapper;

    public AdministratorPersistenceMapper(CustomerPersistenceMapper customerMapper,
                                         StorePersistenceMapper storeMapper) {
        this.customerMapper = customerMapper;
        this.storeMapper = storeMapper;
    }

    public Administrator toDomain(AdministratorEntity entity) {
        if (entity == null) return null;

        Administrator admin = new Administrator();
        admin.setId(entity.getId());
        admin.setName(entity.getName());
        admin.setCpf(entity.getCpf());
        admin.setAddress(entity.getAddress());
        admin.setPhone(entity.getPhone());
        admin.setEmail(entity.getEmail());
        admin.setPassword(entity.getPassword());
        admin.setRole(entity.getRole());
        admin.setActive(entity.getActive());

        // Mapear clientes
        if (entity.getCustomers() != null && !entity.getCustomers().isEmpty()) {
            admin.setCustomers(
                entity.getCustomers().stream()
                    .map(customerMapper::toDomain)
                    .collect(Collectors.toList())
            );
        } else {
            admin.setCustomers(new ArrayList<>());
        }

        // Mapear lojas
        if (entity.getStores() != null && !entity.getStores().isEmpty()) {
            admin.setStores(
                entity.getStores().stream()
                    .map(storeMapper::toDomain)
                    .collect(Collectors.toList())
            );
        } else {
            admin.setStores(new ArrayList<>());
        }

        return admin;
    }

    public AdministratorEntity toEntity(Administrator domain) {
        if (domain == null) return null;

        AdministratorEntity entity = new AdministratorEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCpf(domain.getCpf());
        entity.setAddress(domain.getAddress());
        entity.setPhone(domain.getPhone());
        entity.setEmail(domain.getEmail());
        entity.setPassword(domain.getPassword());
        entity.setRole(domain.getRole());
        entity.setActive(domain.getActive());

        // Mapear clientes
        if (domain.getCustomers() != null && !domain.getCustomers().isEmpty()) {
            entity.setCustomers(
                domain.getCustomers().stream()
                    .map(customerMapper::toEntity)
                    .collect(Collectors.toList())
            );
        } else {
            entity.setCustomers(new ArrayList<>());
        }

        // Mapear lojas
        if (domain.getStores() != null && !domain.getStores().isEmpty()) {
            entity.setStores(
                domain.getStores().stream()
                    .map(storeMapper::toEntity)
                    .collect(Collectors.toList())
            );
        } else {
            entity.setStores(new ArrayList<>());
        }

        return entity;
    }
}

