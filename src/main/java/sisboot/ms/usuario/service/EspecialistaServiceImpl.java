package sisboot.ms.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisboot.ms.usuario.model.Especialista;
import sisboot.ms.usuario.model.Rol;
import sisboot.ms.usuario.model.Usuario;
import sisboot.ms.usuario.repository.EspecialistaRepository;
import sisboot.ms.usuario.repository.RolRepository;
import sisboot.ms.usuario.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class EspecialistaServiceImpl implements EspecialistaService {

    @Autowired
    private EspecialistaRepository especialistaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Especialista crearEspecialista(Especialista especialista, String email, String contrasenia) {
        // Verificar si el correo ya está registrado
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);

        Usuario usuario;
        if (usuarioExistente.isPresent()) {
            usuario = usuarioExistente.get();

            // Imprime el email del usuario existente
            System.out.println("Usuario existente encontrado con email: " + usuario.getEmail());

            if (usuario.getRol().getNombre().equalsIgnoreCase("Especialista")) {
                throw new IllegalArgumentException("El correo ya está registrado como especialista.");
            }
            // Si está registrado como otro rol (Administrador o Paciente), crea un nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setContrasenia(contrasenia); // Nueva contraseña para el nuevo perfil
            nuevoUsuario.setRol(buscarRolEspecialista()); // Asigna el rol de especialista
            usuario = usuarioRepository.save(nuevoUsuario);
        } else {
            usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setContrasenia(contrasenia);
            usuario.setRol(buscarRolEspecialista());
            usuario = usuarioRepository.save(usuario);
        }

        especialista.setUsuario(usuario);
        return especialistaRepository.save(especialista);
    }

    @Override
    public Especialista obtenerEspecialistaPorId(Long idEspecialista) {
        return especialistaRepository.findById(idEspecialista)
                .orElseThrow(() -> new IllegalArgumentException("Especialista no encontrado con ID: " + idEspecialista));
    }

    @Override
    public Especialista actualizarEspecialista(Long idEspecialista, Especialista especialista) {
        Especialista especialistaExistente = obtenerEspecialistaPorId(idEspecialista);

        especialistaExistente.setNombres(especialista.getNombres());
        especialistaExistente.setApellidos(especialista.getApellidos());
        especialistaExistente.setFecNacimiento(especialista.getFecNacimiento());
        especialistaExistente.setGenero(especialista.getGenero());
        especialistaExistente.setNumTelefono(especialista.getNumTelefono());
        especialistaExistente.setEspecialidad(especialista.getEspecialidad());
        especialistaExistente.setCodigo(especialista.getCodigo());

        return especialistaRepository.save(especialistaExistente);
    }

    @Override
    public void eliminarEspecialista(Long idEspecialista) {
        Especialista especialista = obtenerEspecialistaPorId(idEspecialista);
        especialistaRepository.delete(especialista);
    }

    private Rol buscarRolEspecialista() {
        return rolRepository.findByNombre("Especialista")
                .orElseThrow(() -> new IllegalArgumentException("El rol de especialista no está configurado."));
    }
}
