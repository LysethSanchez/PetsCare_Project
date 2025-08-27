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

import com.petscare.auth.model.PostTratamiento;
import com.petscare.auth.service.PostTratamientoService;

/**
 * Controlador REST para gestionar los seguimientos post-tratamiento.
 * Endpoints:
 *  GET    /api/seguimiento                 -> Listar todos
 *  POST   /api/seguimiento                 -> Crear seguimiento
 *  GET    /api/seguimiento/{id}            -> Buscar por id
 *  PUT    /api/seguimiento/{id}            -> Actualizar seguimiento
 *  DELETE /api/seguimiento/{id}            -> Eliminar seguimiento
 *  GET    /api/seguimiento/mascota/{id}    -> Buscar seguimientos de una mascota
 */
@RestController
@RequestMapping("/api/posttratamiento")
@CrossOrigin(origins = "*")
public class PostTratamientoController {

    @Autowired
    private PostTratamientoService service;

    // Crear un nuevo seguimiento
    @PostMapping
    public PostTratamiento guardar(@RequestBody PostTratamiento pt) {
        return service.guardar(pt);
    }

    // Listar todos los seguimientos
    @GetMapping
    public List<PostTratamiento> listar() {
        return service.listar();
    }

    // Obtener seguimiento por ID
    @GetMapping("/{id}")
    public PostTratamiento obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Seguimiento no encontrado"));
    }

    // Actualizar seguimiento
    @PutMapping("/{id}")
    public PostTratamiento actualizar(@PathVariable Long id, @RequestBody PostTratamiento actualizado) {
        PostTratamiento existente = service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Seguimiento no encontrado"));

        // Campos que se pueden actualizar
        existente.setDescripcion(actualizado.getDescripcion());
        existente.setFechaRecomendacion(actualizado.getFechaRecomendacion());
        existente.setRecomendaciones(actualizado.getRecomendaciones());
        existente.setEspecialista(actualizado.getEspecialista());

        return service.guardar(existente);
    }

    // Eliminar seguimiento
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // Listar seguimientos de una mascota específica
    @GetMapping("/mascota/{idMascota}")
    public List<PostTratamiento> porMascota(@PathVariable Long idMascota) {
        return service.buscarPorMascota(idMascota);
    }
}
