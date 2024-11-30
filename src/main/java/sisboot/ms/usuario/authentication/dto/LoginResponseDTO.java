package sisboot.ms.usuario.authentication.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponseDTO {
    private String message;
    private int status;
    private Long idRol;
    private Long idUsuario;
    private Long idPaciente; // Opcional según el tipo
    private Long idEspecialista; // Opcional según el tipo
    private Long idAdministrador; // Opcional según el tipo
    private Object perfil;
}
