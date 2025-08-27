package com.petscare.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.petscare.auth.model.Horario;
import com.petscare.auth.service.HorarioService;

/**
 * Controlador REST para gestionar los horarios de los especialistas.
 * Endpoints:
 *  GET    /api/horarios                  -> Listar todos los horarios
 *  POST   /api/horarios                  -> Crear un nuevo horario
 *  GET    /api/horarios/especialista/{id} -> Listar horarios por especialista
 *  PUT    /api/horarios/{id}             -> Actualizar un horario
 *  DELETE /api/horarios/{id}             -> Eliminar un horario
 */
@RestController
@RequestMapping("/api/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    // Crear un nuevo horario
    @PostMapping
    public Horario crear(@RequestBody Horario horario) {
        return horarioService.guardar(horario);
    }

    // Listar todos los horarios
    @GetMapping
    public List<Horario> listarTodos() {
        return horarioService.obtenerTodos();
    }

    // Listar horarios de un especialista por su ID
    @GetMapping("/especialista/{id}")
    public List<Horario> obtenerPorEspecialista(@PathVariable Long id) {
        return horarioService.obtenerPorEspecialista(id);
    }

    // Actualizar un horario
    @PutMapping("/{id}")
    public Horario actualizar(@PathVariable Long id, @RequestBody Horario horario) {
        // Buscar si existe
        Horario existente = horarioService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        // Actualizamos los campos
        existente.setEspecialistaId(horario.getEspecialistaId());
        existente.setFechaHoraInicio(horario.getFechaHoraInicio());
        existente.setFechaHoraFin(horario.getFechaHoraFin());
        existente.setDescripcion(horario.getDescripcion());

        return horarioService.guardar(existente);
    }

    // Eliminar un horario por su ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        horarioService.eliminar(id);
    }
}
