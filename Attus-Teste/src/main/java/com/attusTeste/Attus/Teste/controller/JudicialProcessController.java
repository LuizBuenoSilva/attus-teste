package com.attusTeste.Attus.Teste.controller;

import com.attusTeste.Attus.Teste.model.JudicialProcess;
import com.attusTeste.Attus.Teste.service.JudicialProcessService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/processes")
public class JudicialProcessController {

    private final JudicialProcessService processService;

    public JudicialProcessController(JudicialProcessService processService) {
        this.processService = processService;
    }

    @PostMapping
    public ResponseEntity<JudicialProcess> createProcess(@RequestBody JudicialProcess process) {
        return ResponseEntity.ok(processService.create(process));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JudicialProcess> updateProcess(@PathVariable Long id,
                                                         @RequestBody JudicialProcess process) {
        process.setId(id);
        return ResponseEntity.ok(processService.update(process));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id,
                                             @RequestParam String status) {
        processService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JudicialProcess>> getAll() {
        return ResponseEntity.ok(processService.findAll());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<JudicialProcess>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(processService.findByStatus(status));
    }

    @GetMapping("/search")
    public ResponseEntity<List<JudicialProcess>> search(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate openingDate,
            @RequestParam(required = false) String cpfOrCnpj) {

        return ResponseEntity.ok(processService.search(status, openingDate, cpfOrCnpj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JudicialProcess> getById(@PathVariable Long id) {
        return processService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
