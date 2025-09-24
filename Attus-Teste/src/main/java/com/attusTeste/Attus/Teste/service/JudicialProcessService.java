package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.JudicialProcess;
import com.attusTeste.Attus.Teste.model.Party;
import com.attusTeste.Attus.Teste.repository.JudicialProcessRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JudicialProcessService {

    private final JudicialProcessRepository repository;

    public JudicialProcessService(JudicialProcessRepository repository) {
        this.repository = repository;
    }

    public JudicialProcess create(JudicialProcess process) {
        repository.findByCaseNumber(process.getCaseNumber())
                .ifPresent(p -> { throw new RuntimeException("Número de processo já existente"); });
        validateProcess(process);
        process.setStatus("ATIVO");
        process.getParties().forEach(p -> p.setJudicialProcess(process));
        process.getActions().forEach(a -> a.setJudicialProcess(process));
        return repository.save(process);
    }

    public JudicialProcess update(JudicialProcess process) {
        validateProcess(process);
        process.getParties().forEach(p -> p.setJudicialProcess(process));
        process.getActions().forEach(a -> a.setJudicialProcess(process));
        return repository.save(process);
    }

    public void archive(Long id) {
        updateStatus(id, "ARQUIVADO");
    }

    public void updateStatus(Long id, String status) {
        if (!List.of("ATIVO", "SUSPENSO", "ARQUIVADO").contains(status.toUpperCase()))
            throw new RuntimeException("Status inválido");
        JudicialProcess process = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado"));
        process.setStatus(status.toUpperCase());
        repository.save(process);
    }

    @Transactional(readOnly = true)
    public List<JudicialProcess> findAll() {
        List<JudicialProcess> processes = repository.findAll();
        processes.forEach(this::initializeCollections);
        return processes;
    }

    @Transactional(readOnly = true)
    public List<JudicialProcess> findByStatus(String status) {
        List<JudicialProcess> processes = repository.findByStatus(status);
        processes.forEach(this::initializeCollections);
        return processes;
    }

    @Transactional(readOnly = true)
    public List<JudicialProcess> search(String status, LocalDate openingDate, String cpfOrCnpj) {
        List<JudicialProcess> processes = repository.searchProcesses(status, openingDate, cpfOrCnpj);
        processes.forEach(this::initializeCollections);
        return processes;
    }

    @Transactional(readOnly = true)
    public Optional<JudicialProcess> findById(Long id) {
        Optional<JudicialProcess> opt = repository.findById(id);
        opt.ifPresent(this::initializeCollections);
        return opt;
    }

    // Inicializa parties e actions para evitar LazyInitialization
    private void initializeCollections(JudicialProcess process) {
        if (process.getParties() != null) process.getParties().size();
        if (process.getActions() != null) process.getActions().size();
    }

    private void validateProcess(JudicialProcess process) {
        if (process.getOpeningDate() != null && process.getOpeningDate().isAfter(LocalDate.now()))
            throw new RuntimeException("Data de abertura inválida");
        if (process.getParties() != null) {
            for (Party p : process.getParties()) {
                if (!isValidCpfOrCnpj(p.getCpfOrCnpj()))
                    throw new RuntimeException("CPF/CNPJ inválido: " + p.getCpfOrCnpj());
            }
        }
    }

    private boolean isValidCpfOrCnpj(String cpfOrCnpj) {
        return cpfOrCnpj != null && !cpfOrCnpj.isBlank(); // Pode melhorar com regex real
    }
}
