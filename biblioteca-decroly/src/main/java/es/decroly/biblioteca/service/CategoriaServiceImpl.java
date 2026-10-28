package es.decroly.biblioteca.service;

import es.decroly.biblioteca.model.Categoria;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public abstract class CategoriaServiceImpl implements CategoriaService {
    public CategoriaServiceImpl(List<Categoria> categorias, Categoria fantasía, Categoria cienciaFicción, Categoria categoria, Categoria categoria1) {
    }

    @Override
    public List<Categoria> listar() {
        return List.of();
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id) {
        return null;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return null;
    }

    @Override
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        return null;
    }

    @Override
    public Categoria eliminarCategoria(Long id) {
        return null;
    }
}
