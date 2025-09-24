package com.attusTeste.Attus.Teste.controller;

import com.attusTeste.Attus.Teste.dto.ActionDTO;
import com.attusTeste.Attus.Teste.dto.JudicialProcessDTO;
import com.attusTeste.Attus.Teste.dto.PartyDTO;
import com.attusTeste.Attus.Teste.model.Action;
import com.attusTeste.Attus.Teste.model.JudicialProcess;
import com.attusTeste.Attus.Teste.model.Party;
import com.attusTeste.Attus.Teste.service.JudicialProcessService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/processes")
public class JudicialProcessController {

    private final JudicialProcessService service;

    public JudicialProcessController(JudicialProcessService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JudicialProcessDTO> create(@RequestBody JudicialProcessDTO dto) {
        JudicialProcess process = toEntity(dto);
        JudicialProcess saved = service.create(process);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JudicialProcessDTO> update(@PathVariable Long id, @RequestBody JudicialProcessDTO dto) {
        JudicialProcess process = toEntity(dto);
        process.setId(id);
        JudicialProcess updated = service.update(process);
        return ResponseEntity.ok(toDTO(updated));
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<Void> archive(@PathVariable Long id) {
        service.archive(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JudicialProcessDTO> get(@PathVariable Long id) {
        return service.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<JudicialProcessDTO> list(@RequestParam(required = false) String status) {
        List<JudicialProcess> processes = (status != null)
                ? service.findByStatus(status)
                : service.findAll();

        return processes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<JudicialProcessDTO> search(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate openingDate,
            @RequestParam(required = false) String cpfOrCnpj) {

        return service.search(status, openingDate, cpfOrCnpj).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ---------------- Conversão DTO ----------------

    private JudicialProcessDTO toDTO(JudicialProcess process) {
        JudicialProcessDTO dto = new JudicialProcessDTO();
        dto.setId(process.getId());
        dto.setCaseNumber(process.getCaseNumber());
        dto.setOpeningDate(process.getOpeningDate());
        dto.setDescription(process.getDescription());
        dto.setStatus(process.getStatus());

        List<PartyDTO> partyDTOs = process.getParties() != null
                ? process.getParties().stream().map(p -> {
            PartyDTO pdto = new PartyDTO();
            pdto.setId(p.getId());
            pdto.setFullName(p.getFullName());
            pdto.setCpfOrCnpj(p.getCpfOrCnpj());
            pdto.setPartyType(p.getPartyType());
            pdto.setContact(p.getContact());
            return pdto;
        }).collect(Collectors.toList())
                : Collections.emptyList();
        dto.setParties(partyDTOs);

        List<ActionDTO> actionDTOs = process.getActions() != null
                ? process.getActions().stream().map(a -> {
            ActionDTO adto = new ActionDTO();
            adto.setId(a.getId());
            adto.setActionType(a.getActionType());
            adto.setActionDate(a.getActionDate());
            adto.setDescription(a.getDescription());
            return adto;
        }).collect(Collectors.toList())
                : Collections.emptyList();
        dto.setActions(actionDTOs);

        return dto;
    }

    private JudicialProcess toEntity(JudicialProcessDTO dto) {
        JudicialProcess process = new JudicialProcess();
        process.setId(dto.getId());
        process.setCaseNumber(dto.getCaseNumber());
        process.setOpeningDate(dto.getOpeningDate());
        process.setDescription(dto.getDescription());
        process.setStatus(dto.getStatus());

        List<Party> parties = dto.getParties() != null
                ? dto.getParties().stream().map(pdto -> {
            Party p = new Party();
            p.setId(pdto.getId());
            p.setFullName(pdto.getFullName());
            p.setCpfOrCnpj(pdto.getCpfOrCnpj());
            p.setPartyType(pdto.getPartyType());
            p.setContact(pdto.getContact());
            p.setJudicialProcess(process);
            return p;
        }).collect(Collectors.toList())
                : Collections.emptyList();
        process.setParties(parties);

        List<Action> actions = dto.getActions() != null
                ? dto.getActions().stream().map(adto -> {
            Action a = new Action();
            a.setId(adto.getId());
            a.setActionType(adto.getActionType());
            a.setActionDate(adto.getActionDate());
            a.setDescription(adto.getDescription());
            a.setJudicialProcess(process);
            return a;
        }).collect(Collectors.toList())
                : Collections.emptyList();
        process.setActions(actions);

        return process;
    }
}
