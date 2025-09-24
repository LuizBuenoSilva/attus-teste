package com.attusTeste.Attus.Teste.controller;

import com.attusTeste.Attus.Teste.model.Action;
import com.attusTeste.Attus.Teste.service.ActionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actions")
public class ActionController {

    private final ActionService service;

    public ActionController(ActionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Action> create(@RequestBody Action action) {
        return ResponseEntity.ok(service.create(action));
    }

    @GetMapping("/byProcess/{processId}")
    public List<Action> getByProcess(@PathVariable Long processId) {
        return service.findByProcessId(processId);
    }
}
