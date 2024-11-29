package sisboot.ms.usuario.dto;

import lombok.Data;
import sisboot.ms.usuario.model.Paciente;

import java.time.LocalDate;

@Data
public class PacienteRegistroDTO {
    private String nombres;
    private String apellidos;
    private LocalDate fecNacimiento;
    private String docIdentidad;
    private Long idGenero;
    private String numTelefono;
    private Long idCondicion;
    private Long idCarrera; // Puede ser null si no es alumno
    private Long idUbigeo;
    private String email;
    private String contrasenia;
}