package br.com.emanuel.emprestai.adapter.inbound.mapper;

import br.com.emanuel.emprestai.adapter.inbound.web.dtos.RegisterAdministratorRequest;
import br.com.emanuel.emprestai.adapter.inbound.web.dtos.AdministratorResponse;
import br.com.emanuel.emprestai.domain.model.Administrator;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class AdministratorMapper {

    public Administrator toDomain(RegisterAdministratorRequest request) {
        if (request == null) return null;

        Administrator admin = new Administrator();
        admin.setName(request.getName());
        admin.setCpf(request.getCpf());
        admin.setAddress(request.getAddress());
        admin.setPhone(request.getPhone());
        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword()); // Será criptografado no service
        admin.setCustomers(new ArrayList<>());
        admin.setStores(new ArrayList<>());

        return admin;
    }

    public AdministratorResponse toResponse(Administrator domain) {
        if (domain == null) return null;

        AdministratorResponse response = new AdministratorResponse();
        response.setId(domain.getId());
        response.setName(domain.getName());
        response.setCpf(domain.getCpf());
        response.setAddress(domain.getAddress());
        response.setPhone(domain.getPhone());
        response.setEmail(domain.getEmail());
        response.setRole(domain.getRole());
        response.setActive(domain.getActive());

        // Converter clientes e lojas se necessário
        if (domain.getCustomers() != null) {
            response.setCustomers(new ArrayList<>()); // Pode adicionar mapping completo se necessário
        }
        if (domain.getStores() != null) {
            response.setStores(new ArrayList<>()); // Pode adicionar mapping completo se necessário
        }

        return response;
    }
}

