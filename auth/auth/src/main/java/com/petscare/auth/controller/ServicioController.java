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
import com.petscare.auth.model.Servicio;
import com.petscare.auth.service.ServicioService;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    /** Crear servicio -> 201 Created + Location */
    @PostMapping
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        Servicio creado = servicioService.agregarServicio(servicio);
        URI location = URI.create("/api/servicios/" + creado.getId());
        return ResponseEntity.created(location).body(creado);
    }

    /** Listar todos -> 200 OK */
    @GetMapping
    public ResponseEntity<List<Servicio>> listarServicios() {
        return ResponseEntity.ok(servicioService.listarServicios());
    }

    /** Obtener por ID -> 200 OK | 404 Not Found */
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id) {
        Servicio s = servicioService.obtenerPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Servicio " + id + " no encontrado"));
        return ResponseEntity.ok(s);
    }

    /** Actualizar -> 200 OK | 404 Not Found */
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizar(@PathVariable Long id, @RequestBody Servicio servicio) {
        // Verifica existencia antes de actualizar
        servicioService.obtenerPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Servicio " + id + " no encontrado"));
        servicio.setId(id);
        Servicio actualizado = servicioService.actualizarServicio(servicio);
        return ResponseEntity.ok(actualizado);
    }

    /** Eliminar -> 204 No Content | 404 Not Found */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.obtenerPorId(id)
            .orElseThrow(() -> new ResourceNotFoundException("Servicio " + id + " no encontrado"));
        servicioService.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }
}
