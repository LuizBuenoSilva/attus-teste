package com.attusTeste.Attus.Teste.repository;

import com.attusTeste.Attus.Teste.model.Action;
import com.attusTeste.Attus.Teste.model.JudicialProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ActionRepositoryTest {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private JudicialProcessRepository judicialProcessRepository;

    private JudicialProcess judicialProcess;

    @BeforeEach
    void setUp() {
        // Criar o processo judicial
        judicialProcess = new JudicialProcess(
                "PROCESSO78910",
                LocalDate.now(),
                "Processo teste com actions",
                "ATIVO"
        );

        // Criar ações sem acento
        Action action1 = new Action("Peticao", LocalDate.now(), "Peticao inicial feita pelo autor");
        Action action2 = new Action("Audiencia", LocalDate.now(), "Audiencia marcada com o juiz");

        // Usar método helper para garantir bidirecionalidade
        judicialProcess.addAction(action1);
        judicialProcess.addAction(action2);

        // Salvar o processo; as ações serão salvas automaticamente pelo cascade
        judicialProcessRepository.save(judicialProcess);
    }

    @Test
    void shouldSaveAndFindActionsByJudicialProcessId() {
        List<Action> actions = actionRepository.findByJudicialProcessId(judicialProcess.getId());

        assertThat(actions).hasSize(2);
        assertThat(actions).extracting("actionType")
                .containsExactlyInAnyOrder("Peticao", "Audiencia");
    }

    @Test
    void shouldFindActionsByActionType() {
        List<Action> peticoes = actionRepository.findByActionType("Peticao");

        assertThat(peticoes).hasSize(1);
        assertThat(peticoes.get(0).getDescription())
                .isEqualTo("Peticao inicial feita pelo autor");
    }
}
