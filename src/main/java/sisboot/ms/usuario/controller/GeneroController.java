package sisboot.ms.usuario.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sisboot.ms.usuario.model.Genero;
import sisboot.ms.usuario.repository.GeneroRepository;

import java.util.List;

@RestController
@RequestMapping("/api/usuario/genero")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    // Obtener todos los géneros
    @GetMapping("/listar")
    public ResponseEntity<List<Genero>> listarGeneros() {
        List<Genero> generos = generoRepository.findAll();
        return ResponseEntity.ok(generos);
    }
}