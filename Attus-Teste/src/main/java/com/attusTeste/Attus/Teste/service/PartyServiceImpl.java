package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.Party;
import com.attusTeste.Attus.Teste.repository.PartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyServiceImpl implements PartyService {

    private final PartyRepository repository;

    public PartyServiceImpl(PartyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Party create(Party party) {
        return repository.save(party);
    }

    @Override
    public List<Party> findByProcess(Long judicialProcessId) {
        return repository.findByJudicialProcessId(judicialProcessId);
    }

    @Override
    public List<Party> findByCpfOrCnpj(String cpfOrCnpj) {
        return repository.findByCpfOrCnpj(cpfOrCnpj);
    }
}
