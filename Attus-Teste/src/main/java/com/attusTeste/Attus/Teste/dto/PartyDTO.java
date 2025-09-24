package com.attusTeste.Attus.Teste.dto;

public class PartyDTO {
    private Long id;
    private String fullName;
    private String cpfOrCnpj;
    private String partyType;
    private String contact;

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
}
