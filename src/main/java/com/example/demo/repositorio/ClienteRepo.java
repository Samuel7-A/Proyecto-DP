package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.modelo.Cliente;

public interface ClienteRepo extends JpaRepository<Cliente, Long> {
}
