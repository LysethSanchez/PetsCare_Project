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
import com.petscare.auth.model.Seguimiento;
import com.petscare.auth.service.SeguimientoService;

/**
 * Controlador REST para consultar seguimientos de clientes sobre sus mascotas.
 *
 * Endpoints:
 *  GET /api/seguimiento/mascota/{idMascota} -> Lista de seguimientos de la mascota
 *
 * Códigos:
 *  200 OK  -> cuando existen seguimientos
 *  404 Not Found -> cuando no se encuentran registros
 */
@RestController
@RequestMapping("/api/seguimiento")
@CrossOrigin(origins = "*")
public class SeguimientoClienteController {

    @Autowired
    private SeguimientoService seguimientoService;

    /** Consultar seguimiento de una mascota -> 200 OK | 404 Not Found */
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<Seguimiento>> obtenerSeguimientoPorMascota(@PathVariable Long idMascota) {
        List<Seguimiento> lista = seguimientoService.obtenerPorMascota(idMascota);
        if (lista == null || lista.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron seguimientos para la mascota " + idMascota);
        }
        return ResponseEntity.ok(lista);
    }
}
