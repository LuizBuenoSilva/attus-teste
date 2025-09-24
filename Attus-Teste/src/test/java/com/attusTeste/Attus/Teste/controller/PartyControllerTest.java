package com.attusTeste.Attus.Teste.controller;

import com.attusTeste.Attus.Teste.model.Party;
import com.attusTeste.Attus.Teste.service.PartyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PartyControllerTest {

    @Test
    void testCreateParty() {
        PartyService service = Mockito.mock(PartyService.class);
        Party party = new Party();
        party.setFullName("Fulano");
        party.setCpfOrCnpj("12345678900");

        when(service.create(party)).thenReturn(party);

        PartyController controller = new PartyController(service);
        ResponseEntity<Party> response = controller.create(party);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Fulano", response.getBody().getFullName());
        assertEquals("12345678900", response.getBody().getCpfOrCnpj());
    }

    @Test
    void testGetByProcess() {
        PartyService service = Mockito.mock(PartyService.class);
        Party p1 = new Party();
        Party p2 = new Party();

        when(service.findByProcess(1L)).thenReturn(List.of(p1, p2));

        PartyController controller = new PartyController(service);
        List<Party> parties = controller.getByProcess(1L);

        assertEquals(2, parties.size());
    }

    @Test
    void testGetByCpfOrCnpj() {
        PartyService service = Mockito.mock(PartyService.class);
        Party p = new Party();
        p.setCpfOrCnpj("12345678900");

        when(service.findByCpfOrCnpj("12345678900")).thenReturn(List.of(p));

        PartyController controller = new PartyController(service);
        List<Party> parties = controller.getByCpfOrCnpj("12345678900");

        assertEquals(1, parties.size());
        assertEquals("12345678900", parties.get(0).getCpfOrCnpj());
    }
}
