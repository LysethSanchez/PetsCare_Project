package com.petscare.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petscare.auth.model.Seguimiento;
import com.petscare.auth.service.SeguimientoService;

@RestController
@RequestMapping("/api/seguimiento")
@CrossOrigin(origins = "*")
public class SeguimientoClienteController {

    @Autowired
    private SeguimientoService seguimientoService;

    // ✅ GET - Consultar seguimiento de una mascota
    @GetMapping("/seguimiento_mascota/{idMascota}")
    public List<Seguimiento> obtenerSeguimientoPorMascota(@PathVariable Long idMascota) {
        return seguimientoService.obtenerPorMascota(idMascota);
    }
}
