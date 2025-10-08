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
import com.petscare.auth.model.Especialista;
import com.petscare.auth.service.EspecialistaService;

/**
 * Controlador REST para gestionar especialistas.
 * Endpoints:
 *  GET    /api/especialistas          -> Listar todos (200)
 *  POST   /api/especialistas          -> Crear especialista (201 + Location)
 *  GET    /api/especialistas/{id}     -> Obtener por ID (200 / 404)
 *  PUT    /api/especialistas/{id}     -> Actualizar (200 / 404)
 *  DELETE /api/especialistas/{id}     -> Eliminar (204 / 404)
 */
@RestController
@RequestMapping("/api/especialistas")
@CrossOrigin(origins = "*")
public class EspecialistaController {

    @Autowired
    private EspecialistaService service;

    // Listar todos -> 200 OK
    @GetMapping
    public ResponseEntity<List<Especialista>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // Crear -> 201 Created + Location
    @PostMapping
    public ResponseEntity<Especialista> crear(@RequestBody Especialista especialista) {
        Especialista creado = service.guardar(especialista);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();

        return ResponseEntity.created(location).body(creado);
    }

    // Obtener por ID -> 200 OK / 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Especialista> obtener(@PathVariable Long id) {
        Especialista esp = service.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialista " + id + " no encontrado"));
        return ResponseEntity.ok(esp);
    }

    // Actualizar -> 200 OK / 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<Especialista> actualizar(@PathVariable Long id, @RequestBody Especialista datos) {
        Especialista existente = service.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialista " + id + " no encontrado"));

        // Actualiza campos permitidos
        existente.setNombre(datos.getNombre());
        existente.setEspecialidad(datos.getEspecialidad());
        existente.setCorreo(datos.getCorreo());

        Especialista actualizado = service.guardar(existente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar -> 204 No Content / 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Especialista existente = service.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialista " + id + " no encontrado"));

        service.eliminar(existente.getId());
        return ResponseEntity.noContent().build();
    }
}
