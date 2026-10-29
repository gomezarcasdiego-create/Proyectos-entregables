package es.decroly.biblioteca.service;

import es.decroly.biblioteca.exception.BadRequestException;
import es.decroly.biblioteca.exception.NotFoundException;
import es.decroly.biblioteca.model.Categoria;

import java.util.List;

/**
 * Define las operaciones disponibles para gestionar categorías de la biblioteca.
 */
public interface CategoriaService {
    /**
     * Devuelve todas las categorías.
     *
     * @return lista de categorías
     */
    List<Categoria> listar();

    /**
     * Busca una categoría por su identificador.
     *
     * @param id identificador de la categoría
     * @return categoría encontrada
     * @throws NotFoundException si no existe una categoría con ese identificador
     */
    Categoria buscarCategoriaPorId(Long id);

    /**
     * Crea una categoría.
     *
     * @param categoria datos de la categoría que se va a crear
     * @return categoría creada
     * @throws BadRequestException si el nombre es obligatorio o supera los 50 caracteres
     */
    Categoria crearCategoria(Categoria categoria);

    /**
     * Actualiza una categoría existente.
     *
     * @param id identificador de la categoría que se va a actualizar
     * @param categoria nuevos datos de la categoría
     * @return categoría actualizada
     * @throws NotFoundException si no existe una categoría con ese identificador
     * @throws BadRequestException si los datos de la categoría no son válidos
     */
    Categoria actualizarCategoria(Long id, Categoria categoria);

    /**
     * Elimina una categoría existente.
     *
     * @param id identificador de la categoría que se va a eliminar
     * @return categoría eliminada
     * @throws NotFoundException si no existe una categoría con ese identificador
     */
    Categoria eliminarCategoria(Long id);
}
