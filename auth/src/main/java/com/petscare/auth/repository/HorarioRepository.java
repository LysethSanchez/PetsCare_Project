package com.petscare.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petscare.auth.model.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByEspecialistaId(Long especialistaId);
}
