package sisboot.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sisboot.ms.usuario.model.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    // Busca un rol por su nombre
    Optional<Rol> findByNombre(String nombre);
}
