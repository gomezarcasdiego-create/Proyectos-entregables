package es.decroly.gimnasio.service;

import es.decroly.gimnasio.dto.ActividadPeticionDto;
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
public abstract class ActividadServiceImpl implements ActividadService {
    private List<Actividad> actividades;
    private final AtomicLong secuencia;

    public ActividadServiceImpl(List<Actividad> actividades) {
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
    public Actividad Crear(ActividadPeticionDto dto) {
       validar(dto, null);
       Actividad actividad = new Actividad();
        actividad.setId(secuencia.incrementAndGet());
       actividad.setNombre(dto.getNombre());
       actividad.setMonitor(dto.getMonitor());
       actividad.setPlazas(dto.getPlazas());

       actividades.add(actividad);
       return actividad;
    }

    @Override
    public Actividad Modificar(ActividadPeticionDto dto, Long id) {
        Actividad actividad = listarPorId(id);
        if (actividad == null) {
            return null;
        }
        validar(dto, id);
        actividad.setNombre(dto.getNombre());
        actividad.setMonitor(dto.getMonitor());
        actividad.setPlazas(dto.getPlazas());

        return actividad;
    }

    @Override
    public void Eliminar(Long id) {
        actividades.removeIf(l -> l.getId().equals(id));
    }

    @Override
    public Actividad Consultar(String nombre) {
        return null;
    }

    private void validar(ActividadPeticionDto actividad, Long idExcluido) {
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
