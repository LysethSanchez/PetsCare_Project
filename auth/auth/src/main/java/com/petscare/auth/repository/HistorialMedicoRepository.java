package com.petscare.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petscare.auth.model.HistorialMedico;

/**
 * Acceso a datos para la entidad HistorialMedico
 */
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {
    List<HistorialMedico> findByIdMascota(Long idMascota);
}
