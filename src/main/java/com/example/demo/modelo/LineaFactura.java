package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LineaFactura {

    @Id @GeneratedValue
    private Long idLinea;

    @ManyToOne
    private ConsumoServ consumoServ;

    @ManyToOne
    private Alquiler alquiler;

    private double importe;

    // GETTERS Y SETTERS

    public Long getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Long idLinea) {
        this.idLinea = idLinea;
    }

    public ConsumoServ getConsumoServ() {
        return consumoServ;
    }

    public void setConsumoServ(ConsumoServ consumoServ) {
        this.consumoServ = consumoServ;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}

