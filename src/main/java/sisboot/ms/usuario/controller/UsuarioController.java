package sisboot.ms.usuario.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sisboot.ms.usuario.model.Usuario;
import sisboot.ms.usuario.repository.UsuarioRepository;
import sisboot.ms.usuario.service.UsuarioService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/usuario/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public Iterable<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
