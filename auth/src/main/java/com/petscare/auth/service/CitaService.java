package com.petscare.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.Cita;
import com.petscare.auth.repository.CitaRepository;

/**
 * Lógica de negocio para citas.
 */
@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    // Crear cita con estado por defecto "Pendiente"
    public Cita crearCita(Cita cita) {
        cita.setEstado("Pendiente");
        return citaRepository.save(cita);
    }

    // Listar todas las citas
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    // Buscar cita por ID
    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    // Eliminar cita
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }

    // Actualizar cita
    public Cita actualizarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    // Buscar citas por idMascota
    public List<Cita> buscarPorMascota(Long idMascota) {
        return citaRepository.findByIdMascota(idMascota);
    }

    // Buscar citas por especialista
    public List<Cita> buscarPorEspecialista(String especialista) {
        return citaRepository.findByEspecialista(especialista);
    }

}
