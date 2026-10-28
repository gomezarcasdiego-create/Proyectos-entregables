package es.decroly.biblioteca.service;

import ch.qos.logback.core.util.COWArrayList;
import es.decroly.biblioteca.dto.LibroPeticionDto;
import es.decroly.biblioteca.exception.NotFoundException;
import es.decroly.biblioteca.model.Libro;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j

public abstract class LibroServiceBase implements LibroService {

    private List<Libro> libros = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public LibroServiceBase(List<Libro> libros) {
        this.libros = new ArrayList<>(libros);
        long maxId = 0;
        for (Libro l : libros){
            if (l.getId() > maxId)
                maxId = l.getId();
        }

        this.contadorId.set(maxId);
    }


    @Override
    public Libro listarLibro(Long id){
        return (Libro) this.libros;
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
        return libros.stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    @Override
    public Libro crear(LibroPeticionDto dto) {
        Libro libro = new Libro();
        libro.setId(contadorId.getAndIncrement());
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
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
        return libro;
    }

    @Override
    public void eliminar(Long id) {
        libros.removeIf(l -> l.getId().equals(id));
    }
}


