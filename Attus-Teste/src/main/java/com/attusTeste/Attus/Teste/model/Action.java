package com.attusTeste.Attus.Teste.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actionType;
    private LocalDate actionDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "judicial_process_id")
    @JsonBackReference("process-actions")
    private JudicialProcess judicialProcess;

    public Action() {}

    public Action(String actionType, LocalDate actionDate, String description) {
        this.actionType = actionType;
        this.actionDate = actionDate;
        this.description = description;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }

    public LocalDate getActionDate() { return actionDate; }
    public void setActionDate(LocalDate actionDate) { this.actionDate = actionDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public JudicialProcess getJudicialProcess() { return judicialProcess; }
    public void setJudicialProcess(JudicialProcess judicialProcess) { this.judicialProcess = judicialProcess; }
}
