package es.decroly.biblioteca.service;

import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.model.Libro;

import java.util.List;

public abstract class LibroServiceImpl implements LibroService {


    public LibroServiceImpl() {
    }

    public LibroServiceImpl(List<Libro> elPrincipito, Libro libro, Libro libro1, Libro libro2, Libro juegoDeTronos) {
    }

    @Override
    public Libro listarLibro(Long Id) {
        return null;
    }

    @Override
    public Libro buscarPorId(Long Id) {
        return null;
    }

    @Override
    public List<Libro> listarLibrosDisponibles() {
        return List.of();
    }

    @Override
    public List<Libro> obtenerLibrosDeCategoria(Long id) {
        return List.of();
    }

    @Override
    public List<Libro> listarLibrosDeAutor(String autor) {
        return List.of();
    }

    @Override
    public Libro crear(LibroPeticionDto libro) {
        return null;
    }

    @Override
    public Libro modificar(Long id, LibroPeticionDto libro) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
