package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Alquiler {

    @Id @GeneratedValue
    private Long idAlq;

    private String nomAlq;
    private double horaAlq;
    private double precioAlq;

    // GETTERS Y SETTERS

    public Long getIdAlq() {
        return idAlq;
    }

    public void setIdAlq(Long idAlq) {
        this.idAlq = idAlq;
    }

    public String getNomAlq() {
        return nomAlq;
    }

    public void setNomAlq(String nomAlq) {
        this.nomAlq = nomAlq;
    }

    public double getHoraAlq() {
        return horaAlq;
    }

    public void setHoraAlq(double horaAlq) {
        this.horaAlq = horaAlq;
    }

    public double getPrecioAlq() {
        return precioAlq;
    }

    public void setPrecioAlq(double precioAlq) {
        this.precioAlq = precioAlq;
    }
}

