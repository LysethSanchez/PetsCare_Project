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

import com.petscare.auth.model.Especialista;
import com.petscare.auth.service.EspecialistaService;

/**
 * Controlador REST para gestionar especialistas.
 * Endpoints:
 *  GET    /api/especialistas         -> Listar todos
 *  POST   /api/especialistas         -> Crear especialista
 *  GET    /api/especialistas/{id}    -> Obtener especialista por ID
 *  PUT    /api/especialistas/{id}    -> Actualizar especialista
 *  DELETE /api/especialistas/{id}    -> Eliminar especialista
 */
@RestController
@RequestMapping("/api/especialistas")
@CrossOrigin(origins = "*")
public class EspecialistaController {

    @Autowired
    private EspecialistaService service;

    // Listar todos los especialistas
    @GetMapping
    public List<Especialista> listar() {
        return service.obtenerTodos();
    }

    // Crear nuevo especialista
    @PostMapping
    public Especialista crear(@RequestBody Especialista especialista) {
        return service.guardar(especialista);
    }

    // Obtener especialista por ID
    @GetMapping("/{id}")
    public Especialista obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado"));
    }

    // Actualizar especialista
    @PutMapping("/{id}")
    public Especialista actualizar(@PathVariable Long id, @RequestBody Especialista especialista) {
        // Verificar si existe
        Especialista existente = service.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Especialista no encontrado"));

        // Actualizamos solo los campos necesarios
        existente.setNombre(especialista.getNombre());
        existente.setEspecialidad(especialista.getEspecialidad());
        existente.setCorreo(especialista.getCorreo());

        return service.guardar(existente);
    }

    // Eliminar especialista
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
