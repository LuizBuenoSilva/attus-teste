package com.attusTeste.Attus.Teste.repository;

import com.attusTeste.Attus.Teste.model.JudicialProcess;
import com.attusTeste.Attus.Teste.model.Party;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PartyRepositoryTest {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private JudicialProcessRepository judicialProcessRepository;

    private JudicialProcess judicialProcess;
    private Party party1;
    private Party party2;

    @BeforeEach
    void setUp() {
        judicialProcess = judicialProcessRepository.save(
                new JudicialProcess("CASE456", LocalDate.now(), "Processo teste", "ATIVO")
        );

        party1 = new Party("Fulano", "12345678901", "Autor", "fulano@email.com");
        party2 = new Party("Beltrano", "98765432100", "Réu", "beltrano@email.com");

        party1.setJudicialProcess(judicialProcess);
        party2.setJudicialProcess(judicialProcess);

        // Salvar manualmente, pois não há cascade
        partyRepository.save(party1);
        partyRepository.save(party2);
    }

    @Test
    void shouldSaveAndFindPartiesByJudicialProcessId() {
        List<Party> parties = partyRepository.findByJudicialProcessId(judicialProcess.getId());

        assertThat(parties).hasSize(2);
        assertThat(parties).extracting("fullName").containsExactlyInAnyOrder("Fulano", "Beltrano");
    }

    @Test
    void shouldFindPartiesByCpfOrCnpj() {
        List<Party> found = partyRepository.findByCpfOrCnpj("12345678901");

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getFullName()).isEqualTo("Fulano");
    }
}
