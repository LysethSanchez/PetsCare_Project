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

import com.petscare.auth.config.ResourceNotFoundException;
import com.petscare.auth.model.Horario;
import com.petscare.auth.service.HorarioService;

/**
 * Controlador REST para gestionar los horarios de los especialistas.
 *
 * Endpoints:
 *  GET    /api/horarios                      -> Listar todos (200)
 *  POST   /api/horarios                      -> Crear (201 + Location)
 *  GET    /api/horarios/{id}                 -> Obtener por ID (200 | 404)
 *  GET    /api/horarios/especialista/{id}    -> Listar por especialista (200)
 *  PUT    /api/horarios/{id}                 -> Actualizar (200 | 404)
 *  DELETE /api/horarios/{id}                 -> Eliminar (204 | 404)
 */
@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    /** Crear un nuevo horario -> 201 Created + Location */
    @PostMapping
    public ResponseEntity<Horario> crear(@RequestBody Horario horario) {
        Horario creado = horarioService.guardar(horario);
        URI location = URI.create("/api/horarios/" + creado.getId());
        return ResponseEntity.created(location).body(creado);
    }

    /** Listar todos -> 200 OK */
    @GetMapping
    public ResponseEntity<List<Horario>> listarTodos() {
        return ResponseEntity.ok(horarioService.obtenerTodos());
    }

    /** Obtener por ID -> 200 OK | 404 Not Found */
    @GetMapping("/{id}")
    public ResponseEntity<Horario> obtenerPorId(@PathVariable Long id) {
        Horario h = horarioService.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario " + id + " no encontrado"));
        return ResponseEntity.ok(h);
    }

    /** Listar horarios por especialista -> 200 OK */
    @GetMapping("/especialista/{id}")
    public ResponseEntity<List<Horario>> obtenerPorEspecialista(@PathVariable Long id) {
        return ResponseEntity.ok(horarioService.obtenerPorEspecialista(id));
    }

    /** Actualizar -> 200 OK | 404 Not Found */
    @PutMapping("/{id}")
    public ResponseEntity<Horario> actualizar(@PathVariable Long id, @RequestBody Horario horario) {
        // Verifica existencia antes de actualizar
        horarioService.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario " + id + " no encontrado"));

        horario.setId(id);
        Horario actualizado = horarioService.guardar(horario);
        return ResponseEntity.ok(actualizado);
    }

    /** Eliminar -> 204 No Content | 404 Not Found */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        horarioService.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario " + id + " no encontrado"));
        horarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
