package es.decroly.biblioteca.service;

import es.decroly.biblioteca.exception.BadRequestException;
import es.decroly.biblioteca.exception.ConflictException;
import es.decroly.biblioteca.model.Categoria;
import lombok.extern.slf4j.Slf4j;
import es.decroly.biblioteca.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public abstract class CategoriaSeviceBase implements CategoriaService {

    private List<Categoria> categorias;
    private final AtomicLong secuencia;

    public CategoriaSeviceBase(List<Categoria> categorias) {
        this.categorias = new ArrayList<>(categorias);

        long maxId = 0;
        for (Categoria categoria : this.categorias) {
            if (categoria.getId() > maxId) {
                maxId = categoria.getId();
            }
        }

        this.secuencia = new AtomicLong(maxId);
    }

    @Override
    public List<Categoria> listar() {
        return categorias;
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id){
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + id));
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        validar(categoria, null);
        Long id = categoria.getId();
        Categoria nueva = new Categoria(id, categoria.getNombre(), categoria.getDescripcion());
        categorias.add(categoria);
        return nueva;
    }

    @Override
    public Categoria eliminarCategoria(Long id) {
        Categoria categoria = buscarCategoriaPorId(id);
        categorias.remove(categoria);
        return categoria;
    }

    @Override
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaActualizada = buscarCategoriaPorId(id);
        validar(categoria, id);
        categoriaActualizada.setNombre(categoria.getNombre());
        categoriaActualizada.setDescripcion(categoria.getDescripcion());
        return categoriaActualizada;
    }

    private void validar(Categoria categoria, Long idExcluido) {
        if (categoria == null
                || categoria.getNombre() == null
                || categoria.getNombre().isBlank()) {
            throw new BadRequestException("El nombre de la categoría es obligatorio");
        }

        if (categoria.getNombre().trim().length() > 50) {
            throw new BadRequestException("El nombre no puede superar 50 caracteres");
        }

        String nombre = categoria.getNombre().trim();
        boolean nombreDuplicado = categorias.stream()
                .anyMatch(actual -> !Objects.equals(actual.getId(), idExcluido)
                        && actual.getNombre() != null
                        && actual.getNombre().trim().equalsIgnoreCase(nombre));

        if (nombreDuplicado) {
            throw new ConflictException("Ya existe una categoría con ese nombre");
        }
    }
}
