package com.petscare.auth.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.HistorialMedico;
import com.petscare.auth.repository.HistorialMedicoRepository;

/**
 * Servicio que contiene la lógica para gestionar los historiales médicos.
 */
@Service
public class HistorialMedicoService {

    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    public HistorialMedico guardar(HistorialMedico historial) {
        // Si no se asignó una fecha, se pone la fecha actual
        if (historial.getFechaRegistro() == null) {
            historial.setFechaRegistro(LocalDateTime.now());
        }
        return historialMedicoRepository.save(historial);
    }

    public List<HistorialMedico> listarTodos() {
        return historialMedicoRepository.findAll();
    }

    public Optional<HistorialMedico> buscarPorId(Long id) {
        return historialMedicoRepository.findById(id);
    }

    public void eliminar(Long id) {
        historialMedicoRepository.deleteById(id);
    }
}
