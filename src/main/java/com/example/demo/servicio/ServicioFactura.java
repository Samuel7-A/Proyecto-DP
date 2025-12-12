package com.example.demo.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.Factura;
import com.example.demo.repositorio.FacturaRepo;

@Service
public class ServicioFactura {

    @Autowired
    private FacturaRepo repo;

    public Factura generarFact(Long idFact) {
        return repo.findById(idFact).orElse(null);
    }

    public List<Factura> listarTodas() {
        return repo.findAll();
    }

    public Factura obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public boolean eliminar(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
