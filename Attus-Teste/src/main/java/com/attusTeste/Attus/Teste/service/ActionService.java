package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.Action;
import java.util.List;

public interface ActionService {
    Action create(Action action);
    List<Action> findByProcessId(Long processId);
}
