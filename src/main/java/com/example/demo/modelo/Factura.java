package com.example.demo.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Factura {

    @Id @GeneratedValue
    private Long idFactura;

    private LocalDate fechaFact;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Empleado empleado;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LineaFactura> detalles;

    private double total;

    // GETTERS Y SETTERS

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public LocalDate getFechaFact() {
        return fechaFact;
    }

    public void setFechaFact(LocalDate fechaFact) {
        this.fechaFact = fechaFact;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<LineaFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<LineaFactura> detalles) {
        this.detalles = detalles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
