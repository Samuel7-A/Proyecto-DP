package com.example.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LineaFactura {

    @Id @GeneratedValue
    private Long idLinea;

    private Long idConsServ;
    private Long idAlq;
    private double importe;

    // GETTERS Y SETTERS

    public Long getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Long idLinea) {
        this.idLinea = idLinea;
    }

    public Long getIdConsServ() {
        return idConsServ;
    }

    public void setIdConsServ(Long idConsServ) {
        this.idConsServ = idConsServ;
    }

    public Long getIdAlq() {
        return idAlq;
    }

    public void setIdAlq(Long idAlq) {
        this.idAlq = idAlq;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}

