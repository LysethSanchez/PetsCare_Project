package com.petscare.auth.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long especialistaId; // ID del especialista
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    private String descripcion; // Consulta, cirugía, etc.

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEspecialistaId() { return especialistaId; }
    public void setEspecialistaId(Long especialistaId) { this.especialistaId = especialistaId; }

    public LocalDateTime getFechaHoraInicio() { return fechaHoraInicio; }
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) { this.fechaHoraInicio = fechaHoraInicio; }

    public LocalDateTime getFechaHoraFin() { return fechaHoraFin; }
    public void setFechaHoraFin(LocalDateTime fechaHoraFin) { this.fechaHoraFin = fechaHoraFin; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
