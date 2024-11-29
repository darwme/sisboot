package sisboot.ms.usuario.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sisboot.ms.usuario.model.Rol;
import sisboot.ms.usuario.repository.RolRepository;
import sisboot.ms.usuario.service.RolService;


@RestController
@Slf4j
@RequestMapping("/api/usuario/rol")
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private RolService rolService;



    @GetMapping("/get")
    public Iterable<Rol> listarTiposUsuario(){
        return rolRepository.findAll();
    }

    @PostMapping("/insert")
    public Rol guardarRol(@RequestBody Rol rol){
        return rolRepository.save(rol);
    }

}
