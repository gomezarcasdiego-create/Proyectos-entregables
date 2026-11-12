package es.decroly.gimnasio.service;

import es.decroly.gimnasio.dto.ActividadPeticionDto;
import es.decroly.gimnasio.model.Actividad;

import java.util.List;

public interface ActividadService {
    List<Actividad> listar();

    Actividad listarPorId(Long id);

    Actividad Crear(Actividad actividad);

    Actividad Modificar(Long id, Actividad actividad);

    Actividad Crear(ActividadPeticionDto dto);

    Actividad Modificar(ActividadPeticionDto dto, Long id);

    void Eliminar(Long id);

    Actividad Consultar(String nombre);
}
