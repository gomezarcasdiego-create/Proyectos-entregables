package es.decroly.biblioteca.controller;

import es.decroly.biblioteca.dto.CategoriaPeticionDto;
import es.decroly.biblioteca.model.Categoria;
import es.decroly.biblioteca.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtener(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@Valid @RequestBody Categoria categoria) {
        Categoria creada = categoriaService.crearCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> modificar(@PathVariable Long id,
                                               @Valid @RequestBody Categoria categoria) {
        Categoria actualizada = categoriaService.actualizarCategoria(id, categoria);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoriaService.buscarCategoriaPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
