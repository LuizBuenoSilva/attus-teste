package com.attusTeste.Attus.Teste.dto;

import java.time.LocalDate;

public class ActionDTO {
    private Long id;
    private String actionType;
    private LocalDate actionDate;
    private String description;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public LocalDate getActionDate() { return actionDate; }
    public void setActionDate(LocalDate actionDate) { this.actionDate = actionDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
