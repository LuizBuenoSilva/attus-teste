package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.JudicialProcess;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JudicialProcessService {
    JudicialProcess create(JudicialProcess process);
    JudicialProcess update(JudicialProcess process);
    void archive(Long id);
    void updateStatus(Long id, String status);
    List<JudicialProcess> findAll();
    List<JudicialProcess> findByStatus(String status);
    List<JudicialProcess> search(String status, LocalDate openingDate, String cpfOrCnpj);
    Optional<JudicialProcess> findById(Long id);
}
