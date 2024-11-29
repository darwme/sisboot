package sisboot.ms.usuario.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sisboot.ms.usuario.model.Condicion;
import sisboot.ms.usuario.repository.CondicionRepository;

import java.util.List;

@RestController
@RequestMapping("/api/usuario/condicion")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class CondicionController {

    @Autowired
    private CondicionRepository condicionRepository;

    // Obtener todas las condiciones
    @GetMapping("/listar")
    public ResponseEntity<List<Condicion>> listarCondiciones() {
        List<Condicion> condiciones = condicionRepository.findAll();
        return ResponseEntity.ok(condiciones);
    }
}
