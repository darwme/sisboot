package sisboot.ms.usuario.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "especialista")
@Data
public class Especialista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialista")
    private Long idEspecialista;

    @Column(name = "doc_identidad", nullable = false, unique = true, length = 9)
    private String docIdentidad;

    @Column(nullable = false, length = 50)
    private String nombres;

    @Column(nullable = false, length = 70)
    private String apellidos;

    @Column(name = "fec_nacimiento", nullable = false)
    private java.time.LocalDate fecNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;

    @Column(name = "num_telefono", nullable = false, length = 9)
    private String numTelefono;

    @Column(nullable = false, length = 50)
    private String especialidad;

    @Column(nullable = false, unique = true, length = 11)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
