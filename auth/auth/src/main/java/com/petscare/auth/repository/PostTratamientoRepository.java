package com.petscare.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petscare.auth.model.PostTratamiento;

/**
 * Acceso a datos para los seguimientos post-tratamiento.
 */
public interface PostTratamientoRepository extends JpaRepository<PostTratamiento, Long> {
    List<PostTratamiento> findByIdMascota(Long idMascota);
}
