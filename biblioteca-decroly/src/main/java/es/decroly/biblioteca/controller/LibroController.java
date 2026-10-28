package es.decroly.biblioteca.controller;

import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.model.Libro;
import es.decroly.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // GET /api/libros  y  GET /api/libros?autor=xxx
    @GetMapping
    public ResponseEntity<List<Libro>> listar(@RequestParam(required = false) String autor) {
        if (autor == null || autor.isBlank()) {
            return ResponseEntity.ok(libroService.listarLibrosDeAutor(""));
        }
        return ResponseEntity.ok(libroService.listarLibrosDeAutor(autor));
    }

    // GET /api/libros/disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<Libro>> disponibles() {
        return ResponseEntity.ok(libroService.listarLibrosDisponibles());
    }

    // GET /api/libros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtener(@PathVariable Long id) {
        Libro libro = libroService.listarLibro(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }

    // POST /api/libros
    @PostMapping
    public ResponseEntity<Libro> crear(@Valid @RequestBody LibroPeticionDto dto) {
        Libro creado = libroService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // PUT /api/libros/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Libro> modificar(@PathVariable Long id,
                                           @Valid @RequestBody LibroPeticionDto dto) {
        Libro modificado = libroService.modificar(id, dto);
        if (modificado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modificado);
    }

    // DELETE /api/libros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (libroService.listarLibro(id) == null) {
            return ResponseEntity.notFound().build();
        }
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


