package com.example.demo.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.Alquiler;
import com.example.demo.repositorio.AlquilerRepo;

@Service
public class ServicioAlquiler {

    @Autowired
    private AlquilerRepo repo;

    public Alquiler crear(Alquiler a) {
        return repo.save(a);
    }

    public List<Alquiler> listar() {
        return repo.findAll();
    }

    public Alquiler obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Alquiler actualizar(Long id, Alquiler a) {
        if (repo.existsById(id)) {
            a.setIdAlq(id);
            return repo.save(a);
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
