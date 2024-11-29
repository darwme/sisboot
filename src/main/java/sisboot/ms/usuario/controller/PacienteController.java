package sisboot.ms.usuario.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sisboot.ms.usuario.dto.PacienteRegistroDTO;
import sisboot.ms.usuario.model.Paciente;
import sisboot.ms.usuario.repository.PacienteRepository;
import sisboot.ms.usuario.service.PacienteService;

import java.util.Collections;
import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/api/usuario/paciente")
@CrossOrigin(origins = "http://localhost:4200")

public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteRepository pacienteRepository;


    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPaciente(@RequestBody PacienteRegistroDTO dto) {
        try {
            Paciente paciente = pacienteService.crearPaciente(dto);
            return ResponseEntity.ok(paciente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public Iterable<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }
}
