package com.clinica.sistemas.reina.madre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.sistemas.reina.madre.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}

