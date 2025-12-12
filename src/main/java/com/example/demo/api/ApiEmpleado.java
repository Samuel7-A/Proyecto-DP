package com.example.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.modelo.Empleado;
import com.example.demo.servicio.ServicioEmpleado;
import com.example.demo.repositorio.EmpleadoRepo;

@RestController
@RequestMapping("/empleado")
public class ApiEmpleado {

    @Autowired
    EmpleadoRepo repo;

    @PostMapping("/crear")
    public Empleado crear(@RequestBody Empleado e) {
        return repo.save(e);
    }

    @GetMapping("/listar")
    public List<Empleado> listar() {
        return repo.findAll();
    }
}
