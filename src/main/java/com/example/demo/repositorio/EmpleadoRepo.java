package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Empleado;

public interface EmpleadoRepo extends JpaRepository<Empleado, Long> {
}
