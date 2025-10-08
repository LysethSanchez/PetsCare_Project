package com.petscare.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.Administrativo;
import com.petscare.auth.repository.AdministrativoRepository;

@Service
public class AdministrativoService {

    @Autowired
    private AdministrativoRepository administrativoRepository;

    public Administrativo crearAdministrativo(Administrativo administrativo) {
        return administrativoRepository.save(administrativo);
    }

    public List<Administrativo> obtenerTodos() {
        return administrativoRepository.findAll();
    }

    public Optional<Administrativo> obtenerPorId(Long id) {
        return administrativoRepository.findById(id);
    }

    public Administrativo actualizarAdministrativo(Long id, Administrativo datos) {
        return administrativoRepository.findById(id).map(admin -> {
            admin.setNombre(datos.getNombre());
            admin.setCargo(datos.getCargo());
            admin.setEmail(datos.getEmail());
            admin.setTelefono(datos.getTelefono());
            return administrativoRepository.save(admin);
        }).orElse(null);
    }

    public void eliminarAdministrativo(Long id) {
        administrativoRepository.deleteById(id);
    }
}
