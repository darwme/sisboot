package sisboot.ms.usuario.authentication.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private Long idRol;
    private String email;
    private String contrasenia;
}
