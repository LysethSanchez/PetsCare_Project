package com.petscare.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petscare.auth.model.Cita;
import com.petscare.auth.model.HistorialMedico;
import com.petscare.auth.model.Mascota;
import com.petscare.auth.model.PostTratamiento;
import com.petscare.auth.service.ReporteService;

/**
 * Controlador REST para generar reportes de mascotas, citas,
 * historiales médicos y seguimientos.
 */
@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // Mascotas de un propietario
    @GetMapping("/mascotas/{idPropietario}")
    public List<Mascota> obtenerMascotas(@PathVariable Long idPropietario) {
        return reporteService.obtenerMascotasPorPropietario(idPropietario);
    }

    // Historial de una mascota
    @GetMapping("/historial/{idMascota}")
    public List<HistorialMedico> obtenerHistorial(@PathVariable Long idMascota) {
        return reporteService.obtenerHistorialPorMascota(idMascota);
    }

    // Seguimientos (post-tratamientos) de una mascota
    @GetMapping("/seguimiento/{idMascota}")
    public List<PostTratamiento> obtenerSeguimientos(@PathVariable Long idMascota) {
        return reporteService.obtenerSeguimientosPorMascota(idMascota);
    }

    // Citas asignadas a un especialista por su nombre
    @GetMapping("/citas-especialista/{nombre}")
    public List<Cita> obtenerCitasEspecialista(@PathVariable String nombre) {
        return reporteService.obtenerCitasPorEspecialista(nombre);
    }
}
