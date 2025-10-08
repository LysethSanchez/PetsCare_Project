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
import com.petscare.auth.model.Mascota;
import com.petscare.auth.service.MascotaService;

/**
 * Controlador REST para gestionar las mascotas.
 * Endpoints:
 *  GET    /api/mascotas                         -> Listar todas las mascotas
 *  POST   /api/mascotas                         -> Registrar una nueva mascota (201 + Location)
 *  GET    /api/mascotas/{id}                    -> Buscar mascota por ID (404 si no existe)
 *  GET    /api/mascotas/propietario/{idPropietario} -> Mascotas por propietario
 *  PUT    /api/mascotas/{id}                    -> Actualizar una mascota (404 si no existe)
 *  DELETE /api/mascotas/{id}                    -> Eliminar mascota (204)
 */
@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    /** Registrar mascota -> 201 Created + Location */
    @PostMapping
    public ResponseEntity<Mascota> registrar(@RequestBody Mascota mascota) {
        Mascota guardada = mascotaService.registrar(mascota);
        URI location = URI.create("/api/mascotas/" + guardada.getId());
        return ResponseEntity.created(location).body(guardada);
    }

    /** Listar todas -> 200 OK */
    @GetMapping
    public ResponseEntity<List<Mascota>> listar() {
        return ResponseEntity.ok(mascotaService.listar());
    }

    /** Obtener por id -> 200 OK | 404 Not Found */
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> buscarPorId(@PathVariable Long id) {
        Mascota m = mascotaService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota " + id + " no encontrada"));
        return ResponseEntity.ok(m);
    }

    /** Buscar por propietario -> 200 OK */
    @GetMapping("/propietario/{idPropietario}")
    public ResponseEntity<List<Mascota>> buscarPorPropietario(@PathVariable Long idPropietario) {
        return ResponseEntity.ok(mascotaService.buscarPorPropietario(idPropietario));
    }

    /** Actualizar -> 200 OK | 404 Not Found */
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizar(@PathVariable Long id, @RequestBody Mascota mascota) {
        // Validar existencia antes de actualizar
        mascotaService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota " + id + " no encontrada"));
        mascota.setId(id);
        Mascota actualizada = mascotaService.actualizar(mascota);
        return ResponseEntity.ok(actualizada);
    }

    /** Eliminar -> 204 No Content | 404 Not Found */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mascotaService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota " + id + " no encontrada"));
        mascotaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
