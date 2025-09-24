package com.attusTeste.Attus.Teste.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JudicialProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String caseNumber;

    @Column(nullable = false)
    private LocalDate openingDate;

    private String description;

    @OneToMany(mappedBy = "judicialProcess", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("process-parties")
    private List<Party> parties = new ArrayList<>();

    @OneToMany(mappedBy = "judicialProcess", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("process-actions")
    private List<Action> actions = new ArrayList<>();

    private String status;

    public JudicialProcess() {}

    public JudicialProcess(String caseNumber, LocalDate openingDate, String description, String status) {
        this.caseNumber = caseNumber;
        this.openingDate = openingDate;
        this.description = description;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public LocalDate getOpeningDate() { return openingDate; }
    public void setOpeningDate(LocalDate openingDate) { this.openingDate = openingDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Party> getParties() { return parties; }
    public void setParties(List<Party> parties) { this.parties = parties; }

    public List<Action> getActions() { return actions; }
    public void setActions(List<Action> actions) { this.actions = actions; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void addParty(Party party) {
        parties.add(party);
        party.setJudicialProcess(this);
    }

    public void addAction(Action action) {
        actions.add(action);
        action.setJudicialProcess(this);
    }
}
