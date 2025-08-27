package com.petscare.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.PostTratamiento;
import com.petscare.auth.repository.PostTratamientoRepository;

/**
 * Lógica de negocio para los seguimientos post-tratamiento.
 */
@Service
public class PostTratamientoService {

    @Autowired
    private PostTratamientoRepository repository;

    public PostTratamiento guardar(PostTratamiento pt) {
        return repository.save(pt);
    }

    public List<PostTratamiento> listar() {
        return repository.findAll();
    }

    public Optional<PostTratamiento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<PostTratamiento> buscarPorMascota(Long idMascota) {
        return repository.findByIdMascota(idMascota);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
