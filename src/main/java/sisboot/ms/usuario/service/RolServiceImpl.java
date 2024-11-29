package sisboot.ms.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sisboot.ms.usuario.model.Rol;
import sisboot.ms.usuario.repository.RolRepository;


@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }
}
