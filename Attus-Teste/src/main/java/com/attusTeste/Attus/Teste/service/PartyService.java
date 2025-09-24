package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.Party;
import com.attusTeste.Attus.Teste.repository.PartyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PartyService {

    private final PartyRepository repository;

    public PartyService(PartyRepository repository) {
        this.repository = repository;
    }

    public Party create(Party party) {
        return repository.save(party);
    }

    public List<Party> findByProcess(Long judicialProcessId) {
        return repository.findByJudicialProcessId(judicialProcessId);
    }

    public List<Party> findByCpfOrCnpj(String cpfOrCnpj) {
        return repository.findByCpfOrCnpj(cpfOrCnpj);
    }
}
