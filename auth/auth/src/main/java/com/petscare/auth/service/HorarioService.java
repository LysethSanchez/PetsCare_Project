package com.petscare.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.Horario;
import com.petscare.auth.repository.HorarioRepository;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    public Horario guardar(Horario horario) {
        return horarioRepository.save(horario);
    }

    public List<Horario> obtenerTodos() {
        return horarioRepository.findAll();
    }

    public List<Horario> obtenerPorEspecialista(Long especialistaId) {
        return horarioRepository.findByEspecialistaId(especialistaId);
    }

    public Optional<Horario> obtenerPorId(Long id) {
        return horarioRepository.findById(id);
    }

    public void eliminar(Long id) {
        horarioRepository.deleteById(id);
    }
}
