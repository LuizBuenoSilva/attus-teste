package com.attusTeste.Attus.Teste.repository;

import com.attusTeste.Attus.Teste.model.JudicialProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JudicialProcessRepository extends JpaRepository<JudicialProcess, Long> {

    Optional<JudicialProcess> findByCaseNumber(String caseNumber);

    List<JudicialProcess> findByStatus(String status);

    @Query("SELECT DISTINCT jp FROM JudicialProcess jp " +
            "JOIN jp.parties p " +
            "WHERE (:status IS NULL OR jp.status = :status) " +
            "AND (:openingDate IS NULL OR jp.openingDate = :openingDate) " +
            "AND (:cpfOrCnpj IS NULL OR p.cpfOrCnpj = :cpfOrCnpj)")
    List<JudicialProcess> searchProcesses(@Param("status") String status,
                                          @Param("openingDate") LocalDate openingDate,
                                          @Param("cpfOrCnpj") String cpfOrCnpj);
}
