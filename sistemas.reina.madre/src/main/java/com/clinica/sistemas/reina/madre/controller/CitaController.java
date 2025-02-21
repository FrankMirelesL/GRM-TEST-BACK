package com.clinica.sistemas.reina.madre.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.sistemas.reina.madre.dto.CitaRequest;
import com.clinica.sistemas.reina.madre.model.Cita;
import com.clinica.sistemas.reina.madre.model.CitaHistorial;
import com.clinica.sistemas.reina.madre.model.Medico;
import com.clinica.sistemas.reina.madre.model.Paciente;
import com.clinica.sistemas.reina.madre.model.TipoCita;
import com.clinica.sistemas.reina.madre.repository.CitaHistorialRepository;
import com.clinica.sistemas.reina.madre.repository.CitaRepository;
import com.clinica.sistemas.reina.madre.repository.MedicoRepository;
import com.clinica.sistemas.reina.madre.repository.PacienteRepository;
import com.clinica.sistemas.reina.madre.repository.TipoCitaRepository;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TipoCitaRepository tipoCitaRepository;

    @Autowired
    private CitaHistorialRepository citaHistorialRepository;

    // Crear nueva cita
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cita crearCita(@RequestBody CitaRequest citaRequest) {
        Optional<Paciente> paciente = pacienteRepository.findById(citaRequest.getPacienteId());
        Optional<Medico> medico = medicoRepository.findById(citaRequest.getMedicoId());
        Optional<TipoCita> tipoCita = tipoCitaRepository.findById(citaRequest.getTipoCitaId());

        if (paciente.isPresent() && medico.isPresent() && tipoCita.isPresent()) {
            Cita nuevaCita = new Cita();
            nuevaCita.setNumeroCita(citaRequest.getNumeroCita());
            nuevaCita.setFecha(citaRequest.getFecha());
            nuevaCita.setHora(citaRequest.getHora());
            nuevaCita.setPaciente(paciente.get());
            nuevaCita.setMedico(medico.get());
            nuevaCita.setTipoCita(tipoCita.get());

            return citaRepository.save(nuevaCita);
        } else {
            throw new RuntimeException("Paciente, Médico o Tipo de Cita no encontrados");
        }
    }

    // Obtener todas las citas
    @GetMapping
    public List<Cita> obtenerTodasLasCitas() {
        return citaRepository.findAll();
    }

    @GetMapping("/eliminadas")
    public List<CitaHistorial> obtenerCitasEliminadas() {
        return citaHistorialRepository.findAll();
    }
    //Obtener todas las listas para drop
    @GetMapping("/pacientes")
    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll();
    }

    @GetMapping("/medicos")
    public List<Medico> obtenerMedicos() {
        return medicoRepository.findAll();
    }

    @GetMapping("/tipoCita")
    public List<TipoCita> obtenerTiposCita() {
        return tipoCitaRepository.findAll();
    }

    // Obtener cita por ID
    @GetMapping("/{id}")
    public Cita obtenerCitaPorId(@PathVariable Integer id) {
        Optional<Cita> cita = citaRepository.findById(id);
        return cita.orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    // Eliminar cita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Integer id) {
        Optional<Cita> citaOptional = citaRepository.findById(id);
        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();
            
            registrarCambio(cita, "Eliminado");
            
            citaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable Integer id, @RequestBody Cita citaDetalles) {
        Optional<Cita> citaOptional = citaRepository.findById(id);
        if (citaOptional.isPresent()) {
            Cita cita = citaOptional.get();

            boolean huboCambio = false;
            if (!cita.getFecha().equals(citaDetalles.getFecha())) {
                cita.setFecha(citaDetalles.getFecha());
                huboCambio = true;
            }
            if (!cita.getHora().equals(citaDetalles.getHora())) {
                cita.setHora(citaDetalles.getHora());
                huboCambio = true;
            }
            if (!cita.getPaciente().getId().equals(citaDetalles.getPaciente().getId())) {
                cita.setPaciente(citaDetalles.getPaciente());
                huboCambio = true;
            }
            if (!cita.getMedico().getId().equals(citaDetalles.getMedico().getId())) {
                cita.setMedico(citaDetalles.getMedico());
                huboCambio = true;
            }
            if (!cita.getTipoCita().getId().equals(citaDetalles.getTipoCita().getId())) {
                cita.setTipoCita(citaDetalles.getTipoCita());
                huboCambio = true;
            }

            if (huboCambio) {
                registrarCambio(cita, "Editado");
            }

            Cita citaActualizada = citaRepository.save(cita);
            return ResponseEntity.ok(citaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

     private void registrarCambio(Cita cita, String descripcion) {
        CitaHistorial historial = new CitaHistorial();
        historial.setNumeroCita(cita.getNumeroCita());
        historial.setFecha(cita.getFecha());
        historial.setHora(cita.getHora());
        historial.setPacienteId(cita.getPaciente().getId());
        historial.setMedicoId(cita.getMedico().getId());
        historial.setTipoCitaId(cita.getTipoCita().getId());
        historial.setDescripcion(descripcion);
        historial.setFechaModificacion(LocalDateTime.now());

        citaHistorialRepository.save(historial);
    }
}
