package com.petscare.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petscare.auth.model.Administrativo;

public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {
}
