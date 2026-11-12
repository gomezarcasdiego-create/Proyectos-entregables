package es.decroly.gimnasio.service;

import es.decroly.gimnasio.model.Actividad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadServiceMemoria extends ActividadServiceBase {
    public ActividadServiceMemoria() {
        super(List.of(new Actividad(1L,"Pilates","Laura Gomez", 12, 0),
                new Actividad(2L,"Spinning", "Marcos Ruiz", 20,  8),
                new Actividad(3L,"Yoga", "Laura Gomez", 15, 15),
                new Actividad(4L,"Body Bump", "Ana Torres", 25, 3)));
    }
}
