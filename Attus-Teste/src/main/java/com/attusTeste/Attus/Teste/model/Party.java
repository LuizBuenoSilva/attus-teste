package com.attusTeste.Attus.Teste.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String cpfOrCnpj;
    private String partyType;
    private String contact;

    @ManyToOne
    @JoinColumn(name = "judicial_process_id")
    @JsonBackReference("process-parties")
    private JudicialProcess judicialProcess;

    public Party() {}

    public Party(String fullName, String cpfOrCnpj, String partyType, String contact) {
        this.fullName = fullName;
        this.cpfOrCnpj = cpfOrCnpj;
        this.partyType = partyType;
        this.contact = contact;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getCpfOrCnpj() { return cpfOrCnpj; }
    public void setCpfOrCnpj(String cpfOrCnpj) { this.cpfOrCnpj = cpfOrCnpj; }

    public String getPartyType() { return partyType; }
    public void setPartyType(String partyType) { this.partyType = partyType; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public JudicialProcess getJudicialProcess() { return judicialProcess; }
    public void setJudicialProcess(JudicialProcess judicialProcess) { this.judicialProcess = judicialProcess; }
}
