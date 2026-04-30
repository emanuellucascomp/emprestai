package br.com.emanuel.emprestai.adapter.outbound.persistence;

import br.com.emanuel.emprestai.adapter.outbound.persistence.entity.StoreEntity;
import br.com.emanuel.emprestai.domain.model.Store;
import org.springframework.stereotype.Component;

@Component
public class StorePersistenceMapper {

    public StoreEntity toEntity(Store domain) {
        if (domain == null) return null;
        StoreEntity e = new StoreEntity();
        e.setId(domain.getId());
        e.setName(domain.getName());
        e.setCnpj(domain.getCnpj());
        e.setAddress(domain.getAddress());
        e.setPhone(domain.getPhone());
        return e;
    }

    public Store toDomain(StoreEntity entity) {
        if (entity == null) return null;
        Store s = new Store();
        s.setId(entity.getId());
        s.setName(entity.getName());
        s.setCnpj(entity.getCnpj());
        s.setAddress(entity.getAddress());
        s.setPhone(entity.getPhone());
        return s;
    }
}
