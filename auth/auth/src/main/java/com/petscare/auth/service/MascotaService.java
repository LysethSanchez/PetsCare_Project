package com.petscare.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.Mascota;
import com.petscare.auth.repository.MascotaRepository;

/**
 * Lógica de negocio para la gestión de mascotas
 */
@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public Mascota registrar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> listar() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> buscarPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    public List<Mascota> buscarPorPropietario(Long idPropietario) {
        return mascotaRepository.findByIdPropietario(idPropietario);
    }

    public Mascota actualizar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public void eliminar(Long id) {
        mascotaRepository.deleteById(id);
    }
}
