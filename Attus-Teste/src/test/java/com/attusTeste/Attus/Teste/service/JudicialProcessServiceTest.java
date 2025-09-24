package com.attusTeste.Attus.Teste.service;

import com.attusTeste.Attus.Teste.model.Action;
import com.attusTeste.Attus.Teste.model.JudicialProcess;
import com.attusTeste.Attus.Teste.model.Party;
import com.attusTeste.Attus.Teste.repository.JudicialProcessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JudicialProcessServiceTest {

    @Mock
    private JudicialProcessRepository repository;

    @InjectMocks
    private JudicialProcessServiceImpl service; // usa a implementação

    private JudicialProcess process;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        process = new JudicialProcess();
        process.setCaseNumber("CASE001");
        process.setOpeningDate(LocalDate.now());
        process.setDescription("Processo de teste");
        process.setStatus("ATIVO");
        process.setParties(new ArrayList<>());
        process.setActions(new ArrayList<>());

        Party party = new Party("Fulano", "12345678901", "Autor", "fulano@email.com");
        Action action = new Action("Peticao", LocalDate.now(), "Peticao inicial");

        process.addParty(party);
        process.addAction(action);
    }

    @Test
    void shouldCreateProcess() {
        when(repository.findByCaseNumber("CASE001")).thenReturn(Optional.empty());
        when(repository.save(process)).thenReturn(process);

        JudicialProcess created = service.create(process);

        assertThat(created).isNotNull();
        assertThat(created.getStatus()).isEqualTo("ATIVO");
        assertThat(created.getParties()).hasSize(1);
        assertThat(created.getActions()).hasSize(1);

        verify(repository, times(1)).save(process);
    }

    @Test
    void shouldUpdateStatus() {
        when(repository.findById(1L)).thenReturn(Optional.of(process));
        service.updateStatus(1L, "ARQUIVADO");

        assertThat(process.getStatus()).isEqualTo("ARQUIVADO");
        verify(repository, times(1)).save(process);
    }

    @Test
    void shouldThrowIfInvalidStatus() {
        try {
            service.updateStatus(1L, "INVALIDO");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Status inválido");
        }
    }
}
