package sisboot.ms.usuario.authentication.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sisboot.ms.usuario.authentication.dto.LoginRequestDTO;
import sisboot.ms.usuario.authentication.dto.LoginResponseDTO;
import sisboot.ms.usuario.model.Usuario;
import sisboot.ms.usuario.repository.AdministradorRepository;
import sisboot.ms.usuario.repository.EspecialistaRepository;
import sisboot.ms.usuario.repository.PacienteRepository;
import sisboot.ms.usuario.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private EspecialistaRepository especialistaRepository;
    @Autowired
    private AdministradorRepository administradorRepository;

    public LoginResponseDTO login(LoginRequestDTO request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndContraseniaAndRolIdRol(
                request.getEmail(),
                request.getContrasenia(),
                request.getIdRol().longValue()
        );

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();
        LoginResponseDTO.LoginResponseDTOBuilder response = LoginResponseDTO.builder()
                .message("Usuario autenticado correctamente")
                .status(200)
                .idRol(usuario.getRol().getIdRol())
                .idUsuario(usuario.getIdUsuario());

        switch (request.getIdRol().intValue()) {
            case 3: // Paciente
                return pacienteRepository.findByUsuarioIdUsuario(usuario.getIdUsuario())
                        .map(paciente -> response.idPaciente(paciente.getIdPaciente()).build())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
            case 2: // Especialista
                return especialistaRepository.findByUsuarioIdUsuario(usuario.getIdUsuario())
                        .map(especialista -> response.idEspecialista(especialista.getIdEspecialista()).build())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialista no encontrado"));
            case 1: // Administrador
                return administradorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario())
                        .map(admin -> response.idAdministrador(admin.getIdAdministrador()).build())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador no encontrado"));
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no válido");
        }
    }
}