package es.decroly.biblioteca.service;

import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.exception.BadRequestException;
import es.decroly.biblioteca.exception.ConflictException;
import es.decroly.biblioteca.exception.NotFoundException;
import es.decroly.biblioteca.model.Libro;

import java.util.List;

/**
 * Define las operaciones disponibles para gestionar libros de la biblioteca.
 */
public interface LibroService {
    /**
     * Busca un libro por su identificador y devuelve null si no existe.
     *
     * @param id identificador del libro
     * @return libro encontrado o null si no existe
     */
    Libro listarLibro(Long id);

    /**
     * Busca un libro por su identificador.
     *
     * @param id identificador del libro
     * @return libro encontrado
     * @throws NotFoundException si no existe un libro con ese identificador
     */
    Libro buscarPorId(Long id);

    /**
     * Devuelve los libros que tienen al menos un ejemplar disponible.
     *
     * @return lista de libros disponibles
     */
    List<Libro> listarLibrosDisponibles();

    /**
     * Busca libros pertenecientes a una categoría.
     *
     * @param id identificador de la categoría
     * @return lista de libros de la categoría
     */
    List<Libro> obtenerLibrosDeCategoria(Long id);

    /**
     * Filtra libros por autor sin distinguir entre mayúsculas y minúsculas.
     * El texto vacío o null permite listar todos los libros.
     *
     * @param autor texto de autor usado como filtro
     * @return lista de libros coincidentes
     */
    List<Libro> listarLibrosDeAutor(String autor);

    /**
     * Crea un libro a partir de los datos recibidos.
     *
     * @param libro datos del libro que se va a crear
     * @return libro creado
     * @throws BadRequestException si los datos del libro no son válidos
     * @throws ConflictException si ya existe un libro con ese ISBN
     */
    Libro crear(LibroPeticionDto libro);

    /**
     * Actualiza un libro existente.
     *
     * @param id identificador del libro que se va a actualizar
     * @param libro nuevos datos del libro
     * @return libro actualizado o null si no existe
     * @throws BadRequestException si los datos del libro no son válidos
     * @throws ConflictException si otro libro ya tiene ese ISBN
     */
    Libro modificar(Long id, LibroPeticionDto libro);

    /**
     * Elimina un libro por su identificador.
     *
     * @param id identificador del libro que se va a eliminar
     */
    void eliminar(Long id);
}
