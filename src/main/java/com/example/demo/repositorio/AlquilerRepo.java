package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Alquiler;

public interface AlquilerRepo extends JpaRepository<Alquiler, Long> {
}
