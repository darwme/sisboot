package sisboot.ms.usuario.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "paciente")
@Data
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

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

    @ManyToOne
    @JoinColumn(name = "id_ubigeo", nullable = false)
    private Ubigeo ubigeo;

    @ManyToOne
    @JoinColumn(name = "id_condicion", nullable = false)
    private Condicion condicion;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;
}
