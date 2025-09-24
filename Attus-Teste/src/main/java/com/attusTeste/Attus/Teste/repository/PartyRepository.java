package com.attusTeste.Attus.Teste.repository;

import com.attusTeste.Attus.Teste.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    List<Party> findByJudicialProcessId(Long judicialProcessId);

    @Query("SELECT p FROM Party p WHERE p.cpfOrCnpj = :cpfOrCnpj")
    List<Party> findByCpfOrCnpj(@Param("cpfOrCnpj") String cpfOrCnpj);
}

