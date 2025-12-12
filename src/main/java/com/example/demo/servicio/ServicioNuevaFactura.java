package com.example.demo.servicio;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.Factura;
import com.example.demo.modelo.LineaFactura;
import com.example.demo.repositorio.FacturaRepo;

@Service
public class ServicioNuevaFactura {

    @Autowired
    private FacturaRepo repo;

    public double calcularTotal(List<LineaFactura> detalles) {
        return detalles.stream()
                .mapToDouble(LineaFactura::getImporte)
                .sum();
    }

    public Factura nueva(List<LineaFactura> detalles, Cliente cli, Empleado emp) {
        Factura f = new Factura();
        f.setFechaFact(LocalDate.now());
        f.setCliente(cli);
        f.setEmpleado(emp);
        f.setDetalles(detalles);
        f.setTotal(calcularTotal(detalles));
        return f;
    }

    public Factura grabar(Factura f) {
        return repo.save(f);
    }
}
