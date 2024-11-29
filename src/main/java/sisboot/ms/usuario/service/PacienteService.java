package sisboot.ms.usuario.service;

import sisboot.ms.usuario.dto.PacienteRegistroDTO;
import sisboot.ms.usuario.model.Paciente;

import java.util.Map;

public interface PacienteService {
    Paciente crearPaciente(PacienteRegistroDTO dto);

    Paciente obtenerPacientePorId(Long idPaciente);

    Paciente actualizarPaciente(Long idPaciente, Paciente paciente);

    void eliminarPaciente(Long idPaciente);
}