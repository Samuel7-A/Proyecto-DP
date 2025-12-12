package com.example.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.modelo.ConsumoServ;
import com.example.demo.servicio.ServicioConsumoServ;

@RestController
@RequestMapping("/api/consumos")
public class ApiConsumoServ {

    @Autowired
    private ServicioConsumoServ servicioConsumoServ;

    @GetMapping
    public ResponseEntity<List<ConsumoServ>> listarServicios() {
        List<ConsumoServ> consumos = servicioConsumoServ.listar();
        return ResponseEntity.ok(consumos);
    }

    @PostMapping
    public ResponseEntity<ConsumoServ> registrarConsumo(@RequestBody ConsumoServ consumoServ) {
        ConsumoServ nuevo = servicioConsumoServ.crear(consumoServ);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumoServ> buscarConsumo(@PathVariable Long id) {
        ConsumoServ consumo = servicioConsumoServ.obtenerPorId(id);
        if (consumo != null) {
            return ResponseEntity.ok(consumo);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoServ> actualizar(@PathVariable Long id, @RequestBody ConsumoServ consumoServ) {
        ConsumoServ actualizado = servicioConsumoServ.actualizar(id, consumoServ);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (servicioConsumoServ.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
