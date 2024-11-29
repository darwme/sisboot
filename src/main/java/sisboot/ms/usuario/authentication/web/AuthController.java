package sisboot.ms.usuario.authentication.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sisboot.ms.usuario.authentication.domain.AuthService;
import sisboot.ms.usuario.authentication.dto.LoginRequestDTO;
import sisboot.ms.usuario.authentication.dto.LoginResponseDTO;

@RestController
@RequestMapping("/api/usuario/auth")
@CrossOrigin(origins = "http://localhost:4200")

@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        System.out.println("Request recibido: " + request);
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}