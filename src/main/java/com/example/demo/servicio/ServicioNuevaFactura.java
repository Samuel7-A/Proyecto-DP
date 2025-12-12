package com.example.demo.servicio;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.Alquiler;
import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.ConsumoServ;
import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.Factura;
import com.example.demo.modelo.LineaFactura;
import com.example.demo.repositorio.AlquilerRepo;
import com.example.demo.repositorio.ConsumoRepo;
import com.example.demo.repositorio.FacturaRepo;

@Service
public class ServicioNuevaFactura {

    @Autowired
    private FacturaRepo repo;

    @Autowired
    private AlquilerRepo alquilerRepo;

    @Autowired
    private ConsumoRepo consumoRepo;

    public double calcularTotal(List<LineaFactura> detalles) {
        return detalles.stream()
                .mapToDouble(LineaFactura::getImporte)
                .sum();
    }

    public Factura nueva(List<LineaFactura> detalles, Cliente cli, Empleado emp) {
        // Calcular importe de cada línea automáticamente
        for (LineaFactura linea : detalles) {
            double importeLinea = 0;

            // Si tiene alquiler, calcular precio
            if (linea.getIdAlq() != null) {
                Alquiler alq = alquilerRepo.findById(linea.getIdAlq()).orElse(null);
                if (alq != null) {
                    importeLinea += alq.getPrecioAlq() * alq.getHoraAlq();
                }
            }

            // Si tiene consumo de servicio, calcular precio
            if (linea.getIdConsServ() != null) {
                ConsumoServ cons = consumoRepo.findById(linea.getIdConsServ()).orElse(null);
                if (cons != null) {
                    importeLinea += cons.getPrecio() * cons.getHoras();
                }
            }

            linea.setImporte(importeLinea);
        }

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
