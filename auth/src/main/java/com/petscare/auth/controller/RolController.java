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
import com.petscare.auth.model.Rol;
import com.petscare.auth.service.RolService;

/**
 * Controlador REST para Roles.
 * Endpoints:
 *  POST   /api/roles          -> Crear (201 + Location)
 *  GET    /api/roles          -> Listar (200)
 *  GET    /api/roles/{id}     -> Obtener por id (200 / 404)
 *  PUT    /api/roles/{id}     -> Actualizar (200 / 404)
 *  DELETE /api/roles/{id}     -> Eliminar (204 / 404)
 */
@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RolController {

    @Autowired
    private RolService rolService;

    // Crear rol -> 201 Created + Location
    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Rol creado = rolService.crearRol(rol);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();

        return ResponseEntity.created(location).body(creado);
    }

    // Listar roles -> 200 OK
    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    // Obtener rol por id -> 200 OK / 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRol(@PathVariable Long id) {
        Rol rol = rolService.obtenerRolPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol " + id + " no encontrado"));
        return ResponseEntity.ok(rol);
    }

    // Actualizar rol -> 200 OK / 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol cambios) {
        Rol existente = rolService.obtenerRolPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol " + id + " no encontrado"));

        existente.setNombre(cambios.getNombre());

        Rol actualizado = rolService.crearRol(existente); // save
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar rol -> 204 No Content / 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        Rol existente = rolService.obtenerRolPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol " + id + " no encontrado"));

        rolService.eliminarRol(existente.getId());
        return ResponseEntity.noContent().build();
    }
}
