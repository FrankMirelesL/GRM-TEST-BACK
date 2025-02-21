package com.clinica.sistemas.reina.madre.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.sistemas.reina.madre.model.Medico;
import com.clinica.sistemas.reina.madre.model.Paciente;
import com.clinica.sistemas.reina.madre.model.TipoCita;
import com.clinica.sistemas.reina.madre.repository.MedicoRepository;
import com.clinica.sistemas.reina.madre.repository.PacienteRepository;
import com.clinica.sistemas.reina.madre.repository.TipoCitaRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TipoCitaRepository tipoCitaRepository;

    // Crear Medico
    @PostMapping("/medico")
    public ResponseEntity<Medico> crearMedico(@RequestBody Medico medico) {
        Medico nuevoMedico = medicoRepository.save(medico);
        return ResponseEntity.ok(nuevoMedico);
    }

    // Crear Paciente
    @PostMapping("/paciente")
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente) {
        Paciente nuevoPaciente = pacienteRepository.save(paciente);
        return ResponseEntity.ok(nuevoPaciente);
    }

    // Crear TipoCita
    @PostMapping("/tipocita")
    public ResponseEntity<TipoCita> crearTipoCita(@RequestBody TipoCita tipoCita) {
        TipoCita nuevoTipoCita = tipoCitaRepository.save(tipoCita);
        return ResponseEntity.ok(nuevoTipoCita);
    }
}

