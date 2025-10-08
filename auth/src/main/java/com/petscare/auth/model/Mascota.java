package com.petscare.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreMascota;
    private String especie;
    private String raza;
    private String sexo;
    private int edad;
    private String fecha;
    private Long idPropietario;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreMascota() { return nombreMascota; }
    public void setNombreMascota(String nombreMascota) { this.nombreMascota = nombreMascota; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public Long getIdPropietario() { return idPropietario; }
    public void setIdPropietario(Long idPropietario) { this.idPropietario = idPropietario; }
}
