package br.com.emanuel.emprestai.domain.ports.outbound;

import br.com.emanuel.emprestai.domain.model.Administrator;
import java.util.List;
import java.util.Optional;

public interface AdministratorRepository {
    Administrator save(Administrator administrator);
    Optional<Administrator> findById(Long id);
    List<Administrator> findAll();
    void deleteById(Long id);
    Optional<Administrator> findByCpf(String cpf);
}

