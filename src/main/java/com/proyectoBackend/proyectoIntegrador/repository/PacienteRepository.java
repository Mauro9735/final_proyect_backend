package com.proyectoBackend.proyectoIntegrador.repository;

import com.proyectoBackend.proyectoIntegrador.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findByEmail(String email);
}
