package com.attusTeste.Attus.Teste.repository;

import com.attusTeste.Attus.Teste.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    List<Action> findByJudicialProcessId(Long judicialProcessId);

    List<Action> findByActionType(String petição);
}
