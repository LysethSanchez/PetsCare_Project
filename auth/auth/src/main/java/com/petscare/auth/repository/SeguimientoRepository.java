package com.petscare.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petscare.auth.model.Seguimiento;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> {
    List<Seguimiento> findByIdMascota(Long idMascota);
}