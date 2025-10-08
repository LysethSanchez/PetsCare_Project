package com.petscare.auth.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.Servicio;
import com.petscare.auth.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public Servicio agregarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    public Optional<Servicio> obtenerPorId(Long id) {
        return servicioRepository.findById(id);
    }

    public Servicio actualizarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }
}
