package com.attusTeste.Attus.Teste.controller;

import com.attusTeste.Attus.Teste.model.Party;
import com.attusTeste.Attus.Teste.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parties")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @PostMapping
    public ResponseEntity<Party> createParty(@RequestBody Party party) {
        Party created = partyService.create(party);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/process/{processId}")
    public ResponseEntity<List<Party>> getByProcess(@PathVariable Long processId) {
        List<Party> parties = partyService.findByProcess(processId);
        return ResponseEntity.ok(parties);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Party>> getByCpfOrCnpj(@RequestParam String cpfOrCnpj) {
        List<Party> parties = partyService.findByCpfOrCnpj(cpfOrCnpj);
        return ResponseEntity.ok(parties);
    }
}
