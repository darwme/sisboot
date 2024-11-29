package sisboot.ms.usuario.dto;

import lombok.Data;
import sisboot.ms.usuario.model.Especialista;

@Data
public class EspecialistaRegistroDTO {
    private Especialista especialista; // Datos del especialista
    private String email;              // Correo electrónico del usuario
    private String contrasenia;        // Contraseña del usuario
}