package sisboot.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sisboot.ms.usuario.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
