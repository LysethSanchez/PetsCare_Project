package com.petscare.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.Seguimiento;
import com.petscare.auth.repository.SeguimientoRepository;

@Service
public class SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    public List<Seguimiento> obtenerPorMascota(Long idMascota) {
        return seguimientoRepository.findByIdMascota(idMascota);
    }
}
