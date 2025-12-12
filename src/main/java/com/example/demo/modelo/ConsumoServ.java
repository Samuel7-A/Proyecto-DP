package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ConsumoServ {

    @Id @GeneratedValue
    private Long idConsumoServ;

    private double horasConsServ;
    private String servicio;
    private double horas;
    private double precio;

    // GETTERS Y SETTERS

    public Long getIdConsumoServ() {
        return idConsumoServ;
    }

    public void setIdConsumoServ(Long idConsumoServ) {
        this.idConsumoServ = idConsumoServ;
    }

    public double getHorasConsServ() {
        return horasConsServ;
    }

    public void setHorasConsServ(double horasConsServ) {
        this.horasConsServ = horasConsServ;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

