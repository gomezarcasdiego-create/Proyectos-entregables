package es.decroly.biblioteca.controller;

import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.model.Libro;
import es.decroly.biblioteca.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Libros", description = "Operaciones para gestionar los libros de la biblioteca")
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // GET /api/libros  y  GET /api/libros?autor=xxx
    @Operation(summary = "Listar libros", description = "Lista todos los libros o filtra por autor")
    @ApiResponse(responseCode = "200", description = "Lista de libros devuelta")
    @GetMapping
    public ResponseEntity<List<Libro>> listar(@RequestParam(required = false) String autor) {
        if (autor == null || autor.isBlank()) {
            return ResponseEntity.ok(libroService.listarLibrosDeAutor(""));
        }
        return ResponseEntity.ok(libroService.listarLibrosDeAutor(autor));
    }

    // GET /api/libros/disponibles
    @Operation(summary = "Listar libros disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de libros con ejemplares disponibles")
    @GetMapping("/disponibles")
    public ResponseEntity<List<Libro>> disponibles() {
        return ResponseEntity.ok(libroService.listarLibrosDisponibles());
    }

    // GET /api/libros/{id}
    @Operation(summary = "Buscar libro por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Libro encontrado"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtener(@PathVariable Long id) {
        Libro libro = libroService.listarLibro(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }

    // POST /api/libros
    @Operation(summary = "Crear libro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Libro creado"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos"),
            @ApiResponse(responseCode = "409", description = "Ya existe un libro con ese ISBN")
    })
    @PostMapping
    public ResponseEntity<Libro> crear(@Valid @RequestBody LibroPeticionDto dto) {
        Libro creado = libroService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // PUT /api/libros/{id}
    @Operation(summary = "Modificar libro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Libro modificado"),
            @ApiResponse(responseCode = "400", description = "Datos no válidos"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado"),
            @ApiResponse(responseCode = "409", description = "Ya existe un libro con ese ISBN")
    })
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
    @Operation(summary = "Eliminar libro")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Libro eliminado"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (libroService.listarLibro(id) == null) {
            return ResponseEntity.notFound().build();
        }
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


