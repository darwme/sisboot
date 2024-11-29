package sisboot.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sisboot.ms.usuario.model.Paciente;
import sisboot.ms.usuario.model.Rol;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByDocIdentidad(String docIdentidad);
    Optional<Paciente> findByUsuarioIdUsuario(Long idUsuario);
}
