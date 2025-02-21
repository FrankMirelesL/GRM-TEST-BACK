package com.clinica.sistemas.reina.madre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.sistemas.reina.madre.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}

