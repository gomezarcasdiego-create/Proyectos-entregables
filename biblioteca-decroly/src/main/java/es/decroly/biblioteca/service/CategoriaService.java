package es.decroly.biblioteca.service;

import es.decroly.biblioteca.model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listar();
    Categoria buscarCategoriaPorId(Long id);
    Categoria crearCategoria(Categoria categoria);
    Categoria actualizarCategoria(Long id, Categoria categoria);
    Categoria eliminarCategoria(Long id);
}
