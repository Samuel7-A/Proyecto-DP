package com.example.demo.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<LineaFactura> lineasFinales = new ArrayList<>();

        for (LineaFactura linea : detalles) {
            // Si tiene alquiler, crear línea con descripción
            if (linea.getIdAlq() != null) {
                Alquiler alq = alquilerRepo.findById(linea.getIdAlq()).orElse(null);
                if (alq != null) {
                    LineaFactura lineaAlq = new LineaFactura();
                    lineaAlq.setDescripcion("Alquiler: " + alq.getNomAlq());
                    lineaAlq.setImporte(alq.getPrecioAlq() * alq.getHoraAlq());
                    lineasFinales.add(lineaAlq);
                }
            }

            // Si tiene consumos, crear una línea por cada consumo
            if (linea.getIdsConsServ() != null && !linea.getIdsConsServ().isEmpty()) {
                for (Long idCons : linea.getIdsConsServ()) {
                    ConsumoServ cons = consumoRepo.findById(idCons).orElse(null);
                    if (cons != null) {
                        LineaFactura lineaCons = new LineaFactura();
                        lineaCons.setDescripcion("Servicio: " + cons.getServicio());
                        lineaCons.setImporte(cons.getPrecio() * cons.getHoras());
                        lineasFinales.add(lineaCons);
                    }
                }
            }
        }

        Factura f = new Factura();
        f.setFechaFact(LocalDate.now());
        f.setCliente(cli);
        f.setEmpleado(emp);
        f.setDetalles(lineasFinales);
        f.setTotal(calcularTotal(lineasFinales));
        return f;
    }

    public Factura grabar(Factura f) {
        return repo.save(f);
    }
}
