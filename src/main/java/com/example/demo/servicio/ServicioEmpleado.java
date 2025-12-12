package com.example.demo.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.Empleado;
import com.example.demo.repositorio.EmpleadoRepo;

@Service
public class ServicioEmpleado {

    @Autowired
    private EmpleadoRepo repo;

    public Empleado crear(Empleado e) {
        return repo.save(e);
    }

    public List<Empleado> listar() {
        return repo.findAll();
    }

    public Empleado obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Empleado actualizar(Long id, Empleado e) {
        if (repo.existsById(id)) {
            e.setIdEmpleado(id);
            return repo.save(e);
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
