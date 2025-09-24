package com.attusTeste.Attus.Teste.controller;

import com.attusTeste.Attus.Teste.model.Party;
import com.attusTeste.Attus.Teste.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parties")
public class PartyController {

    private final PartyService service;

    public PartyController(PartyService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Party> create(@RequestBody Party party) {
        return ResponseEntity.ok(service.create(party));
    }

    @GetMapping("/byProcess/{processId}")
    public List<Party> getByProcess(@PathVariable Long processId) {
        return service.findByProcess(processId);
    }

    @GetMapping("/byCpfOrCnpj")
    public List<Party> getByCpfOrCnpj(@RequestParam String cpfOrCnpj) {
        return service.findByCpfOrCnpj(cpfOrCnpj);
    }
}
