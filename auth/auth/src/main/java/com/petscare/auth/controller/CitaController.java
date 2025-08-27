package com.petscare.auth.controller;

import java.util.List;
import java.util.Optional;

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

import com.petscare.auth.model.Cita;
import com.petscare.auth.service.CitaService;

/**
 * Controlador REST para gestionar citas.
 * Endpoints:
 *  GET    /api/citas                 -> Listar todas las citas
 *  POST   /api/citas                 -> Crear una cita
 *  GET    /api/citas/{id}            -> Obtener cita por ID
 *  PUT    /api/citas/{id}            -> Actualizar cita
 *  DELETE /api/citas/{id}            -> Eliminar cita
 *  GET    /api/citas/mascota/{id}    -> Listar citas de una mascota
 *  GET    /api/citas/especialista/{nombre} -> Listar citas por especialista
 */
@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    // Crear una nueva cita
    @PostMapping
    public Cita crear(@RequestBody Cita cita) {
        return citaService.crearCita(cita);
    }

    // Listar todas las citas
    @GetMapping
    public List<Cita> listar() {
        return citaService.listarCitas();
    }

    // Buscar una cita por ID
    @GetMapping("/{id}")
    public Optional<Cita> obtener(@PathVariable Long id) {
        return citaService.buscarPorId(id);
    }

    // Actualizar una cita
    @PutMapping("/{id}")
    public Cita actualizar(@PathVariable Long id, @RequestBody Cita cita) {
        cita.setId(id); // Asegura que se actualiza la cita correcta
        return citaService.actualizarCita(cita);
    }

    // Eliminar una cita
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        citaService.eliminarCita(id);
    }

    // Buscar citas por idMascota
    @GetMapping("/mascota/{idMascota}")
    public List<Cita> buscarPorMascota(@PathVariable Long idMascota) {
        return citaService.buscarPorMascota(idMascota);
    }

    // Buscar citas por especialista
    @GetMapping("/especialista/{nombre}")
    public List<Cita> buscarPorEspecialista(@PathVariable String nombre) {
        return citaService.buscarPorEspecialista(nombre);
    }

}
