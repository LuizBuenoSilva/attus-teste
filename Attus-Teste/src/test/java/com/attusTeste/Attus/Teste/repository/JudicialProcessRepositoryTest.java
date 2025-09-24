package com.attusTeste.Attus.Teste.repository;

import com.attusTeste.Attus.Teste.model.JudicialProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JudicialProcessRepositoryTest {

    @Autowired
    private JudicialProcessRepository judicialProcessRepository;

    private JudicialProcess judicialProcess;

    @BeforeEach
    void setUp() {
        judicialProcess = new JudicialProcess("CASE123", LocalDate.now(), "Processo de teste", "ATIVO");
        judicialProcessRepository.save(judicialProcess);
    }

    @Test
    void shouldFindByCaseNumber() {
        Optional<JudicialProcess> found = judicialProcessRepository.findByCaseNumber("CASE123");
        assertThat(found).isPresent();
        assertThat(found.get().getDescription()).isEqualTo("Processo de teste");
    }

    @Test
    void shouldFindByStatus() {
        List<JudicialProcess> processes = judicialProcessRepository.findByStatus("ATIVO");
        assertThat(processes).hasSize(1);
        assertThat(processes.get(0).getCaseNumber()).isEqualTo("CASE123");
    }
}
