package com.petscare.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petscare.auth.model.HistorialMedico;
import com.petscare.auth.service.HistorialMedicoService;

/**
 * Controlador REST para gestionar los historiales médicos.
 * Endpoints:
 *  GET    /api/historial            -> Listar todos
 *  POST   /api/historial            -> Registrar historial
 *  GET    /api/historial/{id}       -> Buscar historial por id
 *  PUT    /api/historial/{id}       -> Actualizar historial
 *  DELETE /api/historial/{id}       -> Eliminar historial
 */
@RestController
@RequestMapping("/api/historial")
@CrossOrigin(origins = "*")
public class HistorialMedicoController {

    @Autowired
    private HistorialMedicoService historialMedicoService;

    // Crear un nuevo historial
    @PostMapping
    public HistorialMedico crear(@RequestBody HistorialMedico historial) {
        return historialMedicoService.guardar(historial);
    }

    // Listar todos los historiales
    @GetMapping
    public List<HistorialMedico> listar() {
        return historialMedicoService.listarTodos();
    }

    // Buscar historial por ID
    @GetMapping("/{id}")
    public HistorialMedico buscar(@PathVariable Long id) {
        return historialMedicoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));
    }

    // Actualizar historial (solo actualiza campos editables)
    @PutMapping("/{id}")
    public HistorialMedico actualizar(@PathVariable Long id, @RequestBody HistorialMedico actualizado) {
        HistorialMedico existente = historialMedicoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

        // Actualizamos los campos necesarios
        existente.setDiagnostico(actualizado.getDiagnostico());
        existente.setTratamiento(actualizado.getTratamiento());
        existente.setMedicamentos(actualizado.getMedicamentos());
        existente.setObservaciones(actualizado.getObservaciones());

        return historialMedicoService.guardar(existente);
    }

    // Eliminar historial
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        historialMedicoService.eliminar(id);
    }
}
