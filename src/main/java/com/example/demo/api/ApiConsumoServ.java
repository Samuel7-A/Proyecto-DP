package com.example.demo.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.modelo.ConsumoServ;
import com.example.demo.servicio.ServicioConsumoServ;
import com.example.demo.repositorio.ConsumoRepo;

@RestController
@RequestMapping("/consserv")
public class ApiConsumoServ {

    @Autowired
    ConsumoRepo repo;

    @PostMapping("/crear")
    public ConsumoServ crear(@RequestBody ConsumoServ c) {
        return repo.save(c);
    }

    @GetMapping("/listar")
    public List<ConsumoServ> listar() {
        return repo.findAll();
    }
}
