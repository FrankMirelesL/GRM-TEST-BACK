package com.clinica.sistemas.reina.madre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.sistemas.reina.madre.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
}
