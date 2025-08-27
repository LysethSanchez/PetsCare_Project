package com.petscare.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.Especialista;
import com.petscare.auth.repository.EspecialistaRepository;

@Service
public class EspecialistaService {

    @Autowired
    private EspecialistaRepository repository;

    public List<Especialista> obtenerTodos() {
        return repository.findAll();
    }

    public Especialista guardar(Especialista especialista) {
        return repository.save(especialista);
    }

    public Optional<Especialista> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
