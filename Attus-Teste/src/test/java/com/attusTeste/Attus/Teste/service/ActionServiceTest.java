package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.Action;
import com.attusTeste.Attus.Teste.repository.ActionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ActionServiceTest {

    private ActionRepository repository;
    private ActionService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ActionRepository.class);
        service = new ActionService(repository);
    }

    @Test
    void testCreateAction() {
        Action action = new Action();
        action.setActionType("Audiência");

        when(repository.save(action)).thenReturn(action);

        Action created = service.create(action);

        assertEquals("Audiência", created.getActionType());
        verify(repository, times(1)).save(action);
    }

    @Test
    void testFindByProcessId() {
        when(repository.findByJudicialProcessId(1L)).thenReturn(List.of(new Action(), new Action()));

        List<Action> actions = service.findByProcessId(1L);

        assertEquals(2, actions.size());
    }
}
