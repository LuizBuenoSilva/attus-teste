package com.attusTeste.Attus.Teste.controller;

import com.attusTeste.Attus.Teste.model.JudicialProcess;
import com.attusTeste.Attus.Teste.service.JudicialProcessService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JudicialProcessControllerTest {

    @Test
    void testGetProcessFound() {
        JudicialProcessService service = mock(JudicialProcessService.class);
        JudicialProcess process = new JudicialProcess();
        process.setId(1L);
        process.setCaseNumber("123");
        process.setDescription("Teste");
        process.setOpeningDate(LocalDate.now());
        process.setStatus("OPEN");

        when(service.findById(1L)).thenReturn(Optional.of(process));

        JudicialProcessController controller = new JudicialProcessController(service);
        ResponseEntity<JudicialProcess> response = controller.getById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("123", response.getBody().getCaseNumber());
    }

    @Test
    void testGetProcessNotFound() {
        JudicialProcessService service = mock(JudicialProcessService.class);

        when(service.findById(1L)).thenReturn(Optional.empty());

        JudicialProcessController controller = new JudicialProcessController(service);
        ResponseEntity<JudicialProcess> response = controller.getById(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testListProcesses() {
        JudicialProcessService service = mock(JudicialProcessService.class);
        JudicialProcess process1 = new JudicialProcess();
        process1.setId(1L);
        process1.setCaseNumber("111");

        JudicialProcess process2 = new JudicialProcess();
        process2.setId(2L);
        process2.setCaseNumber("222");

        when(service.findAll()).thenReturn(List.of(process1, process2));

        JudicialProcessController controller = new JudicialProcessController(service);
        List<JudicialProcess> result = controller.getAll().getBody();

        assertEquals(2, result.size());
        assertEquals("111", result.get(0).getCaseNumber());
        assertEquals("222", result.get(1).getCaseNumber());
    }
}
