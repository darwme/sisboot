package sisboot.ms.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sisboot.ms.usuario.model.Ubigeo;

import java.util.List;

public interface UbigeoRepository extends JpaRepository <Ubigeo, Long> {
    // Método para obtener todos los departamentos
    @Query("SELECT DISTINCT u.departamento FROM Ubigeo u")
    List<String> findDistinctDepartamentos();

    // Método para obtener provincias por departamento
    @Query("SELECT DISTINCT u.provincia FROM Ubigeo u WHERE u.departamento = :departamento")
    List<String> findProvinciasByDepartamento(@Param("departamento") String departamento);

    // Método para obtener distritos por provincia, retornando objetos Ubigeo completos
    @Query("SELECT u FROM Ubigeo u WHERE u.provincia = :provincia")
    List<Ubigeo> findDistritosByProvincia(@Param("provincia") String provincia);
}
