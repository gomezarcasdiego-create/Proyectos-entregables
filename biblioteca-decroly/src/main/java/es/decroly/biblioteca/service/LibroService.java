package es.decroly.biblioteca.service;

import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.model.Libro;

import java.util.List;

public interface LibroService {


    Libro listarLibro(Long Id);
    Libro buscarPorId(Long Id);
    List<Libro> listarLibrosDisponibles();
    List<Libro> obtenerLibrosDeCategoria(Long id);
    List<Libro> listarLibrosDeAutor(String autor);
    Libro crear(LibroPeticionDto libro);
    Libro modificar(Long id, LibroPeticionDto libro);
    void eliminar(Long id);



}
