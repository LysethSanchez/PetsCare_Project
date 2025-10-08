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
import com.petscare.auth.model.PostTratamiento;
import com.petscare.auth.service.PostTratamientoService;

/**
 * Controlador REST para gestionar los seguimientos post-tratamiento.
 * Endpoints:
 *  GET    /api/posttratamiento                 -> Listar todos
 *  POST   /api/posttratamiento                 -> Crear seguimiento (201 Created)
 *  GET    /api/posttratamiento/{id}            -> Buscar por id (200 / 404)
 *  PUT    /api/posttratamiento/{id}            -> Actualizar seguimiento (200 / 404)
 *  DELETE /api/posttratamiento/{id}            -> Eliminar seguimiento (204 / 404)
 *  GET    /api/posttratamiento/mascota/{id}    -> Buscar seguimientos de una mascota (200)
 */
@RestController
@RequestMapping("/api/posttratamiento")
@CrossOrigin(origins = "*")
public class PostTratamientoController {

    @Autowired
    private PostTratamientoService service;

    // Crear un nuevo seguimiento -> 201 Created + Location
    @PostMapping
    public ResponseEntity<PostTratamiento> guardar(@RequestBody PostTratamiento pt) {
        PostTratamiento guardado = service.guardar(pt);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guardado.getId())
                .toUri();

        return ResponseEntity.created(location).body(guardado);
    }

    // Listar todos -> 200 OK
    @GetMapping
    public ResponseEntity<List<PostTratamiento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // Obtener por ID -> 200 OK / 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<PostTratamiento> obtener(@PathVariable Long id) {
        PostTratamiento encontrado = service.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seguimiento " + id + " no encontrado"));
        return ResponseEntity.ok(encontrado);
    }

    // Actualizar seguimiento -> 200 OK / 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<PostTratamiento> actualizar(@PathVariable Long id, @RequestBody PostTratamiento actualizado) {
        PostTratamiento existente = service.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seguimiento " + id + " no encontrado"));

        // Campos editables
        existente.setDescripcion(actualizado.getDescripcion());
        existente.setFechaRecomendacion(actualizado.getFechaRecomendacion());
        existente.setRecomendaciones(actualizado.getRecomendaciones());
        existente.setEspecialista(actualizado.getEspecialista());

        PostTratamiento result = service.guardar(existente);
        return ResponseEntity.ok(result);
    }

    // Eliminar -> 204 No Content / 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        PostTratamiento existente = service.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seguimiento " + id + " no encontrado"));

        service.eliminar(existente.getId());
        return ResponseEntity.noContent().build();
    }

    // Listar seguimientos de una mascota -> 200 OK
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<PostTratamiento>> porMascota(@PathVariable Long idMascota) {
        return ResponseEntity.ok(service.buscarPorMascota(idMascota));
    }
}
