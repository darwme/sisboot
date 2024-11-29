package sisboot.ms.usuario.service;

import sisboot.ms.usuario.model.Especialista;

public interface EspecialistaService {
    Especialista crearEspecialista(Especialista especialista, String email, String contrasenia);
    Especialista obtenerEspecialistaPorId(Long idEspecialista);
    Especialista actualizarEspecialista(Long idEspecialista, Especialista especialista);
    void eliminarEspecialista(Long idEspecialista);
}