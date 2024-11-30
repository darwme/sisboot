package sisboot.ms.usuario.authentication.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sisboot.ms.usuario.authentication.dto.LoginRequestDTO;
import sisboot.ms.usuario.authentication.dto.LoginResponseDTO;
import sisboot.ms.usuario.model.Administrador;
import sisboot.ms.usuario.model.Especialista;
import sisboot.ms.usuario.model.Paciente;
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
                    Paciente paciente = pacienteRepository.findByUsuarioIdUsuario(usuario.getIdUsuario())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
                    response.idPaciente(paciente.getIdPaciente())
                            .perfil(paciente); // Guardar el perfil completo
                    break;
                case 2: // Especialista
                    Especialista especialista = especialistaRepository.findByUsuarioIdUsuario(usuario.getIdUsuario())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialista no encontrado"));
                    response.idEspecialista(especialista.getIdEspecialista())
                            .perfil(especialista); // Guardar el perfil completo
                    break;
                case 1: // Administrador
                    Administrador admin = administradorRepository.findByUsuarioIdUsuario(usuario.getIdUsuario())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador no encontrado"));
                    response.idAdministrador(admin.getIdAdministrador())
                            .perfil(admin); // Guardar el perfil completo
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no válido");
            }

            return response.build();
        }
    }