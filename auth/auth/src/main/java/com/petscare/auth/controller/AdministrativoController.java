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

import com.petscare.auth.model.Administrativo;
import com.petscare.auth.service.AdministrativoService;

@RestController
@RequestMapping("/api/administrativos")
@CrossOrigin(origins = "*")
public class AdministrativoController {

    @Autowired
    private AdministrativoService administrativoService;

    @PostMapping
    public Administrativo crear(@RequestBody Administrativo administrativo) {
        return administrativoService.crearAdministrativo(administrativo);
    }

    @GetMapping
    public List<Administrativo> listar() {
        return administrativoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Administrativo obtener(@PathVariable Long id) {
        return administrativoService.obtenerPorId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Administrativo actualizar(@PathVariable Long id, @RequestBody Administrativo datos) {
        return administrativoService.actualizarAdministrativo(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        administrativoService.eliminarAdministrativo(id);
    }
}
