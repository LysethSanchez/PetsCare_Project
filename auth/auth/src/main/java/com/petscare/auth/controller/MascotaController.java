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

import com.petscare.auth.model.Mascota;
import com.petscare.auth.service.MascotaService;

/**
 * Controlador REST para gestionar las mascotas.
 * Endpoints:
 *  GET    /api/mascotas        -> Listar todas las mascotas
 *  POST   /api/mascotas        -> Registrar una nueva mascota
 *  GET    /api/mascotas/{id}   -> Buscar mascota por ID
 *  GET    /api/mascotas/propietario/{idPropietario} -> Mascotas por propietario
 *  PUT    /api/mascotas/{id}   -> Actualizar una mascota
 *  DELETE /api/mascotas/{id}   -> Eliminar mascota
 */
@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    // Registrar mascota
    @PostMapping
    public Mascota registrar(@RequestBody Mascota mascota) {
        return mascotaService.registrar(mascota);
    }

    // Listar todas las mascotas
    @GetMapping
    public List<Mascota> listar() {
        return mascotaService.listar();
    }

    // Buscar mascota por ID
    @GetMapping("/{id}")
    public Optional<Mascota> buscarPorId(@PathVariable Long id) {
        return mascotaService.buscarPorId(id);
    }

    // Buscar mascotas por ID del propietario
    @GetMapping("/propietario/{idPropietario}")
    public List<Mascota> buscarPorPropietario(@PathVariable Long idPropietario) {
        return mascotaService.buscarPorPropietario(idPropietario);
    }

    // Actualizar mascota usando el ID en la URL
    @PutMapping("/{id}")
    public Mascota actualizar(@PathVariable Long id, @RequestBody Mascota mascota) {
        mascota.setId(id); // aseguramos que se actualiza la correcta
        return mascotaService.actualizar(mascota);
    }

    // Eliminar mascota
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        mascotaService.eliminar(id);
    }
}
