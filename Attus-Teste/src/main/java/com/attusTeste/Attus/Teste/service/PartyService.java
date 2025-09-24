package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.Party;
import java.util.List;

public interface PartyService {
    Party create(Party party);
    List<Party> findByProcess(Long judicialProcessId);
    List<Party> findByCpfOrCnpj(String cpfOrCnpj);
}
