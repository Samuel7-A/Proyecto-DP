package com.example.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.modelo.Empleado;
import com.example.demo.servicio.ServicioEmpleado;

@RestController
@RequestMapping("/api/empleados")
public class ApiEmpleado {

    @Autowired
    private ServicioEmpleado servicioEmpleado;

    @PostMapping
    public ResponseEntity<Empleado> registrarEmp(@RequestBody Empleado empleado) {
        Empleado nuevo = servicioEmpleado.crear(empleado);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarEmp(@PathVariable Long id) {
        Empleado empleado = servicioEmpleado.obtenerPorId(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> listarEmpleados() {
        List<Empleado> empleados = servicioEmpleado.listar();
        return ResponseEntity.ok(empleados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        Empleado actualizado = servicioEmpleado.actualizar(id, empleado);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmp(@PathVariable Long id) {
        if (servicioEmpleado.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
