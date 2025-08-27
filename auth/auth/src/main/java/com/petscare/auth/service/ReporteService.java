package com.petscare.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petscare.auth.model.Cita;
import com.petscare.auth.model.HistorialMedico;
import com.petscare.auth.model.Mascota;
import com.petscare.auth.model.PostTratamiento;
import com.petscare.auth.repository.CitaRepository;
import com.petscare.auth.repository.HistorialMedicoRepository;
import com.petscare.auth.repository.MascotaRepository;
import com.petscare.auth.repository.PostTratamientoRepository;

@Service
public class ReporteService {

    @Autowired
    private MascotaRepository mascotaRepo;

    @Autowired
    private CitaRepository citaRepo;

    @Autowired
    private HistorialMedicoRepository historialRepo;

    @Autowired
    private PostTratamientoRepository postTratamientoRepo;

    // Reporte: Mascotas de un propietario
    public List<Mascota> obtenerMascotasPorPropietario(Long idPropietario) {
        return mascotaRepo.findByIdPropietario(idPropietario);
    }

    // Reporte: Historial de una mascota
    public List<HistorialMedico> obtenerHistorialPorMascota(Long idMascota) {
        return historialRepo.findByIdMascota(idMascota);
    }

    // Reporte: Seguimientos de una mascota
    public List<PostTratamiento> obtenerSeguimientosPorMascota(Long idMascota) {
        return postTratamientoRepo.findByIdMascota(idMascota);
    }

    // Reporte: Citas asignadas a un especialista
    public List<Cita> obtenerCitasPorEspecialista(String nombreEspecialista) {
        return citaRepo.findByEspecialista(nombreEspecialista);
    }
}
