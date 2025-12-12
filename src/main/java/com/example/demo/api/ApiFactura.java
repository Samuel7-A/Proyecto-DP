package com.example.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.modelo.Cliente;
import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.Factura;
import com.example.demo.modelo.LineaFactura;
import com.example.demo.servicio.ServicioFactura;
import com.example.demo.servicio.ServicioNuevaFactura;
import com.example.demo.repositorio.ClienteRepo;
import com.example.demo.repositorio.EmpleadoRepo;


@RestController
@RequestMapping("/factura")
public class ApiFactura {

    @Autowired
    ServicioNuevaFactura servicioNueva;

    @Autowired
    ServicioFactura servNueva;

    @Autowired
    ClienteRepo clienteRepo;

    @Autowired
    EmpleadoRepo empleadoRepo;

    @PostMapping("/crear/{idCliente}/{idEmpleado}")
    public Factura crearFactura(
            @PathVariable Long idCliente,
            @PathVariable Long idEmpleado,
            @RequestBody List<LineaFactura> detalles
    ) {
        Cliente c = clienteRepo.findById(idCliente).orElse(null);
        Empleado e = empleadoRepo.findById(idEmpleado).orElse(null);

        Factura f = servicioNueva.nueva(detalles, c, e);
        servicioNueva.grabar(f);

        return f;
    }

    @GetMapping("/{id}")
    public Factura obtenerFactura(@PathVariable Long id) {
        return servNueva.generarFact(id);
    }
}
