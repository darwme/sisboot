package sisboot.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sisboot.ms.usuario.model.Condicion;

public interface CondicionRepository extends JpaRepository<Condicion, Long> {
}
