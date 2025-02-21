package com.clinica.sistemas.reina.madre.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.sistemas.reina.madre.model.CitaHistorial;


@Repository
public interface CitaHistorialRepository  extends JpaRepository<CitaHistorial, Integer> {
}

