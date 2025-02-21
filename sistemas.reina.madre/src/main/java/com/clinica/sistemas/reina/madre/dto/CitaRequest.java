package com.clinica.sistemas.reina.madre.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitaRequest {

    private String numeroCita;
    private LocalDate fecha;
    private LocalTime hora;
    private Integer pacienteId;
    private Integer medicoId;
    private Integer tipoCitaId;

}

