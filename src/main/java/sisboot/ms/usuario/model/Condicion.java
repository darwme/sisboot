package sisboot.ms.usuario.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "condicion")
@Data
public class Condicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_condicion")
    private Long idCondicion;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

}
