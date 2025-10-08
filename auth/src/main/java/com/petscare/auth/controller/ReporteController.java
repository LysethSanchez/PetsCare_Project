package com.petscare.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petscare.auth.config.ResourceNotFoundException;
import com.petscare.auth.model.Cita;
import com.petscare.auth.model.HistorialMedico;
import com.petscare.auth.model.Mascota;
import com.petscare.auth.model.PostTratamiento;
import com.petscare.auth.service.ReporteService;

/**
 * Controlador REST para reportes (consultas de solo lectura).
 *
 * Códigos HTTP:
 * - 200 OK  : cuando hay resultados
 * - 404 Not Found : cuando no existen datos para el criterio solicitado
 */
@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    /** Mascotas por propietario -> 200 | 404 */
    @GetMapping("/mascotas/{idPropietario}")
    public ResponseEntity<List<Mascota>> obtenerMascotas(@PathVariable Long idPropietario) {
        List<Mascota> lista = reporteService.obtenerMascotasPorPropietario(idPropietario);
        if (lista == null || lista.isEmpty()) {
            throw new ResourceNotFoundException("No hay mascotas para el propietario " + idPropietario);
        }
        return ResponseEntity.ok(lista);
    }

    /** Historial médico por mascota -> 200 | 404 */
    @GetMapping("/historial/{idMascota}")
    public ResponseEntity<List<HistorialMedico>> obtenerHistorial(@PathVariable Long idMascota) {
        List<HistorialMedico> lista = reporteService.obtenerHistorialPorMascota(idMascota);
        if (lista == null || lista.isEmpty()) {
            throw new ResourceNotFoundException("No hay historial médico para la mascota " + idMascota);
        }
        return ResponseEntity.ok(lista);
    }

    /** Seguimientos (post-tratamientos) por mascota -> 200 | 404 */
    @GetMapping("/seguimiento/{idMascota}")
    public ResponseEntity<List<PostTratamiento>> obtenerSeguimientos(@PathVariable Long idMascota) {
        List<PostTratamiento> lista = reporteService.obtenerSeguimientosPorMascota(idMascota);
        if (lista == null || lista.isEmpty()) {
            throw new ResourceNotFoundException("No hay seguimientos para la mascota " + idMascota);
        }
        return ResponseEntity.ok(lista);
    }

    /** Citas asignadas a un especialista por su nombre -> 200 | 404 */
    @GetMapping("/citas-especialista/{nombre}")
    public ResponseEntity<List<Cita>> obtenerCitasEspecialista(@PathVariable String nombre) {
        List<Cita> lista = reporteService.obtenerCitasPorEspecialista(nombre);
        if (lista == null || lista.isEmpty()) {
            throw new ResourceNotFoundException("No hay citas asignadas al especialista " + nombre);
        }
        return ResponseEntity.ok(lista);
    }
}
