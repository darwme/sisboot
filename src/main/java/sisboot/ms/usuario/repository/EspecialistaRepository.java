package sisboot.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sisboot.ms.usuario.model.Especialista;

import java.util.Optional;

public interface EspecialistaRepository extends JpaRepository<Especialista, Long> {
    Optional<Especialista> findByUsuarioIdUsuario(Long idUsuario);
}
