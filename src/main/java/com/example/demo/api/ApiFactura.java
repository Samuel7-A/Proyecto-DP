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

@RestController
@RequestMapping("/api/facturas")
public class ApiFactura {

    @Autowired
    private ServicioFactura servicioFactura;

    @Autowired
    private ServicioNuevaFactura servicioNuevaFactura;

    @GetMapping("/{id}")
    public ResponseEntity<Factura> generarFact(@PathVariable Long id) {
        Factura factura = servicioFactura.obtenerPorId(id);
        if (factura != null) {
            return ResponseEntity.ok(factura);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Factura>> listarFacturas() {
        List<Factura> facturas = servicioFactura.listarTodas();
        return ResponseEntity.ok(facturas);
    }

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody FacturaRequest request) {
        Factura factura = servicioNuevaFactura.nueva(
            request.getDetalles(),
            request.getCliente(),
            request.getEmpleado()
        );
        Factura guardada = servicioNuevaFactura.grabar(factura);
        return ResponseEntity.ok(guardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (servicioFactura.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint para obtener solo el total de una factura
    @GetMapping("/{id}/total")
    public ResponseEntity<TotalResponse> obtenerTotal(@PathVariable Long id) {
        Factura factura = servicioFactura.obtenerPorId(id);
        if (factura != null) {
            return ResponseEntity.ok(new TotalResponse(factura.getIdFactura(), factura.getTotal()));
        }
        return ResponseEntity.notFound().build();
    }

    // Clase para respuesta del total
    public static class TotalResponse {
        private Long idFactura;
        private double total;

        public TotalResponse(Long idFactura, double total) {
            this.idFactura = idFactura;
            this.total = total;
        }

        public Long getIdFactura() { return idFactura; }
        public double getTotal() { return total; }
    }

    // Clase interna para recibir los datos de la factura
    public static class FacturaRequest {
        private List<LineaFactura> detalles;
        private Cliente cliente;
        private Empleado empleado;

        public List<LineaFactura> getDetalles() {
            return detalles;
        }

        public void setDetalles(List<LineaFactura> detalles) {
            this.detalles = detalles;
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
    }
}
