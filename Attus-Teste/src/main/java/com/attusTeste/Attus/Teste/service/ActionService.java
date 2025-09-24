package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.Action;
import com.attusTeste.Attus.Teste.repository.ActionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActionService {

    private final ActionRepository repository;

    public ActionService(ActionRepository repository) {
        this.repository = repository;
    }

    public Action create(Action action) {
        return repository.save(action);
    }

    public List<Action> findByProcessId(Long processId) {
        return repository.findByJudicialProcessId(processId);
    }
}
