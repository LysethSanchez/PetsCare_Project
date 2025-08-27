package com.petscare.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petscare.auth.model.Servicio;

/**
 * Repositorio para la entidad Servicio.
 */
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    // No necesita métodos adicionales por ahora.
}
