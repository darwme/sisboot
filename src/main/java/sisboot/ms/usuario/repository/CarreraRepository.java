package sisboot.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sisboot.ms.usuario.model.Carrera;

public interface CarreraRepository extends JpaRepository <Carrera, Long> {
}
