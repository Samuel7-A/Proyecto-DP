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
import com.example.demo.repositorio.ClienteRepo;
import com.example.demo.repositorio.ConsumoRepo;
import com.example.demo.repositorio.EmpleadoRepo;
import com.example.demo.repositorio.FacturaRepo;

@Service
public class ServicioNuevaFactura {

    @Autowired
    private FacturaRepo repo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private EmpleadoRepo empleadoRepo;

    @Autowired
    private AlquilerRepo alquilerRepo;

    @Autowired
    private ConsumoRepo consumoRepo;

    public double calcularTotal(List<LineaFactura> detalles) {
        double total = 0;
        for (LineaFactura linea : detalles) {
            total += linea.getImporte();
        }
        return total;
    }

    public Factura nueva(List<LineaFactura> detalles, Cliente cli, Empleado emp) {
        // Cargar cliente completo desde BD
        Cliente clienteCompleto = clienteRepo.findById(cli.getIdCliente()).orElse(null);
        
        // Cargar empleado completo desde BD
        Empleado empleadoCompleto = empleadoRepo.findById(emp.getIdEmpleado()).orElse(null);

        // Procesar cada línea de detalle
        for (LineaFactura linea : detalles) {
            double importeLinea = 0;
            
            // Cargar consumo completo desde BD
            if (linea.getConsumoServ() != null && linea.getConsumoServ().getIdConsumoServ() != null) {
                ConsumoServ consumoCompleto = consumoRepo.findById(linea.getConsumoServ().getIdConsumoServ()).orElse(null);
                linea.setConsumoServ(consumoCompleto);
                if (consumoCompleto != null) {
                    importeLinea += consumoCompleto.getPrecio() * consumoCompleto.getHoras();
                }
            }
            
            // Cargar alquiler completo desde BD
            if (linea.getAlquiler() != null && linea.getAlquiler().getIdAlq() != null) {
                Alquiler alquilerCompleto = alquilerRepo.findById(linea.getAlquiler().getIdAlq()).orElse(null);
                linea.setAlquiler(alquilerCompleto);
                if (alquilerCompleto != null) {
                    importeLinea += alquilerCompleto.getPrecioAlq() * alquilerCompleto.getHoraAlq();
                }
            }
            
            linea.setImporte(importeLinea);
        }

        Factura f = new Factura();
        f.setFechaFact(LocalDate.now());
        f.setCliente(clienteCompleto);
        f.setEmpleado(empleadoCompleto);
        f.setDetalles(detalles);
        f.setTotal(calcularTotal(detalles));
        return f;
    }

    public Factura grabar(Factura f) {
        return repo.save(f);
    }
}
