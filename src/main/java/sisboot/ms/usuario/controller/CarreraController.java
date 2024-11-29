package sisboot.ms.usuario.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sisboot.ms.usuario.model.Carrera;
import sisboot.ms.usuario.repository.CarreraRepository;

import java.util.List;

@RestController
@RequestMapping("/api/usuario/carrera")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class CarreraController {

    @Autowired
    private CarreraRepository carreraRepository;

    // Obtener todas las carreras
    @GetMapping("/listar")
    public ResponseEntity<List<Carrera>> listarCarreras() {
        List<Carrera> carreras = carreraRepository.findAll();
        return ResponseEntity.ok(carreras);
    }
}
