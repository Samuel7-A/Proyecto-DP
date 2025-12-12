package com.example.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.modelo.Cliente;
import com.example.demo.servicio.ServicioCliente;
import com.example.demo.repositorio.ClienteRepo;

@RestController
@RequestMapping("/cliente")
public class ApiCliente {

    @Autowired
    ClienteRepo repo;

    @PostMapping("/crear")
    public Cliente crear(@RequestBody Cliente c) {
        return repo.save(c);
    }

    @GetMapping("/listar")
    public List<Cliente> listar() {
        return repo.findAll();
    }
}
