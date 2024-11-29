package sisboot.ms.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sisboot.ms.usuario.dto.PacienteRegistroDTO;
import sisboot.ms.usuario.model.Carrera;
import sisboot.ms.usuario.model.Paciente;
import sisboot.ms.usuario.model.Rol;
import sisboot.ms.usuario.model.Usuario;
import sisboot.ms.usuario.repository.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private CondicionRepository condicionRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private UbigeoRepository ubigeoRepository;

    @Override
    @Transactional // Garantizar rollback si ocurre un error
    public Paciente crearPaciente(PacienteRegistroDTO dto) {
        // Verificar si el correo ya está registrado
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(dto.getEmail());

        if (usuarioExistente.isPresent() && usuarioExistente.get().getRol().getNombre().equalsIgnoreCase("Paciente")) {
            throw new IllegalArgumentException("El correo ya está registrado como paciente.");
        }

        // Verificar si el DNI ya está registrado
        boolean dniExistente = pacienteRepository.existsByDocIdentidad(dto.getDocIdentidad());
        if (dniExistente) {
            throw new IllegalArgumentException("El DNI ya está registrado.");
        }

        // Construir el objeto Paciente para validación completa antes de guardar
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setContrasenia(dto.getContrasenia());
        usuario.setRol(buscarRolPaciente());

        Paciente paciente = new Paciente();
        paciente.setNombres(dto.getNombres());
        paciente.setApellidos(dto.getApellidos());
        paciente.setFecNacimiento(dto.getFecNacimiento());
        paciente.setDocIdentidad(dto.getDocIdentidad());
        paciente.setNumTelefono(dto.getNumTelefono());
        paciente.setUsuario(usuario);

        // Asociar entidades relacionadas usando sus IDs
        paciente.setGenero(
                generoRepository.findById(dto.getIdGenero())
                        .orElseThrow(() -> new IllegalArgumentException("Género no encontrado"))
        );
        paciente.setCondicion(
                condicionRepository.findById(dto.getIdCondicion())
                        .orElseThrow(() -> new IllegalArgumentException("Condición no encontrada"))
        );
        if (dto.getIdCarrera() != null) {
            paciente.setCarrera(
                    carreraRepository.findById(dto.getIdCarrera())
                            .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada"))
            );
        }
        paciente.setUbigeo(
                ubigeoRepository.findById(dto.getIdUbigeo())
                        .orElseThrow(() -> new IllegalArgumentException("Ubigeo no encontrado"))
        );

        // Guardar todo dentro de la transacción
        usuario = usuarioRepository.save(usuario);
        paciente.setUsuario(usuario); // Asociar el usuario guardado al paciente
        return pacienteRepository.save(paciente);
    }


    @Override
    public Paciente obtenerPacientePorId(Long idPaciente) {
        // Implementación del metodo obtenerPacientePorId
        return pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado con ID: " + idPaciente));
    }

    @Override
    public Paciente actualizarPaciente(Long idPaciente, Paciente paciente) {
        // Implementación del metodo actualizarPaciente
        Paciente pacienteExistente = obtenerPacientePorId(idPaciente);

        pacienteExistente.setNombres(paciente.getNombres());
        pacienteExistente.setApellidos(paciente.getApellidos());
        pacienteExistente.setFecNacimiento(paciente.getFecNacimiento());
        pacienteExistente.setGenero(paciente.getGenero());
        pacienteExistente.setNumTelefono(paciente.getNumTelefono());
        pacienteExistente.setUbigeo(paciente.getUbigeo());
        pacienteExistente.setCondicion(paciente.getCondicion());
        pacienteExistente.setCarrera(paciente.getCarrera());

        return pacienteRepository.save(pacienteExistente);
    }

    @Override
    public void eliminarPaciente(Long idPaciente) {
        // Implementación del metodo eliminarPaciente
        Paciente paciente = obtenerPacientePorId(idPaciente);
        pacienteRepository.delete(paciente);
    }

    private Rol buscarRolPaciente() {
        return rolRepository.findByNombre("Paciente")
                .orElseThrow(() -> new IllegalArgumentException("El rol de paciente no está configurado."));
    }
}