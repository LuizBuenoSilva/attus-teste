package com.attusTeste.Attus.Teste.dto;

import java.time.LocalDate;
import java.util.List;

public class JudicialProcessDTO {
    private Long id;
    private String caseNumber;
    private LocalDate openingDate;
    private String description;
    private String status;
    private List<PartyDTO> parties;
    private List<ActionDTO> actions;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public LocalDate getOpeningDate() { return openingDate; }
    public void setOpeningDate(LocalDate openingDate) { this.openingDate = openingDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<PartyDTO> getParties() { return parties; }
    public void setParties(List<PartyDTO> parties) { this.parties = parties; }

    public List<ActionDTO> getActions() { return actions; }
    public void setActions(List<ActionDTO> actions) { this.actions = actions; }
}
