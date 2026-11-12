package es.decroly.gimnasio.service;

import es.decroly.gimnasio.exception.BadRequestException;
import es.decroly.gimnasio.exception.ConflictException;
import es.decroly.gimnasio.exception.NotFoundException;
import es.decroly.gimnasio.model.Actividad;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;


@Slf4j
public class ActividadServiceBase implements ActividadService {
    private List<Actividad> actividades;
    private final AtomicLong secuencia;

    public ActividadServiceBase(List<Actividad> actividades) {
        this.actividades = new ArrayList<>(actividades);

        long maxId = 0;
        for (Actividad actividad : this.actividades) {
            if (maxId < actividad.getId()) {
                maxId = actividad.getId();
            }
        }
        this.secuencia = new AtomicLong(maxId);

    }
    @Override
    public List<Actividad> listar() {
        return List.of();
    }

    @Override
    public Actividad listarPorId(Long id) {
        return actividades.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Categoria no encnontrada con ese id", +id));
    }

    @Override
    public Actividad Crear(Actividad actividad) {
        Long id = actividad.getId();
        Actividad nueva = new Actividad(id, actividad.getNombre(), actividad.getMonitor(), actividad.getPlazas(), actividad.getPlazasLibres());
        actividades.add(nueva);
        return nueva;
    }

    @Override
    public Actividad Modificar(Long id, Actividad actividad) {
        Actividad actividadModificada = listarPorId(id);
        actividad.setNombre(actividad.getNombre());
        actividad.setMonitor(actividad.getMonitor());
        return actividadModificada;
    }

    @Override
    public Actividad Eliminar(Long id) {
        Actividad actividad = listarPorId(id);
        actividades.remove(actividad);
        return actividad;
    }

    @Override
    public Actividad Consultar(String nombre) {
        return null;
    }

    private void validar(Actividad actividad, Long idExcluido) {
        if (actividad == null
                || actividad.getNombre() == null
                || actividad.getNombre().isBlank()) {
            throw new BadRequestException("El nombre de la categoría es obligatorio");
        }

        if (actividad.getNombre().trim().length() > 50) {
            throw new BadRequestException("El nombre no puede superar 50 caracteres");
        }
        if (actividad.getMonitor() == null
            || actividad.getMonitor() == null
            || actividad.getMonitor().isBlank()) {
            throw new BadRequestException("El monitor es obligatorio");
        }

        String nombre = actividad.getNombre().trim();
        boolean nombreDuplicado = actividades.stream()
                .anyMatch(actual -> !Objects.equals(actual.getId(), idExcluido)
                        && actual.getNombre() != null
                        && actual.getNombre().trim().equalsIgnoreCase(nombre));

        if (nombreDuplicado) {
            throw new ConflictException("Ya existe una categoría con ese nombre");
        }
    }

}
