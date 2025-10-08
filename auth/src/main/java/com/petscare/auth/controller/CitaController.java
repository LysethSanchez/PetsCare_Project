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
import com.petscare.auth.model.Cita;
import com.petscare.auth.service.CitaService;

/**
 * Controlador REST para gestionar citas.
 * Endpoints:
 *  GET    /api/citas                          -> Listar todas las citas
 *  POST   /api/citas                          -> Crear una cita (201 + Location)
 *  GET    /api/citas/{id}                     -> Obtener cita por ID (404 si no existe)
 *  PUT    /api/citas/{id}                     -> Actualizar cita (404 si no existe)
 *  DELETE /api/citas/{id}                     -> Eliminar cita (204)
 *  GET    /api/citas/mascota/{idMascota}      -> Listar citas de una mascota
 *  GET    /api/citas/especialista/{nombre}    -> Listar citas por especialista
 */
@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    /** Crear una nueva cita -> 201 Created + Location */
    @PostMapping
    public ResponseEntity<Cita> crear(@RequestBody Cita cita) {
        Cita creada = citaService.crearCita(cita);

        // Construye Location absoluto: http://host:puerto/api/citas/{id}
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.getId())
                .toUri();

        return ResponseEntity.created(location).body(creada);
    }

    /** Listar todas -> 200 OK */
    @GetMapping
    public ResponseEntity<List<Cita>> listar() {
        return ResponseEntity.ok(citaService.listarCitas());
    }

    /** Obtener por ID -> 200 OK | 404 Not Found */
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtener(@PathVariable Long id) {
        Cita c = citaService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita " + id + " no encontrada"));
        return ResponseEntity.ok(c);
    }

    /** Actualizar -> 200 OK | 404 Not Found */
    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @RequestBody Cita cita) {
        // Verifica existencia antes de actualizar
        citaService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita " + id + " no encontrada"));

        cita.setId(id);
        Cita actualizada = citaService.actualizarCita(cita);
        return ResponseEntity.ok(actualizada);
    }

    /** Eliminar -> 204 No Content | 404 Not Found */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita " + id + " no encontrada"));

        citaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }

    /** Buscar por idMascota -> 200 OK */
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<Cita>> buscarPorMascota(@PathVariable Long idMascota) {
        return ResponseEntity.ok(citaService.buscarPorMascota(idMascota));
    }

    /** Buscar por especialista -> 200 OK */
    @GetMapping("/especialista/{nombre}")
    public ResponseEntity<List<Cita>> buscarPorEspecialista(@PathVariable String nombre) {
        return ResponseEntity.ok(citaService.buscarPorEspecialista(nombre));
    }
}
