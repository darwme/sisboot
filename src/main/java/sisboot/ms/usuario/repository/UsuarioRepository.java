package sisboot.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sisboot.ms.usuario.model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Busca un usuario por su email
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByEmailAndContraseniaAndRolIdRol(String email, String contrasenia, Long idRol);


}