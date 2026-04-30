package br.com.emanuel.emprestai.adapter.inbound.mapper;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.StoreResponse;
import br.com.emanuel.emprestai.domain.model.Store;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreMapper {

    public Store toDomain(StoreRequest request) {
        if (request == null) return null;
        Store s = new Store();
        s.setName(request.getName());
        s.setCnpj(request.getCnpj());
        s.setAddress(request.getAddress());
        s.setPhone(request.getPhone());
        return s;
    }

    public StoreResponse toResponse(Store domain) {
        if (domain == null) return null;
        StoreResponse resp = new StoreResponse();
        resp.setId(domain.getId());
        resp.setName(domain.getName());
        resp.setCnpj(domain.getCnpj());
        resp.setAddress(domain.getAddress());
        resp.setPhone(domain.getPhone());
        return resp;
    }

    public List<StoreResponse> toResponseList(List<Store> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void updateDomainFromRequest(Store domain, StoreRequest request) {
        if (domain == null || request == null) return;
        domain.setName(request.getName());
        domain.setCnpj(request.getCnpj());
        domain.setAddress(request.getAddress());
        domain.setPhone(request.getPhone());
    }
}
