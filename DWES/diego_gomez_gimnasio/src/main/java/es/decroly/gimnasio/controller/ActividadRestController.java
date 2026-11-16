package es.decroly.gimnasio.controller;

import es.decroly.gimnasio.model.Actividad;
import es.decroly.gimnasio.service.ActividadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/actividades")
public class ActividadRestController {

    private final ActividadService actividadService;

    @GetMapping
    public List<Actividad> listar() {
        return actividadService.listar();
    }





    @GetMapping("/{id}")
    public Actividad buscarPorId(@PathVariable Long id) {
        return actividadService.listarPorId(id);
    }

    @PostMapping
    public Actividad Crear(@RequestBody Actividad producto) {
        return actividadService.Crear(producto);
    }


    @PutMapping
    public Actividad Modificar(@PathVariable Long id,
                              @RequestBody Actividad datos) {
        return actividadService.Modificar(id, datos);
    }

    @DeleteMapping
    public void Eliminar(@PathVariable Long id) {
        actividadService.Eliminar(id);
    }


}
