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
import com.petscare.auth.model.Administrativo;
import com.petscare.auth.service.AdministrativoService;

/**
 * Controlador REST para gestionar administrativos.
 * Endpoints:
 *  GET    /api/administrativos         -> Listar todos (200)
 *  POST   /api/administrativos         -> Crear administrativo (201 + Location)
 *  GET    /api/administrativos/{id}    -> Obtener por ID (200 / 404)
 *  PUT    /api/administrativos/{id}    -> Actualizar (200 / 404)
 *  DELETE /api/administrativos/{id}    -> Eliminar (204 / 404)
 */
@RestController
@RequestMapping("/api/administrativos")
@CrossOrigin(origins = "*")
public class AdministrativoController {

    @Autowired
    private AdministrativoService administrativoService;

    // Crear administrativo -> 201 Created
    @PostMapping
    public ResponseEntity<Administrativo> crear(@RequestBody Administrativo administrativo) {
        Administrativo creado = administrativoService.crearAdministrativo(administrativo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();

        return ResponseEntity.created(location).body(creado);
    }

    // Listar todos -> 200 OK
    @GetMapping
    public ResponseEntity<List<Administrativo>> listar() {
        return ResponseEntity.ok(administrativoService.obtenerTodos());
    }

    // Obtener por ID -> 200 OK / 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Administrativo> obtener(@PathVariable Long id) {
        Administrativo admin = administrativoService.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrativo " + id + " no encontrado"));
        return ResponseEntity.ok(admin);
    }

    // Actualizar -> 200 OK / 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<Administrativo> actualizar(@PathVariable Long id, @RequestBody Administrativo datos) {
        Administrativo existente = administrativoService.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrativo " + id + " no encontrado"));

        existente.setNombre(datos.getNombre());
        existente.setCargo(datos.getCargo());
        existente.setEmail(datos.getEmail());
        existente.setTelefono(datos.getTelefono());

        Administrativo actualizado = administrativoService.crearAdministrativo(existente);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar -> 204 No Content / 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Administrativo existente = administrativoService.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrativo " + id + " no encontrado"));

        administrativoService.eliminarAdministrativo(existente.getId());
        return ResponseEntity.noContent().build();
    }
}
