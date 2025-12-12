package com.example.demo.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.Cliente;
import com.example.demo.repositorio.ClienteRepo;

@Service
public class ServicioCliente {

    @Autowired
    private ClienteRepo repo;

    public Cliente crear(Cliente c) {
        return repo.save(c);
    }

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Cliente actualizar(Long id, Cliente c) {
        if (repo.existsById(id)) {
            c.setIdCliente(id);
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
