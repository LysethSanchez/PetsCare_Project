package com.petscare.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petscare.auth.model.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByIdPropietario(Long idPropietario);
}
