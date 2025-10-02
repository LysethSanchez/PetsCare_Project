package com.petscare.auth.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.petscare.auth.config.ResourceNotFoundException;
import com.petscare.auth.model.HistorialMedico;
import com.petscare.auth.service.HistorialMedicoService;

/**
 * Controlador REST para gestionar los historiales médicos.
 * Endpoints:
 *  GET    /api/historial            -> Listar todos
 *  POST   /api/historial            -> Registrar historial (201 Created + Location)
 *  GET    /api/historial/{id}       -> Buscar historial por id (200 / 404)
 *  PUT    /api/historial/{id}       -> Actualizar historial (200 / 404)
 *  DELETE /api/historial/{id}       -> Eliminar historial (204 / 404)
 */
@RestController
@RequestMapping("/api/historial")
@CrossOrigin(origins = "*")
public class HistorialMedicoController {

    @Autowired
    private HistorialMedicoService historialMedicoService;

    // POST -> 201 Created + Location
    @PostMapping
    public ResponseEntity<HistorialMedico> crear(@RequestBody HistorialMedico historial) {
        HistorialMedico guardado = historialMedicoService.guardar(historial);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardado);
    }

    // GET -> 200 OK
    @GetMapping
    public ResponseEntity<List<HistorialMedico>> listar() {
        return ResponseEntity.ok(historialMedicoService.listarTodos());
    }

    // GET por id -> 200 OK / 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<HistorialMedico> buscar(@PathVariable Long id) {
        HistorialMedico encontrado = historialMedicoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historial " + id + " no encontrado"));
        return ResponseEntity.ok(encontrado);
    }

    // PUT -> 200 OK / 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<HistorialMedico> actualizar(@PathVariable Long id, @RequestBody HistorialMedico actualizado) {
        HistorialMedico existente = historialMedicoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historial " + id + " no encontrado"));

        // Actualiza solo campos editables
        existente.setDiagnostico(actualizado.getDiagnostico());
        existente.setTratamiento(actualizado.getTratamiento());
        existente.setMedicamentos(actualizado.getMedicamentos());
        existente.setObservaciones(actualizado.getObservaciones());

        HistorialMedico result = historialMedicoService.guardar(existente);
        return ResponseEntity.ok(result);
    }

    // DELETE -> 204 No Content / 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        HistorialMedico existente = historialMedicoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historial " + id + " no encontrado"));

        historialMedicoService.eliminar(existente.getId());
        return ResponseEntity.noContent().build();
    }
}
