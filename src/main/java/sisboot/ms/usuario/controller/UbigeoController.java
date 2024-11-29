package sisboot.ms.usuario.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sisboot.ms.usuario.model.Ubigeo;
import sisboot.ms.usuario.repository.UbigeoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/usuario/ubigeo")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UbigeoController {
    @Autowired
    private UbigeoRepository ubigeoRepository;

    // Obtener todos los departamentos
    @GetMapping("/departamentos")
    public ResponseEntity<List<String>> obtenerDepartamentos() {
        List<String> departamentos = ubigeoRepository.findDistinctDepartamentos();
        return ResponseEntity.ok(departamentos);
    }

    // Obtener provincias por departamento
    @GetMapping("/provincias/{departamento}")
    public ResponseEntity<List<String>> obtenerProvincias(@PathVariable String departamento) {
        List<String> provincias = ubigeoRepository.findProvinciasByDepartamento(departamento);
        return ResponseEntity.ok(provincias);
    }

    // Obtener distritos por provincia
    @GetMapping("/distritos/{provincia}")
    public List<Ubigeo> getDistritosByProvincia(@PathVariable String provincia) {
        return ubigeoRepository.findDistritosByProvincia(provincia);
    }


}

