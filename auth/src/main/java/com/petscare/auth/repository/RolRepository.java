package com.petscare.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petscare.auth.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
