package com.clinica.sistemas.reina.madre.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "citas_historial")
public class CitaHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numeroCita;
    private LocalDate fecha;
    private LocalTime hora;
    private Integer pacienteId;
    private Integer medicoId;
    private Integer tipoCitaId;
    
    private String descripcion;  
    private LocalDateTime fechaModificacion; 
}
