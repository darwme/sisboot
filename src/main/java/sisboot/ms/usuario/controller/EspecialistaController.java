package sisboot.ms.usuario.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sisboot.ms.usuario.dto.EspecialistaRegistroDTO;
import sisboot.ms.usuario.model.Especialista;
import sisboot.ms.usuario.repository.EspecialistaRepository;
import sisboot.ms.usuario.service.EspecialistaService;

@RestController
@RequestMapping("/api/usuario/especialista")
@CrossOrigin(origins = "http://localhost:4200") // Cambiar según tu frontend
public class EspecialistaController {

    @Autowired
    private EspecialistaService especialistaService;

    @Autowired
    private EspecialistaRepository especialistaRepository;

    // Endpoint para registrar un especialista
    @PostMapping("/create")
    public ResponseEntity<Especialista> registrarEspecialista(@RequestBody EspecialistaRegistroDTO especialistaRegistroDTO) {
        try {
            Especialista nuevoEspecialista = especialistaService.crearEspecialista(
                    especialistaRegistroDTO.getEspecialista(),
                    especialistaRegistroDTO.getEmail(),
                    especialistaRegistroDTO.getContrasenia()
            );
            return ResponseEntity.ok(nuevoEspecialista);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Manejo de errores
        }
    }

    @GetMapping("/listar")
    public Iterable<Especialista> listarEspecialistas(){
        return especialistaRepository.findAll();
    }

    // Endpoint para obtener un especialista por ID
    @GetMapping("/get/{idEspecialista}")
    public ResponseEntity<Especialista> obtenerEspecialistaPorId(@PathVariable Long idEspecialista) {
        try {
            Especialista especialista = especialistaService.obtenerEspecialistaPorId(idEspecialista);
            return ResponseEntity.ok(especialista);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint para actualizar un especialista
    @PutMapping("/update/{idEspecialista}")
    public ResponseEntity<Especialista> actualizarEspecialista(
            @PathVariable Long idEspecialista,
            @RequestBody Especialista especialista) {
        try {
            Especialista especialistaActualizado = especialistaService.actualizarEspecialista(idEspecialista, especialista);
            return ResponseEntity.ok(especialistaActualizado);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint para eliminar un especialista por ID
    @DeleteMapping("/delete/{idEspecialista}")
    public ResponseEntity<Void> eliminarEspecialista(@PathVariable Long idEspecialista) {
        try {
            especialistaService.eliminarEspecialista(idEspecialista);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
