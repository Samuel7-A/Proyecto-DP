package com.example.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.modelo.Alquiler;
import com.example.demo.servicio.ServicioAlquiler;
import com.example.demo.repositorio.AlquilerRepo;


@RestController
@RequestMapping("/alquiler")
public class ApiAlquiler {

    @Autowired
    AlquilerRepo repo;

    @PostMapping("/crear")
    public Alquiler crear(@RequestBody Alquiler a) {
        return repo.save(a);
    }

    @GetMapping("/listar")
    public List<Alquiler> listar() {
        return repo.findAll();
    }
}
