package com.example.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.modelo.Alquiler;
import com.example.demo.servicio.ServicioAlquiler;

@RestController
@RequestMapping("/api/alquileres")
public class ApiAlquiler {

    @Autowired
    private ServicioAlquiler servicioAlquiler;

    @GetMapping
    public ResponseEntity<List<Alquiler>> listarAlq() {
        List<Alquiler> alquileres = servicioAlquiler.listar();
        return ResponseEntity.ok(alquileres);
    }

    @PostMapping
    public ResponseEntity<Alquiler> registrarAlq(@RequestBody Alquiler alquiler) {
        Alquiler nuevo = servicioAlquiler.crear(alquiler);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alquiler> buscarAlq(@PathVariable Long id) {
        Alquiler alquiler = servicioAlquiler.obtenerPorId(id);
        if (alquiler != null) {
            return ResponseEntity.ok(alquiler);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alquiler> actualizar(@PathVariable Long id, @RequestBody Alquiler alquiler) {
        Alquiler actualizado = servicioAlquiler.actualizar(id, alquiler);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> quitarAlq(@PathVariable Long id) {
        if (servicioAlquiler.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
