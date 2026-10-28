package es.decroly.biblioteca.service;

import es.decroly.biblioteca.model.Categoria;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public abstract class CategoriaSeviceBase implements CategoriaService {

    private List<Categoria> categorias;
    private final AtomicLong secuencia;

    public CategoriaSeviceBase(List<Categoria> Categorias) {
        this.categorias = new ArrayList(categorias);
        long maxId = 0;
        for (Categoria p : categorias) {
            if (p.getId() > maxId) {
                maxId = p.getId();
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
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada" + id));
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        Long id = categoria.getId();
        Categoria nueva = new Categoria(id, categoria.getNombre(), categoria.getDescripcion());
        categorias.add(categoria);
        return nueva;
    }

    @Override
    public Categoria eliminarCategoria(Long id) {
        boolean existe = categorias.stream().anyMatch(c -> c.getId().equals(id));
        if (!existe) {
            throw new RuntimeException("Categoria no encontrada" + id);
        }
        return null;
    }

    @Override
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaActualizada = buscarCategoriaPorId(id);
        categoriaActualizada.setNombre(categoria.getNombre());
        categoriaActualizada.setDescripcion(categoria.getDescripcion());
        return categoriaActualizada;
    }
}
