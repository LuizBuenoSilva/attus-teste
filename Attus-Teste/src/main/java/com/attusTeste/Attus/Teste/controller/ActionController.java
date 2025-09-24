package com.attusTeste.Attus.Teste.controller;

import com.attusTeste.Attus.Teste.model.Action;
import com.attusTeste.Attus.Teste.service.ActionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actions")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping
    public ResponseEntity<Action> createAction(@RequestBody Action action) {
        Action created = actionService.create(action);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/process/{processId}")
    public ResponseEntity<List<Action>> getByProcess(@PathVariable Long processId) {
        List<Action> actions = actionService.findByProcessId(processId);
        return ResponseEntity.ok(actions);
    }
}
