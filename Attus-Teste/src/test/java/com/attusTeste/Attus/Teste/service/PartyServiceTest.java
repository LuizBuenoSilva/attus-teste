package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.Party;
import com.attusTeste.Attus.Teste.repository.PartyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PartyServiceTest {

    private PartyRepository repository;
    private PartyService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(PartyRepository.class);
        service = new PartyService(repository);
    }

    @Test
    void testCreateParty() {
        Party party = new Party();
        party.setFullName("Fulano");

        when(repository.save(party)).thenReturn(party);

        Party created = service.create(party);

        assertEquals("Fulano", created.getFullName());
        verify(repository, times(1)).save(party);
    }

    @Test
    void testFindByProcess() {
        when(repository.findByJudicialProcessId(1L))
                .thenReturn(List.of(new Party(), new Party()));

        List<Party> parties = service.findByProcess(1L);

        assertEquals(2, parties.size());
    }

    @Test
    void testFindByCpfOrCnpj() {
        when(repository.findByCpfOrCnpj("12345678900"))
                .thenReturn(List.of(new Party()));

        List<Party> parties = service.findByCpfOrCnpj("12345678900");

        assertEquals(1, parties.size());
    }
}
