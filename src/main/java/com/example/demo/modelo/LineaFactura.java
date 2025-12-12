package com.example.demo.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.util.List;

@Entity
public class LineaFactura {

    @Id @GeneratedValue
    private Long idLinea;

    private String descripcion;
    private double importe;

    // Campos transitorios para recibir IDs en el JSON (no se guardan en BD)
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idAlq;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Long> idsConsServ;

    // GETTERS Y SETTERS

    public Long getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Long idLinea) {
        this.idLinea = idLinea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdAlq() {
        return idAlq;
    }

    public void setIdAlq(Long idAlq) {
        this.idAlq = idAlq;
    }

    public List<Long> getIdsConsServ() {
        return idsConsServ;
    }

    public void setIdsConsServ(List<Long> idsConsServ) {
        this.idsConsServ = idsConsServ;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}

