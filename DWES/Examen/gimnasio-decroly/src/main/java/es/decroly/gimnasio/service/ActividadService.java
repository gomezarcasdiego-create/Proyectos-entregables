package es.decroly.gimnasio.service;

import es.decroly.gimnasio.exception.BadRequestException;
import es.decroly.gimnasio.exception.ConflictException;
import es.decroly.gimnasio.exception.NotFoundException;
import es.decroly.gimnasio.model.Actividad;

import java.util.List;

public interface ActividadService {
    List<Actividad> listar();

    Actividad listarPorId(Long id);

    Actividad Crear(Actividad actividad);

    Actividad Modificar(Long id, Actividad actividad);

    Actividad Eliminar(Long id);

    Actividad Consultar(String nombre);
}
