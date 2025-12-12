package com.example.demo.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.ConsumoServ;
import com.example.demo.repositorio.ConsumoRepo;

@Service
public class ServicioConsumoServ {

    @Autowired
    private ConsumoRepo repo;

    public ConsumoServ crear(ConsumoServ c) {
        return repo.save(c);
    }

    public List<ConsumoServ> listar() {
        return repo.findAll();
    }

    public ConsumoServ obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public ConsumoServ actualizar(Long id, ConsumoServ c) {
        if (repo.existsById(id)) {
            c.setIdConsumoServ(id);
            return repo.save(c);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
