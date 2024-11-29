package sisboot.ms.usuario.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "administrador")
@Data
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private Long idAdministrador;

    @Column(name = "doc_identidad", nullable = false, unique = true, length = 9)
    private String docIdentidad;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(nullable = false, length = 50)
    private String nombres;

    @Column(nullable = false, length = 70)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    private java.time.LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

    @Column(name = "num_telefono", nullable = false, length = 9)
    private String numTelefono;
}
