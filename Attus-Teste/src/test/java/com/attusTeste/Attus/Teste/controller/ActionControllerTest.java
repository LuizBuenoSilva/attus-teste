package com.attusTeste.Attus.Teste.controller;

import com.attusTeste.Attus.Teste.model.Action;
import com.attusTeste.Attus.Teste.service.ActionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ActionControllerTest {

    @Test
    void testCreateAction() {
        ActionService service = Mockito.mock(ActionService.class);
        Action action = new Action();
        action.setActionType("Audiência");

        when(service.create(action)).thenReturn(action);

        ActionController controller = new ActionController(service);
        Action response = controller.create(action).getBody();

        assertEquals("Audiência", response.getActionType());
    }

    @Test
    void testGetByProcess() {
        ActionService service = Mockito.mock(ActionService.class);
        when(service.findByProcessId(1L)).thenReturn(List.of(new Action(), new Action()));

        ActionController controller = new ActionController(service);
        List<Action> actions = controller.getByProcess(1L);

        assertEquals(2, actions.size());
    }
}
