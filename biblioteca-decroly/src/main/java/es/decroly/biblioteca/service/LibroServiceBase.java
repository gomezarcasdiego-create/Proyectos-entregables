package es.decroly.biblioteca.service;

import ch.qos.logback.core.util.COWArrayList;
import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.exception.NotFoundException;
import es.decroly.biblioteca.model.Libro;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j

public abstract class LibroServiceBase implements LibroService {

    private ArrayList<Libro> libros = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(6);

    public LibroServiceBase(List<Libro> libros) {
        this.libros = new ArrayList<>(libros);
        long maxId = 0;
        for (Libro l : libros){
            if (l.getId() > maxId)
                maxId = l.getId();
        }

        this.contadorId.set(maxId + 1);
    }


    @Override
    public Libro listarLibro(Long id) {
        return libros.stream()
                .filter(libro -> libro.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Libro buscarPorId(Long Id){
        return libros.stream()
                .filter(l -> l.getId().equals(Id))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("No se encontro con ese id" + Id));
    }

    @Override
    public List<Libro> listarLibrosDisponibles() {
        return libros.stream()
                .filter(Libro::isDisponible)
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> obtenerLibrosDeCategoria(Long id) {
        return libros.stream()
                .filter(l -> l.getCategoriaId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Libro> listarLibrosDeAutor(String autor) {
        if (autor == null || autor.isBlank()) {
            return new ArrayList<>(libros);
        }

        return libros.stream()
                .filter(libro -> libro.getAutor() != null
                        && libro.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    @Override
    public Libro crear(LibroPeticionDto dto) {
        Libro libro = new Libro();
        libro.setId(contadorId.getAndIncrement());
        libro.setIsbn(dto.getIsbn());
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setAnioPublicacion(dto.getAnioPublicacion());
        libro.setCategoriaId(dto.getCategoriaId());
        libro.setDisponible(true);
        libros.add(libro);
        return libro;
    }

    @Override
    public Libro modificar(Long id, LibroPeticionDto dto) {
        Libro libro = listarLibro(id);
        if (libro == null) {
            return null;
        }
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setCategoriaId(dto.getCategoriaId());
        libro.setDisponibles(dto.getDisponibles());
        libro.setEjemplares(dto.getEjemplares());
        libro.setIsbn(dto.getIsbn());
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setAnioPublicacion(dto.getAnioPublicacion());
        return libro;

    }

    @Override
    public void eliminar(Long id) {
        libros.removeIf(l -> l.getId().equals(id));
    }
}


