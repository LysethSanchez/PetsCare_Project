package com.petscare.auth.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa una cita médica o de servicio.
 */
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idMascota;
    private LocalDate fecha; // Formato: yyyy-MM-dd
    private String hora;     // Ej: 10:30
    private String tipoServicio; // Vacuna, Consulta, etc.
    private String especialista;
    private String estado; // Pendiente, Confirmada, Cancelada

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdMascota() { return idMascota; }
    public void setIdMascota(Long idMascota) { this.idMascota = idMascota; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }

    public String getEspecialista() { return especialista; }
    public void setEspecialista(String especialista) { this.especialista = especialista; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
