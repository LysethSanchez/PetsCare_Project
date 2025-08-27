package com.petscare.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petscare.auth.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByIdMascota(Long idMascota);
    //List<Cita> findByIdPropietario(Long idPropietario);
    List<Cita> findByEspecialista(String especialista);
}
